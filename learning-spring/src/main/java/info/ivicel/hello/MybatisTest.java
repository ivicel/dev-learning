package info.ivicel.hello;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MybatisTest {

    @Test
    public void queryTest() {
        SqlSession session = MybatisUtil.getSession();
//        List<Student> students = session.selectList("info.ivicel.hello.StudentMapper.selectStudent");
        List<Student> students = session.getMapper(StudentDao.class).listAll();
        System.out.println(students);
    }
}
