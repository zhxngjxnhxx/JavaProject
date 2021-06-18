package pro.admin.query;

import tools.GetDBConnection;

import java.sql.*;

public class ExactSearch
{
    public ExactSearch() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e) {

        }
    }
    public static int doINTExactSearch(String sqlstr) {
        int number=0;
        Connection con = GetDBConnection.connectionDB("ncre", "root", "0617");
        PreparedStatement sql;
        ResultSet rs=null;
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
//        String sqlstr="select number from province_info where province = '辽宁省'";
        if (con == null)
        {
            System.out.println("连接失败");
        }
        try
        {
            sql = con.prepareStatement(sqlstr);
            rs = sql.executeQuery(sqlstr);
        }
        catch ( SQLException e ){

        }
        try
        { if(rs!=null){
            while (rs.next()){

                number = rs.getInt(1);
            }


        }
            con.close();
        }
        catch ( SQLException e )
        {
            System.out.println(e);
        }
        return number;
    }
    public static String dostrExactSearch(String sqlstr) {
        String str="";
        Connection con = GetDBConnection.connectionDB("ncre", "root", "0617");
        PreparedStatement sql;
        ResultSet rs=null;
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
        //        String sqlstr="select number from province_info where province = '辽宁省'";
        if (con == null)
        {
            System.out.println("连接失败");
        }
        try
        {
            sql = con.prepareStatement(sqlstr);
            rs = sql.executeQuery(sqlstr);
        }
        catch ( SQLException e ){

        }
        try
        { while (rs.next()){

            str = rs.getString(1);

        }
            con.close();
        }
        catch ( SQLException e )
        {
            System.out.println(e);
        }
        return str;
    }

    public static void main(String[] args)
    {

    }
}
