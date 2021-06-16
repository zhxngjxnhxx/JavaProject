package pro.user.register;

import tools.GetDBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleRegister {
    Connection con;
    PreparedStatement preSql;
    public HandleRegister(){
        Connection con = GetDBConnection.connectionDB("ncre", "root", "0617");
    }
    public void writeRegisterModel(Register register){
        String sqlstr="insert into register values(?,?,?)";
        int ok=0;
        try {
            preSql=con.prepareStatement(sqlstr);
            preSql.setString(1,register.getId());
            preSql.setString(2,register.getPassword());
            preSql.setString(3,register.getBirth());
            ok = preSql.executeUpdate();
            con.close();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"id不能重复","警告",JOptionPane.WARNING_MESSAGE);
        };
        if(ok!=0){
            JOptionPane.showMessageDialog(null,"注册成功","恭喜",JOptionPane.WARNING_MESSAGE);
        }
    }
}
