package pro.user.login;

import pro.user.login.HandleLogin;
import pro.user.login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {
    Login login;
    JTextField inputID;
    JPasswordField inputPassword;
    JButton buttonLogin;
    boolean loginSucess;
    public LoginView(){
        login = new Login();
        inputID = new JTextField(12);
        inputPassword=new JPasswordField(12);
        buttonLogin = new JButton("登录");
        add(new JLabel("ID："));
        add(inputID);
        add(new JLabel("密码:"));
        add(inputPassword);
        add(buttonLogin);
        buttonLogin.addActionListener(this);//点击登录按钮后触发事件
    }

    public Login getLogin() {
        return login;
    }

    public boolean isLoginSucess(){
        return loginSucess;
    }
    public void actionPerformed(ActionEvent e){
        login.setID(inputID.getText());
        char []pw=inputPassword.getPassword();
        login.setPassword(new String(pw));
        HandleLogin handleLogin =new HandleLogin();
        login =handleLogin.queryVerify(login);
        loginSucess= login.getLoginSucess();
        if(loginSucess){
            add(new JLabel("报名与否："));
            if(login.province.equals("")){//还没有报名
                add(new JLabel("否"));
                validate();
            }
            else {
                add(new JLabel("是"));
                add(new JLabel("报名考场:"));
                add(new JLabel(login.province));
                validate();
            }
        }
    }
}
