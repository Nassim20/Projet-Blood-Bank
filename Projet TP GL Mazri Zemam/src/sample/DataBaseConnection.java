package sample;
import java.sql.Connection;
import  java.sql.DriverManager;

public class DataBaseConnection {
    public Connection DatabaseLink;

    public Connection getConnection(){
        String DataBaseName = "GL";
        String user= "root";
        String  password = "chakib";
        String url = "jdbc:mysql://localhost/" + DataBaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DatabaseLink = DriverManager.getConnection(url,user,password);
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return DatabaseLink;
    }
}