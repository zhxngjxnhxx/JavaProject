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
    JRadioButton j1,j2;
    JPanel panel2,panel20;
    public RFEview() {//省份选择框
        setLayout(new BorderLayout());
        registerForExam = new RegisterForExam();
        buttonRegister = new JButton("确定");
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
        panel.setLayout(new GridLayout(6, 1));// 定义排版，n行三列
//
        //加个滚动条
        //报名按钮变小一点

        add(panel, BorderLayout.CENTER);// 加入面板
        JScrollPane scrollpane=new JScrollPane(panel);
        scrollpane.setBounds(100, 100, 100, 300);
        add(scrollpane,BorderLayout.CENTER);
        panel2=new JPanel();
        panel20=new JPanel();
        ButtonGroup group2 = new ButtonGroup();
        j1=new JRadioButton("二级考试");
        panel20.add(j1);
        j2=new JRadioButton("三级考试");
        panel20.add(j2);
        group2.add(j1);
        group2.add(j2);
        panel2.add(panel20,BorderLayout.CENTER);
        panel2.add(buttonRegister,BorderLayout.SOUTH);
        add(panel2,BorderLayout.SOUTH);
        buttonRegister.addActionListener((this));
        //一名用户只能报名一次考试
    }
    public void setRegisterForExam(Login login){
        this.registerForExam.setLogin(login);
    }
    public void actionPerformed(ActionEvent e) {//点击按钮后执行的事件
        if(!registerForExam.login.getProvince().equals("")){//相当于实现了一名考试只能报一门考试的功能
            JOptionPane.showMessageDialog(null,"您已经报名,无法再次报名,请认真准备考试","提示",JOptionPane.WARNING_MESSAGE);
        }
        else {
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
           info = "";
            for (Component c : this.panel20.getComponents()) {//获取用户选择的等级
                if (c instanceof JRadioButton) {
                    if (((JRadioButton) c).isSelected()) {
                        info += ((JRadioButton) c).getText();
                    }
                }
            }
            if(info.equals("二级考试")){
                registerForExam.setToLevel(2);
            }
            else if(info.equals("三级考试")){
                registerForExam.setToLevel(3);
            }
            int p = registerForExam.getNumber();
        HandleRFE handleRFE = new HandleRFE();
        handleRFE.updateRFE(registerForExam);
        setVisible(false);// 本窗口隐藏,
        this.dispose();
        }
    }
}
