import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import pro.admin.AdminView;
import pro.user.login.HandleLogin;
import pro.user.login.Login;
import tools.InitGlobalFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame implements ActionListener {
    Login login;
    JTextField inputID;
    JPasswordField inputPassword;
    JButton buttonLogin;
    boolean loginSucess;
    public AdminLogin (){
        JPanel j1,j2;
        j1 = new JPanel();
        j1.setLayout(new FlowLayout());
        j2 = new JPanel();
        setLayout ( new GridLayout (3,1) );
        login = new Login();
        inputID = new JTextField(12);
        inputPassword=new JPasswordField(12);
        buttonLogin = new JButton("登录");
        setTitle("管理员登录");
        j1.add(new JLabel("账号:"));
        j1.add(inputID);
        j1.add(new JLabel("密码:"));
        j1.add(inputPassword);
        j1.add(buttonLogin);
        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon = new ImageIcon("D:/JavaProjects/JavaProject/src/image/NCRE.png"); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(350, 200, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        j2.add(lblBackground); // 将组件添加到面板中
        add(j1);
        add(j2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buttonLogin.addActionListener(this);//点击登录按钮后触发事件
    }
    public Login getLogin() {
        return login;
    }

    public boolean isLoginSucess(){
        return loginSucess;
    }
    public void actionPerformed(ActionEvent e){
        if(inputID.getText().equals("admin")){//登录账号为admin才执行下面操作


        login.setID(inputID.getText());
        char []pw=inputPassword.getPassword();
        login.setPassword(new String(pw));
        HandleLogin handleLogin =new HandleLogin();
        login =handleLogin.queryVerify(login);
        loginSucess= login.getLoginSucess();
        if(loginSucess){
            this.setVisible(false);
            this.dispose();
            try {
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            }catch(Exception se) {
                System.out.println(e);
            }
            InitGlobalFont.DoInitGlobalFont(new Font("宋体", Font.PLAIN, 18));
            AdminView adv=new AdminView ();
            adv.setVisible(true);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;
            adv.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
        }
        }
        else {
            JOptionPane.showMessageDialog(null,"登录失败,因为您不是管理员","登录失败",JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main ( String[] args )
    {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }catch(Exception e) {
            System.out.println(e);
        }
        InitGlobalFont.DoInitGlobalFont(new Font ("宋体", Font.PLAIN, 18));
        AdminLogin adl=new AdminLogin ();
        adl.setVisible(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        adl.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
    }
}
