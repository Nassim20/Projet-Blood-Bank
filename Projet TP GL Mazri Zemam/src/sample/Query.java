package sample;


import javax.xml.transform.Result;
import java.sql.*;

public class Query {
    private Connection connection;
    private StringBuilder query;

    public Query() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/gl","root","chakib");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        return rs;
    }

    public ResultSet delete_line(String query) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        return rs;
    }


    public String getQuery(){
        return query.toString();
    }
}
