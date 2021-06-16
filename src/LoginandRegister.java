import pro.user.login.LoginView;
import pro.user.register.RegisterView;

import javax.swing.*;
import java.awt.*;

public class LoginandRegister extends JPanel{
    JTabbedPane p;
    RegisterView registerView;
    LoginView loginView;
    public LoginandRegister(){
        registerView=new RegisterView();
        loginView=new LoginView();
        setLayout(new BorderLayout());
        p =new JTabbedPane();
        p.add("我要注册",registerView);
        p.add("我要登录",loginView);

        p.validate();
        add(p,BorderLayout.CENTER);
    }
    public boolean isLoginSucess(){
        return loginView.isLoginSucess();
    }

    public static void main(String[] args) {
        JFrame jf=new JFrame();
        LoginandRegister loginandRegister =new LoginandRegister();
        jf.setLayout(new BorderLayout());
        jf.add(loginandRegister,BorderLayout.CENTER);
        jf.setVisible(true);
    }
}
