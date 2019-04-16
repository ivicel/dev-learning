package learn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WebServer {

    public static void main(String[] args) {
        Connection conn;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
