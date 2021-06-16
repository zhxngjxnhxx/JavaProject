package tools;

import java.sql.*;
//连接数据库
public class GetDBConnection {
    public static Connection connectionDB(String DBname,String id,String p){
        Connection con=null;
        Statement sql;
        ResultSet rs;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
        }
        String uri = "jdbc:mysql://localhost:3306/"+DBname+"?useSSL=false&characterEncoding=utf-8";
        try{
            con = DriverManager.getConnection(uri,id,p);
        }
        catch (SQLException e){ }
        return con;
    }
}
