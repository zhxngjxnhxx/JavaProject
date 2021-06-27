import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import pro.user.LoginandRegister;
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
        setLayout(new GridLayout(3,1));
        view = new LoginandRegister();
        computerButton = new JButton("报名考试");
        computerButton.addActionListener(this);
        // 设置背景
        JPanel j2=new JPanel();
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon = new ImageIcon("D:/JavaProjects/JavaProject/src/image/NCRE.png"); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(350, 200, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        j2.add(lblBackground); // 将组件添加到面板中
        add(view);
        add(j2);
        JPanel j3 =new JPanel();
        j3.setLayout(new BorderLayout());
        j3.add(computerButton,BorderLayout.SOUTH);
        add(j3);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(view.isLoginSucess()==false){
            JOptionPane.showMessageDialog(null,"请先登录","登录提示",JOptionPane.WARNING_MESSAGE);
        }
        else {//调用报名考试
            RFEview rfeview = new RFEview();
            rfeview.setRegisterForExam(view.loginView.getLogin());//把登录对象传入报名系统
            rfeview.setVisible(true);
            rfeview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rfeview.setBounds(200, 200, 800, 260);

        }
    }

    public static void main(String[] args) {
        try {//加载lookandfeel
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        }catch(Exception e) {
            System.out.println(e);
        }
        InitGlobalFont.DoInitGlobalFont(new Font("宋体", Font.PLAIN, 18));
        MainWindow window=new MainWindow();
        window.setTitle("主窗口");
    }
}
