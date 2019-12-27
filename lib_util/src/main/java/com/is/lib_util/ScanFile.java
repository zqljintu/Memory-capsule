package com.is.lib_util;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.is.lib_util.constant.ScanConstant;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 夜斗
 * @date 2019/11/12
 * 用法
 * 要扫秒的文件绝对路径
 * final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
 * <p>
 * //单一线程线程池
 * ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
 * singleExecutorService.submit(new Runnable() {
 * @Override public void run() {
 * ScanFile scanFileCountUtil = new ScanFile
 * .Builder(mHandler)//设置handler回调
 * .setFilePath(path)//设置路径
 * .setScanTypes(ScanConstant.ScanType.allFile)//设置扫描文件的类型
 * .setFileNameFilter()//设置扫描过滤规则
 * .create();//创建
 * scanFileCountUtil.scanCountFile();//开始扫描
 * }
 * });
 * <p>
 * <p>
 * //文件扫描结束的处理
 * private Handler mHandler = new Handler(Looper.getMainLooper()) {
 * @Override public void handleMessage(Message msg) {
 * switch (msg.what) {
 * case ScanConstant.HandlerWhat.fileInfo://当文件全部扫面结束后会回调这个消息
 * Map<ScanConstant.ScanType, List<FileInfo>> filesInfo = (Map<ScanConstant.ScanType, List<FileInfo>>) msg.obj;
 * List<FileInfo> fileInfos = filesInfo.get(ScanConstant.ScanType.cache);//拿到对应类型的数据
 * String s = fileInfos.toString();
 * Log.d("DDDd", "cache 文件 " + s);
 * Log.d("DDDd", "文件 个数" + fileInfos.size());
 * <p>
 * break;
 * }
 * }
 * };
 */
public class ScanFile {


    /**
     * 扫描文件过滤
     */
    public FilenameFilter mFilenameFilter;
    /**
     * 扫描根目录
     */
    private String mFilePath;
    /**
     * 各个分类所对应的文件后缀
     */
    private Map<ScanConstant.ScanType, Set<String>> mCategorySuffix;
    /**
     * 所有文件集合
     */
    private ConcurrentHashMap<ScanConstant.ScanType, List<FileInfo>> mFileResult = new ConcurrentHashMap<>();
    /**
     * 用于存储文件目录便于层次遍历
     */
    private ConcurrentLinkedQueue<File> mFileConcurrentLinkedQueue;
    /**
     * 是否扫描无用的空文件夹
     */
    private boolean isScanEmptyDirectory = false;
    /**
     * 是否扫描应用的缓存文件
     */
    private boolean isScanAppCache = false;

    private Handler mHandler = null;

    public void scanCountFile() {
        if (mFilePath == null) {
            return;
        }
        final File file = new File(mFilePath);

        //非目录或者目录不存在直接返回
        if (!file.exists() || file.isFile()) {
            return;
        }
        //是否扫描应用缓存
        if (mCategorySuffix.containsKey(ScanConstant.ScanType.appCache)) {
            isScanAppCache = true;
            mCategorySuffix.remove(ScanConstant.ScanType.appCache);
        }
        //是否扫描空文件夹
        if (mCategorySuffix.containsKey(ScanConstant.ScanType.emptyFile)) {
            isScanEmptyDirectory = true;
            mCategorySuffix.remove(ScanConstant.ScanType.emptyFile);
        }

        //获取到根目录下的文件和文件夹
        final File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                if (mFilenameFilter != null) {
                    return mFilenameFilter.accept(file, s);
                }
                //过滤掉隐藏文件
                return !file.getName().startsWith(".");
            }
        });
        List<Runnable> runnableList = new ArrayList<>();
        //创建信号量(最多同时有10个线程可以访问)
