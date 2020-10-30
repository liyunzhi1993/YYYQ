package yyyq.external.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import yyyq.external.service.QiniuClientService;

@Service
public class QiniuClientServiceImpl implements QiniuClientService {

    static final String accessKeyId = "KlNRz-_lluk60VMb1noZEW7d1JRE4Ozp6fnQjW9c";
    static final String accessKeySecret = "yjd1clns9WDM6iW2DzvJ8y6H3bAGO062PAIopbgm";
    static final String accessBucket = "yyyq";
    static final String accessUrl = "http://image.yiyouyiqu.com/";

    /**
     * 上传文件流
     *
     * @param fileName
     * @param fileByte
     * @return
     */
    @Override
    public String uploadImage(String fileName, byte[] fileByte) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = accessKeyId;
        String secretKey = accessKeySecret;
        String bucket = accessBucket;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(fileByte, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
            return "";
        }
        return accessUrl+fileName;
    }
}
