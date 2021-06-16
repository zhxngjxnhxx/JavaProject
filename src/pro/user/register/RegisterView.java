package pro.user.register;
import pro.user.register.HandleRegister;
import pro.user.register.Register;

import javax.swing.*;
import java.awt.event.*;
public class RegisterView extends JPanel implements ActionListener{
    Register register;
    JTextField inputID,inputBirth;
    JPasswordField inputPassword;
    JButton buttonRegister;
    public RegisterView(){
        register = new Register();
        inputID=new JTextField(12);
        inputPassword=new JPasswordField(12);
        inputBirth=new JTextField(12);
        buttonRegister = new JButton("注册");
        add(new JLabel("ID:"));
        add(inputID);
        add(new JLabel("密码:"));
        add(inputPassword);
//        add(new JLabel("出生日期(****-**-**)"));
//        add(inputBirth);
        add(buttonRegister);
        buttonRegister.addActionListener((this));//点击注册按钮后会触发事件
    }
    public void actionPerformed(ActionEvent e){
        register.setId(inputID.getText());
        char []pw = inputPassword.getPassword();
        register.setPassword(new String(pw));
        register.setBirth(inputBirth.getText());
        HandleRegister handleRegister=new HandleRegister();
        handleRegister.writeRegisterModel(register);
    }
}
