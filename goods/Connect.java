import java.sql.*;

public class Connect {
    
    private static Connection connection = null;

    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hill\\Desktop\\database\\myDatabase.accdb", "", "");
        } catch (ClassNotFoundException error) {
            error.printStackTrace();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}