package com.is.lib_util.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 夜斗
 * @date 2019/11/12
 */
public class ScanConstant {


    //每种类型的文件包含的后缀
    public static final Map<ScanType, Set<String>> All_CATEGORY_SUFFIX;
    /**
     * 视频类
     */
    public static final Set<String> videoTypes;
    /**
     * 文件类
     */
    public static final Set<String> documentTypes;
    /**
     * 图片类
     */
    public static final Set<String> pictureTypes;
    /**
     * 音频类
     */
    public static final Set<String> musicTypes;
    /**
     * 安装包类
     */
    public static final Set<String> apkTypes;
    /**
     * 压缩包类
     */
    public static final Set<String> zipTypes;
    /**
     * 缓存文件
     */
    public static final Set<String> cacheFileType;


    static {
        //初始化赋值
        All_CATEGORY_SUFFIX = new HashMap<>();
        //多媒体文件类型
        videoTypes = new HashSet<>();
        videoTypes.add("mp4");
        videoTypes.add("avi");
        videoTypes.add("wmv");
        videoTypes.add("flv");
        All_CATEGORY_SUFFIX.put(ScanType.video, videoTypes);
        //文档文件类型
        documentTypes = new HashSet<>();
        documentTypes.add("txt");
        documentTypes.add("pdf");
        documentTypes.add("doc");
        documentTypes.add("docx");
        documentTypes.add("xls");
        documentTypes.add("xlsx");
        All_CATEGORY_SUFFIX.put(ScanType.document, documentTypes);
        //图片文件类型
        pictureTypes = new HashSet<>();
        pictureTypes.add("jpg");
        pictureTypes.add("jpeg");
        pictureTypes.add("png");
        pictureTypes.add("bmp");
        pictureTypes.add("gif");
        All_CATEGORY_SUFFIX.put(ScanType.picture, pictureTypes);
        //音频文件类型
        musicTypes = new HashSet<>();
        musicTypes.add("mp3");
        musicTypes.add("ogg");
        All_CATEGORY_SUFFIX.put(ScanType.music, musicTypes);

        //apk文件类型
        apkTypes = new HashSet<>();
        apkTypes.add("apk");
        All_CATEGORY_SUFFIX.put(ScanType.apk, apkTypes);

        //压缩包文件类型
        zipTypes = new HashSet<>();
        zipTypes.add("zip");
        zipTypes.add("rar");
        zipTypes.add("7z");
        All_CATEGORY_SUFFIX.put(ScanType.zip, zipTypes);

        //缓存文件类型
        cacheFileType = new HashSet<>();
        cacheFileType.add("log");
        All_CATEGORY_SUFFIX.put(ScanType.cacheFile, cacheFileType);

        //添加空文件扫描
        All_CATEGORY_SUFFIX.put(ScanType.emptyFile, new HashSet<String>());

        //添加app缓存扫描
        All_CATEGORY_SUFFIX.put(ScanType.appCache, new HashSet<String>());
    }

    public static Map<ScanType, Set<String>> switchType(ScanType... ScanType) {
        Map<ScanType, Set<String>> mCategorySuffix = new HashMap<>();
        for (ScanConstant.ScanType scanType : ScanType) {
            switch (scanType) {
                case video:
                    mCategorySuffix.put(ScanConstant.ScanType.video, videoTypes);
                    break;
                case document:
                    mCategorySuffix.put(ScanConstant.ScanType.document, documentTypes);
                    break;
                case picture:
                    mCategorySuffix.put(ScanConstant.ScanType.picture, pictureTypes);
                    break;
                case music:
                    mCategorySuffix.put(ScanConstant.ScanType.music, musicTypes);
                    break;
                case apk:
                    mCategorySuffix.put(ScanConstant.ScanType.apk, apkTypes);
                    break;
                case zip:
                    mCategorySuffix.put(ScanConstant.ScanType.zip, zipTypes);
                    break;
                case cacheFile:
                    mCategorySuffix.put(ScanConstant.ScanType.cacheFile, cacheFileType);
                    break;
                case appCache:
                    mCategorySuffix.put(ScanConstant.ScanType.appCache, new HashSet<String>());
                    break;
                case emptyFile:
                    mCategorySuffix.put(ScanConstant.ScanType.emptyFile, new HashSet<String>());
                    break;
                case allFile:
                    mCategorySuffix.clear();
                    return All_CATEGORY_SUFFIX;

            }
        }
        return mCategorySuffix;
    }

    /**
     * 扫描类型
     */
    public enum ScanType {
        /**
         * 视频类型
         */
        video,
        /**
         * 文档
         */
        document,
        /**
         * 图片
         */
        picture,
        /**
         * 音乐
         */
        music,
        /**
         * 安装包
         */
        apk,
        /**
         * 压缩包
         */
        zip,
        /**
         * 所有类型
         */
        allFile,
        /**
         * 缓存文件
         */
        cacheFile,
        /**
         * 应用的缓存文件夹
         */
        appCache,
        /**
         * 空文件夹
         */
        emptyFile,

        /**
         * 大文件
         */
        bigFile,
    }

    public static class HandlerWhat {
        /**
         * 文件
         */
        public static final int fileInfo = 0x0002;

    }

}
