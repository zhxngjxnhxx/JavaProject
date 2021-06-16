package pro.user.registerforexam;

import pro.admin.query.Query;
import pro.user.login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RFEview extends JFrame implements ActionListener {
    RegisterForExam registerForExam;
    JButton buttonRegister;
    JPanel panel = new JPanel();

    public RFEview() {//省份选择框
        setLayout(new BorderLayout());
        registerForExam = new RegisterForExam();
        buttonRegister = new JButton("报名");
        String[][] content;
        Query findrecord = new Query();
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content = findrecord.getRecord();//记录二维的
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < content.length; i++) {
            JRadioButton jbi = new JRadioButton(content[i][0]);
            panel.add(jbi);
            group.add(jbi);
        }
        panel.setBorder(BorderFactory.createTitledBorder("请选择省份"));// 定义一个面板的边框显示条
//      panel.setLayout(new GridLayout( (int)Math.ceil(content.length/3), 3));// 定义排版，n行三列
        panel.setLayout(new GridLayout(3, 3));// 定义排版，n行三列
//
     //加个滚动条
        //报名按钮变小一点
        add(panel, BorderLayout.CENTER);// 加入面板
        add(buttonRegister,BorderLayout.SOUTH);
        buttonRegister.addActionListener((this));//点击注册按钮后会触发事件
        //一名用户只能报名一次考试
    }
    public void setRegisterForExam(Login login){
        this.registerForExam.setLogin(login);
    }
    public void actionPerformed(ActionEvent e) {//点击按钮后执行的事件
        String info = "";
        for (Component c : this.panel.getComponents()) {//获取用户选择的省份
            if (c instanceof JRadioButton) {
                if (((JRadioButton) c).isSelected()) {
                    info += ((JRadioButton) c).getText();
                }
            }
        }
        registerForExam.setProvince(info);//获取到了用户选择的省份
        //数量应该使用数据库中的
        int p = registerForExam.getNumber();
        HandleRFE handleRFE = new HandleRFE();
        handleRFE.updateRFE(registerForExam);
        setVisible(false);// 本窗口隐藏,
        this.dispose();

    }

    public static void main(String[] args) {
//        JFrame window = new JFrame();
        RFEview rfeview = new RFEview();

        rfeview.setVisible(true);
        rfeview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rfeview.setBounds(200, 200, 800, 260);
    }
}
