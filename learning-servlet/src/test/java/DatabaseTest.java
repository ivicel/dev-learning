import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import info.ivicel.dao.impl.JdbcTemplate;


public class DatabaseTest {

    @Test
    public void testDatabaseInit() {
        Connection conn = JdbcTemplate.getConnection();
        Assert.assertNotNull(conn);
    }
}
