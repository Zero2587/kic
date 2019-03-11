package top.weishilei.kic;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.weishilei.bean.Record;
import top.weishilei.mapper.RecordMapper;

public class RecordApplicationTests extends KicApplicationTests {
    @Autowired
    private RecordMapper recordMapper;

    //@Test
    public void testRecordSelectAll() {
        System.out.println(recordMapper.selectAllRecord(1));

    }

    //@Test
    public void testRecordInsert() {
        Record record = new Record();
        record.setContent("test");
        record.setUserId(1);
        record.setCreateName("dddddddd");
        recordMapper.insertRecord(record);
    }
}
