package com.is.lib_util;

/**
 * @author 夜斗
 * @date 2019/11/12
 */
public class FileInfo {
    /**
     * 当扫描app缓存文件的时候其不为null ，app的包名
     */
    private String cacheAppPackageName;
    /**
     * 扫描到的文件名称
     */
    private String fileName;
    /**
     * 扫描到的文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private long fileSize;

    public FileInfo setCacheAppPackageName(String cacheAppPackageName) {
        this.cacheAppPackageName = cacheAppPackageName;
        return this;
    }

    public String getCacheAppPackageName() {
        return cacheAppPackageName;
    }

    public String getFileName() {
        return fileName;
    }

    public FileInfo setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileInfo setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public FileInfo setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }


    @Override
    public String toString() {
        return "FileInfo{" +
                "cacheAppPackageName='" + cacheAppPackageName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
