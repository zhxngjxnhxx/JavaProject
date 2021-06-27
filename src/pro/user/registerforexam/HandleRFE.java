package pro.user.registerforexam;

import pro.admin.query.ExactSearch;
import pro.admin.query.Query;
import pro.user.login.Login;
import tools.GetDBConnection;
import javax.swing.*;
import java.sql.*;

public class HandleRFE {
    Connection con;
    PreparedStatement preSql;

    public HandleRFE() {
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
    }

    public void updateRFE(RegisterForExam registerForExam) {
        //先获取剩余名额
        String sqlstr="";
        if (registerForExam.getTolevel()==2){
            sqlstr="select number from province_info where province = '"+registerForExam.getProvince()+"'";//单引号问题

        }
        else if(registerForExam.getTolevel()==3){
            sqlstr="select number2 from province_info where province = '"+registerForExam.getProvince()+"'";//单引号问题

        }
        int number=0;
        //获取剩余名额
        Connection con = GetDBConnection.connectionDB("ncre", "root", "0617");
        ResultSet rs=null;
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
        //        String sqlstr="select number from province_info where province = '辽宁省'";
        if (con == null)
        {
            System.out.println("连接失败");
        }
        try
        {
            preSql = con.prepareStatement(sqlstr);
            rs = preSql.executeQuery(sqlstr);
        }
        catch ( SQLException e ){ }
        try
        { if(rs!=null){
            while (rs.next()){

                number = rs.getInt(1);
            }
        }
        }
        catch ( SQLException e ) { System.out.println(e); }

//        number =ExactSearch.doINTExactSearch(sqlstr);

        if(number>0){//有剩余名额

            if(registerForExam.toLevel==2&&registerForExam.login.getLevel()>=0){
                sqlstr = "update province_info set number = number-1 where province = '"+registerForExam.getProvince()+"'";

            }
            else if(registerForExam.toLevel==3&&registerForExam.login.getLevel()>=2){
                sqlstr = "update province_info set number2 = number2-1 where province = '"+registerForExam.getProvince()+"'";
            }
            else {
                JOptionPane.showMessageDialog(null, "无法报名", "提示", JOptionPane.WARNING_MESSAGE);
            }
        int ok = 0;
        try {//报名同时修改两个表 考试系统信息和学生信息//其实是一个库
            preSql = con.prepareStatement(sqlstr);
//            preSql.setString(2, registerForExam.getProvince());
            ok = preSql.executeUpdate();
            con.close();
            ok=0;
            String uri = "jdbc:mysql://localhost:3306/ncre?useSSL=false&characterEncoding=utf-8";
            con = DriverManager.getConnection(uri, "root", "0617");
//            sqlstr = "update register set province= "+registerForExam.getProvince()+" where id= ?";
            sqlstr = "update user set province = '"+ registerForExam.getProvince()+"' where id = '"+registerForExam.login.getId()+"'";
            preSql = con.prepareStatement(sqlstr);
//            preSql.setString(1, registerForExam.login.getId());
            ok = preSql.executeUpdate();
            con.close();
        }
        catch (SQLException e) { }
        if (ok != 0) {
            JOptionPane.showMessageDialog(null, "报名成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        }
        }
        else {
            JOptionPane.showMessageDialog(null, "无剩余名额", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }
}
