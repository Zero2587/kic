package top.weishilei.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.weishilei.bean.Record;

import java.util.List;

/**
 * @author weishilei
 * 记录持久层
 */
@Mapper
public interface RecordMapper {

    /**
     * 查询所有记录
     * @param delete 1:未删除，0:删除
     * @return
     */
    List<Record> selectAllRecord(@Param("delete") Integer delete);

    /**
     * 根据用户id查询记录
     * @param userId
     * @param delete 1:未删除，0:删除
     * @return
     */
    List<Record> selectRecordByUserId(@Param("userId") Integer userId, @Param("delete") Integer delete);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insertRecord(Record record);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    int deleteRecordById(Integer id);

    /**
     * 根据用户id删除记录
     * @param userId
     * @return
     */
    int deleteRecordByUserId(Integer userId);

    /**
     * 插入评论
     * @param record
     * @return
     */
    int insertRecordComment(Record record);

    /**
     * 根据id查询所有评论
     * @param id
     * @return
     */
    List<Record> selectRecordCommentById(Integer id);
}