//        final Semaphore semaphore = new Semaphore(100);
//
        for (File f : files) {
            if (f.isDirectory()) {
                //把目录添加进队列
                mFileConcurrentLinkedQueue.offer(f);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        scanDirFile();
                    }
                };
                runnableList.add(runnable);
            } else {
                //找到该文件所属的类别
                for (Map.Entry<ScanConstant.ScanType, Set<String>> entry : mCategorySuffix.entrySet()) {
                    //获取文件后缀
                    String suffix = f.getName().substring(f.getName().indexOf(".") + 1).toLowerCase();
                    //找到了
                    if (entry.getValue().contains(suffix)) {
                        saveFile(entry.getKey(), f);
                        break;
                    }
                }
            }
        }

        //固定数目线程池(最大线程数目为cpu核心数,多余线程放在等待队列中)
        int nThreads = Runtime.getRuntime().availableProcessors();
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        for (Runnable runnable : runnableList) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
        //等待线程池中的所有线程运行完成
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //传递统计数据给UI界面
        Message msg = Message.obtain();
        msg.what = ScanConstant.HandlerWhat.fileInfo;
        msg.obj = mFileResult;
        mHandler.sendMessage(msg);
    }

    /**
     * 遍历文件夹中的文件
     */
    private void scanDirFile() {
        //对目录进行层次遍历
        while (!mFileConcurrentLinkedQueue.isEmpty()) {
            //队头出队列
            final File tmpFile = mFileConcurrentLinkedQueue.poll();

            if (tmpFile == null) {
                continue;
            }

            //判断设个文件夹是否是缓存文件
            if (isScanAppCache && tmpFile.isDirectory()) {

                if (tmpFile.getName().contains("cache")
                        && tmpFile.length() != 0
                        && tmpFile.getParentFile().getName().startsWith("com.")) {
                    checkResultKeyValue(ScanConstant.ScanType.appCache);
                    mFileResult.get(ScanConstant.ScanType.appCache)
                            .add(new FileInfo().setFilePath(tmpFile.getPath())
                                    .setFileSize(FileUtils.getFolderSize(tmpFile))
                                    .setCacheAppPackageName(tmpFile.getParentFile().getName()));
                }
            }


            final File[] fileArray = tmpFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    //过滤掉隐藏文件 或者过滤自定义的规则 反正隐藏文件一定过滤掉
                    if (mFilenameFilter != null) {
                        return mFilenameFilter.accept(file, s);
                    }
                    //过滤掉隐藏文件
                    return !file.getName().startsWith(".");
                }
            });

            if (fileArray == null) {
                continue;
            }
            //判断是否是空文件夹 与是否需要删除空文件夹
            if (fileArray.length == 0 && isScanEmptyDirectory) {
                checkResultKeyValue(ScanConstant.ScanType.emptyFile);
                mFileResult.get(ScanConstant.ScanType.emptyFile)
                        .add(new FileInfo().setFilePath(tmpFile.getPath())
                                .setFileSize(0)
                                .setCacheAppPackageName(tmpFile.getParentFile().getName()));
                continue;
            }


            for (File f : fileArray) {
                if (f.isDirectory()) {
                    //把目录添加进队列
                    mFileConcurrentLinkedQueue.offer(f);
                } else {
                    //找到该文件所属的类别
                    for (Map.Entry<ScanConstant.ScanType, Set<String>> entry : mCategorySuffix.entrySet()) {
                        //获取文件后缀
                        String suffix = f.getName().substring(f.getName().indexOf(".") + 1).toLowerCase();
                        //找到了
                        if (entry.getValue().contains(suffix)) {
                            saveFile(entry.getKey(), f);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 将扫描到的文件整理到数组中
     *
     * @param key 文件类型
     * @param f   文件
     */
    private void saveFile(ScanConstant.ScanType key, File f) {
        //先判断集合中有没有这个类型的数据 如果没有就先创建一个空的集合
        //在添加数据
        checkResultKeyValue(key);
        mFileResult.get(key)
                .add(new FileInfo()
                        .setFileName(f.getName())
                        .setFilePath(f.getAbsolutePath())
                        .setFileSize(f.length()));
    }

    /**
     * @param key 检查要返回的数据集中是否有这个key 如果没有就创建一个空的
     */
    private void checkResultKeyValue(ScanConstant.ScanType key) {
        if (!mFileResult.containsKey(key)) {
            mFileResult.put(key, new ArrayList<FileInfo>());
        }
    }

    public static class Builder {
        private Handler mHandler;
        private String mFilePath;
        //各个分类所对应的文件后缀
        private Map<ScanConstant.ScanType, Set<String>> mCategorySuffix;


        private FilenameFilter mFilenameFilter;

        public Builder(Handler handler) {
            this.mHandler = handler;
        }

        public Builder setFileNameFilter(FilenameFilter filenameFilter) {
            mFilenameFilter = filenameFilter;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.mFilePath = filePath;
            return this;
        }

        public Builder setScanTypes(ScanConstant.ScanType... scanTypes) {
            mCategorySuffix = ScanConstant.switchType(scanTypes);
            return this;
        }

        private void applyConfig(ScanFile scanFileCountUtil) {
            scanFileCountUtil.mFilePath = mFilePath;
            scanFileCountUtil.mCategorySuffix = mCategorySuffix;
            scanFileCountUtil.mHandler = mHandler;
            if (mFilenameFilter != null) {
                scanFileCountUtil.mFilenameFilter = mFilenameFilter;
            }
            scanFileCountUtil.mFileConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        }

        public ScanFile create() {
            ScanFile scanFileCountUtil = new ScanFile();
            applyConfig(scanFileCountUtil);
            return scanFileCountUtil;
        }
    }


}
