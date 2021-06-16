package pro.user.registerforexam;

import pro.user.login.Login;
import tools.GetDBConnection;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class HandleRFE {
    Connection con;
    PreparedStatement preSql;

    public HandleRFE() {
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
    }

    public void updateRFE(RegisterForExam registerForExam) {//可以设置考试报名限制 改为默认值为 x 每次报名x--，当x为0时，提醒不能报名
        String sqlstr = "update province_info set number = number+1 where province = ?";
        int ok = 0;
        try {//报名同时修改两个表 考试系统信息和学生信息//其实是一个库
            preSql = con.prepareStatement(sqlstr);
            preSql.setString(1, registerForExam.getProvince());
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "id不能重复", "警告", JOptionPane.WARNING_MESSAGE);
        }
        ;
        if (ok != 0) {
            JOptionPane.showMessageDialog(null, "报名成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //test
        HandleRFE h=new HandleRFE();
        int ok = 0;
        Connection con;
        PreparedStatement preSql;
        RegisterForExam registerForExam=new RegisterForExam();
        Login login=new Login();
        login.setID("123");
        registerForExam.setLogin(login);
        registerForExam.setProvince("河南省");
        try {
            String uri = "jdbc:mysql://localhost:3306/ncre?useSSL=false&characterEncoding=utf-8";
            con = DriverManager.getConnection(uri, "root", "0617");
            String sqlstr = "update user set province = " + registerForExam.getProvince() + " where id = ?";
//            String sqlStr = "update  mess set name='杰克' where name='杰克逊' ";
            preSql = con.prepareStatement(sqlstr);
//            preSql.setString(1, registerForExam.login.getId());
            ok = preSql.executeUpdate();
            con.close();
        }
        catch (SQLException e){
          System.out.println("error");
        }
    }
}
