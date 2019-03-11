package top.weishilei.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.weishilei.bean.Record;
import top.weishilei.bean.User;
import top.weishilei.result.Result;
import top.weishilei.result.ResultCode;
import top.weishilei.service.RecordService;

import java.util.List;

/**
 * 记录控制层
 */
@RestController
@RequestMapping("/record")
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;

    /**
     * 显示评论页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ModelAndView showComment(int id) {
        ModelAndView modelAndView = new ModelAndView("record/comment");
        modelAndView.addObject("comment", recordService.findAllRecordCommentById(id));

        return modelAndView;
    }

    /**
     * 新增记录
     * @param record
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Record record) {
        Result result = new Result(ResultCode.RESPONSE_RECORD_FAIL);

        if (StringUtils.isBlank(record.getContent())) {
            result.setResultCode(ResultCode.RESPONSE_RECORD_CONTENT_IS_EMPTY);
            return result.toJSONOString();
        }

        User user = super.getUser();
        if (user == null) {
            result.setResultCode(ResultCode.RESPONSE_RECORD_NOT_LOGIN);
            return result.toJSONOString();
        }

        record.setUserId(user.getId());
        record.setCreateName(user.getName());
        record.setCreateImage(user.getPhoto());
        try {
            record = recordService.addRecord(record);
        } catch (Exception e) {}

        if (record.getId() > 0) {
            result.setResultCode(ResultCode.RESPONSE_SUCCESS);
        }

        return result.toJSONOString();
    }

    /**
     * 查找记录
     * @param start 页数
     * @param size 显示数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String all(@RequestParam(value = "start", defaultValue = "0") int start,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(start,size,"id desc");
        List<Record> recordList = recordService.findAllRecord(1);

        return pageData(recordList);
    }

    /**
     * 查找用户记录
     * @param start
     * @param size
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String userRecord(@RequestParam(value = "start", defaultValue = "0") int start,
                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        User user = super.getUser();
        PageHelper.startPage(start,size,"id desc");
        List<Record> recordList = recordService.findRecodtByUserId(user.getId(),1);

        return pageData(recordList);
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public String delRecord(@RequestParam(value = "id") int id) {
        recordService.deleteRecord(id);

        return new Result(ResultCode.RESPONSE_SUCCESS).toJSONOString();
    }

    private String pageData(List<Record> recordList) {
        PageInfo<Record> pageInfo = new PageInfo<>(recordList);
        Result result = new Result(ResultCode.RESPONSE_SUCCESS);
        result.setData(pageInfo);

        return result.toJSONOString();
    }

}
