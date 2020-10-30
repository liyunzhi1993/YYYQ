package yyyq.external.service;

public interface QiniuClientService {
    /**
     * 上传文件流
     * @param fileName
     * @param fileByte
     * @return
     */
    String uploadImage(String fileName,byte[] fileByte);
}
