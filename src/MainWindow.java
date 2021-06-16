import pro.user.registerforexam.RFEview;
import tools.InitGlobalFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    JButton computerButton;
    LoginandRegister view;
    MainWindow(){
        view = new LoginandRegister();
        computerButton = new JButton("报名考试");
        computerButton.addActionListener(this);
        add(view, BorderLayout.CENTER);
        add(computerButton,BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(view.isLoginSucess()==false){
            JOptionPane.showMessageDialog(null,"请登录","登录提示",JOptionPane.WARNING_MESSAGE);
        }
        else {//调用报名考试
//            setVisible(false);// 本窗口隐藏,
//            JFrame window = new JFrame();
            RFEview rfeview = new RFEview();
            rfeview.setRegisterForExam(view.loginView.getLogin());//把登录对象传入报名系统
//            window.add(rfeview);
            rfeview.setVisible(true);
            rfeview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rfeview.setBounds(200, 200, 800, 260);

        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println("name:" + info.getName());
                System.out.println("class:" + info.getClassName());
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        InitGlobalFont.DoInitGlobalFont(new Font("宋体", Font.PLAIN, 18));
        MainWindow window=new MainWindow();
        window.setTitle("主窗口");
    }
}
