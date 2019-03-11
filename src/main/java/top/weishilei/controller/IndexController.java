package top.weishilei.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.weishilei.result.Result;
import top.weishilei.result.ResultCode;
import top.weishilei.util.QynImageUtil;

import java.io.FileInputStream;

/**
 * @author weishilei
 * 首页控制器
 */
@RestController
public class IndexController extends BaseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 显示首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        return new ModelAndView("index");
    }

    /**
     * 富文本图片上传接口
     * @param file
     * @return
     */
    @RequestMapping(value = "/uditImage", method = RequestMethod.POST)
    @ResponseBody
    public String uditUploadImage(MultipartFile file) {
        Result result = new Result(ResultCode.RESPONSE_RECORD_FAIL);

        if (!QynImageUtil.isImage(file)) {
            result.setResultCode(ResultCode.RESPONSE_IMAGE_NOT_IS_IMAGE);
            return result.toJSONOString();
        }

        String imagePath = null;
        String imageName = QynImageUtil.jointImageName(file.getOriginalFilename());
        try {
            imagePath = QynImageUtil.saveImage((FileInputStream) file.getInputStream(), imageName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imagePath != null) {
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
            JSONObject data = new JSONObject();
            data.put("src", imagePath);
            data.put("title", imageName);
            result.setData(data);
        }

        return result.toJSONOString();
    }
}
