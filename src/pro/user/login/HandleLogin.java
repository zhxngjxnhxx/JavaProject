package pro.user.login;
import tools.GetDBConnection;
import javax.swing.*;
import java.sql.*;
public class HandleLogin {
    Connection con;
    PreparedStatement preSql;
    ResultSet rs;
    public HandleLogin(){
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
    }
    public Login queryVerify(Login loginModel){
        String id =loginModel.getId();
        String pw =loginModel.getPassword();
        String sqlStr="select id,password,province,level from user where "+"id = ? and password = ?";//在数据库中寻找指定的信息
        try {
            preSql =con.prepareStatement(sqlStr);
            preSql.setString(1,id);
            preSql.setString(2,pw);//直接与数据库中的对比
            rs = preSql.executeQuery();
            if(rs.next()==true){//查找到了记录
                loginModel.setLoginSucess(true);
                JOptionPane.showMessageDialog(null,"登录成功","恭喜",JOptionPane.WARNING_MESSAGE);
                //登录成功应该弹出个人信息  报告考试信息 并且报考后该考试不可再次报考

                loginModel.setprovince(rs.getString(3));
                loginModel.setLevel(rs.getInt(4));
            }
            else {
                loginModel.setLoginSucess(false);
                JOptionPane.showMessageDialog(null,"登录失败","登录失败,重新登录",JOptionPane.WARNING_MESSAGE);
            }
            con.close();
        }
        catch (SQLException e){}
        return loginModel;
    }

}
