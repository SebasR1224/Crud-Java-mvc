
package Model;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {
    Connection connection;
    
    
    public Connection getConnection(){
        String url ="jdbc:mysql://localhost:3306/java1";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
        }
        return connection;
    }
    
}
