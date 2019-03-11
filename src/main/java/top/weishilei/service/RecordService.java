package top.weishilei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weishilei.bean.Record;
import top.weishilei.mapper.RecordMapper;

import java.util.List;

/**
 * @author weishilei
 * 记录服务层
 */
@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 添加记录
     * @param record
     * @return
     */
    public Record addRecord(Record record) {
        if (record == null) {
            return null;
        }

        int successNum = 0;
        if (record.isComment()) {
            successNum = recordMapper.insertRecordComment(record);
        } else {
            successNum = recordMapper.insertRecord(record);
        }
        if (successNum < 1) {
            return null;
        }

        return record;
    }

    /**
     * 查找记录
     * @param delete 0：删除，1：未删除
     * @return
     */
    public List<Record> findAllRecord(int delete) {
        if (delete < 0) {
            return null;
        }

        List<Record> list = recordMapper.selectAllRecord(delete);

        return list;
    }

    /**
     * 根据用户id查找记录
     * @param id
     * @param delete
     * @return
     */
    public List<Record> findRecodtByUserId(int id, int delete) {
        if (delete < 0) {
            return null;
        }

        return recordMapper.selectRecordByUserId(id, delete);
    }

    /**
     * 根据id删除记录
     * @param id
     */
    public void deleteRecord(int id) {
        recordMapper.deleteRecordById(id);
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public List<Record> findAllRecordCommentById(int id) {
        if (id < 0) {
            return null;
        }

        return recordMapper.selectRecordCommentById(id);
    }

}
