import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
        Connection con=null;
        java.sql.PreparedStatement pst;
        public static Connection dbconnect()
        {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:4306/quiz","root","");
                return conn;

            }catch (Exception e1)
            {
                e1.printStackTrace();
                return null;
            }
        }
    }

