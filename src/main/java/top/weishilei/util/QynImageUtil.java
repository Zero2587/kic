package top.weishilei.util;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author weishilei
 * 七云牛上传图片工具类
 */
@Component
public class QynImageUtil {
    private static String[] imageSuffix = {".jpg", ".jpeg", ".gif", ".png"};
    private static final String ak = "Z_itF5nJ2lDQr-Lmcp_hdmr8UOuIHDih76T4OOUJ";
    private static final String sk = "WEYDpdHE_Dqiz5XLCGlWbEPFK28uzNPTr4Ys8sBn";
    private static final String path = "kicimage";
    private static final String url = "http://pmfifltnb.bkt.clouddn.com";

    /**
     * 判断图片
     * @param file
     * @return
     */
    public static boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int lastIndex = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(lastIndex, fileName.length());
        for (String suffix : imageSuffix) {
            if (StringUtils.equals(suffix, fileSuffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 上传图片
     * @param file
     * @param key
     * @return
     * @throws IOException
     */
    public static String saveImage(FileInputStream file, String key) throws IOException {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        Auth auth = Auth.create(ak, sk);
        String upToken = auth.uploadToken(path + "/test");

        Response response = uploadManager.put(file, key, upToken, null, null);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        String returnPath = url + "/" + putRet.key;

        return returnPath;
    }

    /**
     * 文件名称+当前时间戳，避免文件名称重复
     * @param fileName
     * @return
     */
    public static String jointImageName(String fileName) {
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        return prefix + System.currentTimeMillis() + suffix;
    }

}
