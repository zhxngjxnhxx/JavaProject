package pro.user.login;

import pro.user.login.HandleLogin;
import pro.user.login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {
    Login login;
    JTextField inputID;
    JPasswordField inputPassword;
    JButton buttonLogin;
    boolean loginSucess;
    public LoginView(){
        setLayout(new GridLayout(2,1));
        login = new Login();
        inputID = new JTextField(12);
        inputPassword=new JPasswordField(12);
        buttonLogin = new JButton("登录");
        JPanel j0=new JPanel();
        j0.add(new JLabel("学号:"));
        j0.add(inputID);
        j0.add(new JLabel("密码:"));
        j0.add(inputPassword);
        j0.add(buttonLogin);
        add(j0);
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
            JPanel j1 =new JPanel();
            j1.add(new JLabel("报名与否："));
            if(login.province.equals("")){//还没有报名
                j1.add(new JLabel("否"));
                add(j1);
                validate();
            }
            else {

                j1.add(new JLabel("是  "));
                j1.add(new JLabel("报名考场:"));
                j1.add(new JLabel(login.province));
                add(j1);
                validate();
            }
        }
    }

}
