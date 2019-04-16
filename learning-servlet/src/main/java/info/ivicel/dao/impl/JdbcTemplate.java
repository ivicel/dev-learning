package info.ivicel.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import info.ivicel.annotation.Column;
import info.ivicel.annotation.Table;
import info.ivicel.serivce.RowMapper;

public class JdbcTemplate {
    private static DruidDataSource ds;
    private static final String PROPERTIES = "db.properties";
    private static final String DB_DRIVER = "db.classDriverName";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static String a;
    private static String b;
    private static String c;

    static {
        try {
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(PROPERTIES));

            String driver = props.getProperty(DB_DRIVER);
            if (invalidateProp(driver)) {
                throw new RuntimeException("Error to find JDBC driver");
            }

            String url = props.getProperty(DB_URL);
            if (invalidateProp(url)) {
                throw new RuntimeException("Database url is empty");
            }

            String username = props.getProperty(DB_USERNAME);
            if (invalidateProp(username)) {
                throw new RuntimeException("Database username is empty");
            }

            String password = props.getProperty(DB_PASSWORD);
            if (invalidateProp(password)) {
                throw new RuntimeException("Database password is empty");
            }
            Class.forName(driver.trim());
//            ds = new DruidDataSource();
//            ds.setMaxActive(5);
//            ds.setUrl(url.trim());
//            ds.setUsername(username.trim());
//            ds.setPassword(password.trim());
            a = url;
            b = username;
            c = password;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public static Connection getConnection() {
        try {
//            return ds.getConnection();
            return DriverManager.getConnection(a, b, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T query(String sql, RowMapper<T> mapper, Object... args) {
        Connection conn = JdbcTemplate.getConnection();
        if (conn == null) {
            return null;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T t = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                t = mapper.mapRow(rs, rs.getRow());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTemplate.close(pstmt, conn, rs);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <T> T queryObject(String query, Object value, Class<T> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(80);
        sb.append("SELECT ");
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> map = new HashMap<>(fields.length);
        boolean isFirst = true;
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if (!isFirst) {
                    sb.append(", ");
                }
                isFirst = false;
                map.put(column.value(), field);
                sb.append("`");
                sb.append(column.value());
                sb.append("`");
            }
        }
        sb.append(" FROM `");
        sb.append(table.value());
        sb.append("` WHERE ");
        sb.append(query);
        sb.append(" = ?");

        return (T) query(sb.toString(), (rs, num) -> {
            try {
                Object o = clazz.getConstructor().newInstance();
                for (Map.Entry<String, Field> entry : map.entrySet()) {
                    Field field = entry.getValue();
                    field.setAccessible(true);
                    field.set(o, rs.getObject(entry.getKey()));
                }
                return o;
            } catch (IllegalAccessException | InstantiationException |
                    NoSuchMethodException | InvocationTargetException ignored) {
            }
            return null;
        }, value);
    }

    public static long query(String sql) {
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, conn, rs);
        }
        return 0;
    }

    public static <T> List<T> findAll(String sql, RowMapper<T> mapper) {
        Connection conn = JdbcTemplate.getConnection();
        if (conn == null) {
            return new ArrayList<>();
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> list = new LinkedList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T t = mapper.mapRow(rs, rs.getRow());
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTemplate.close(pstmt, conn, rs);
        }
        return list;
    }

    private static boolean invalidateProp(String prop) {
        return prop == null || "".equals(prop.trim());
    }

    public static int update(String sql, Object... args) {
        Connection conn = JdbcTemplate.getConnection();
        PreparedStatement pstmt = null;
        if (conn == null) {
            return 0;
        }
        int count = 0;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareCall(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            count = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcTemplate.close(pstmt, conn, null);
        }
        return count;
    }

    public static void close(Statement stmt, Connection conn, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignored) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignored) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
