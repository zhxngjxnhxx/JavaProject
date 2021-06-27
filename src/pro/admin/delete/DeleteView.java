package pro.admin.delete;


import pro.admin.query.Query;
import tools.GetDBConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

//定义类的时候实现监听接口

public class DeleteView extends JPanel implements ActionListener{

    JButton b0,b1,b2;
    Panel cardPanel = new Panel();
    Panel controlpaPanel = new Panel();
    JPanel jPanel1,jPanel2;
    JTextField idtext,provincetext;
    //定义卡片布局对象
    CardLayout card = new CardLayout();
    //定义字符数组，为卡片命名
    String cardName[]={"删除用户信息","删除考试信息"};
    int flag=1;
    Connection con;
    //定义构造函数
    public DeleteView() {

        setLayout(new BorderLayout());
        setSize(400, 300);
        setVisible(true);

        //设置cardpanel面板为卡片布局
        cardPanel.setLayout(card);

        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(3, 1));
        JPanel jPanel10 = new JPanel();
        jPanel10.setLayout(new FlowLayout());
        jPanel10.add(new JLabel("学号:"));
        idtext = new JTextField(10);
        jPanel10.add(idtext);
        jPanel1.add(jPanel10);
        cardPanel.add(cardName[0], jPanel1);

        jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(2, 1));
        JPanel jPanel20 = new JPanel();
        jPanel20.setLayout(new FlowLayout());
        jPanel20.add(new JLabel("省份:"));
        provincetext = new JTextField(10);
        jPanel20.add(provincetext);
        jPanel2.add(jPanel20);
        cardPanel.add(cardName[1], jPanel2);
        //实例化按钮对象
        b0 = new JButton("删除用户信息");
        b1 = new JButton("删除考试信息");
        //为按钮对象注册监听器
        b0.addActionListener(this);
        b1.addActionListener(this);
        controlpaPanel.add(b0);
        controlpaPanel.add(b1);
        //定义容器对象为当前窗体容器对象
//        Container container = getContentPane();
        //将 cardPanel面板放置在窗口边界布局的中间，窗口默认为边界布局
        add(cardPanel, BorderLayout.CENTER);
        // 将controlpaPanel面板放置在窗口边界布局的南边，
        add(controlpaPanel, BorderLayout.NORTH);
        b2 = new JButton("删除");
        add(b2, BorderLayout.SOUTH);
        b2.addActionListener(this);
        setVisible(true);

    }

    //实现按钮的监听触发时的处理
    @Override
    public void actionPerformed(ActionEvent e) {
        //用户单击b0按钮时执行的语句
        if(e.getSource()==b0){
            //通过show()方法中的卡片名称，显示容器中的组件。
            card.show(cardPanel,cardName[0]);
            //对用户表操作
            flag=1;
        }
        if(e.getSource()==b1){
            card.show(cardPanel,cardName[1]);
            flag=2;
        }
        if(e.getSource()==b2){
            if (flag == 1) {
                con = GetDBConnection.connectionDB("ncre", "root", "0617");
                PreparedStatement presql = null;
                ResultSet rs;
                if (con == null) {
                    return;
                }
                Query find =new Query();
                find.setDatabaseName("ncre");
                find.setSQL("select * from user where id='"+idtext.getText()+"' ");
                String [][]str = find.getRecord();
                if(str.length==0){//查找不到记录
                    JOptionPane.showMessageDialog(null, "修改失败,因为该记录不存在", "修改失败", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String sqlstr = "delete from user where id = ?";
                try {
                    presql = con.prepareStatement(sqlstr);
                    presql.setString(1, idtext.getText());
                    int ok = presql.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除成功", "删除成功", JOptionPane.WARNING_MESSAGE);
                    con.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "删除失败", "删除失败", JOptionPane.WARNING_MESSAGE);

                }
            }
            if (flag == 2) {
                con = GetDBConnection.connectionDB("ncre", "root", "0617");
                PreparedStatement presql = null;
                ResultSet rs;
                if (con == null) {
                    return;
                }
                Query find =new Query();
                find.setDatabaseName("ncre");
                find.setSQL("select * from province_info where province ='"+provincetext.getText()+"' ");
                String [][]str = find.getRecord();
                if(str.length==0){//查找不到记录
                    JOptionPane.showMessageDialog(null, "修改失败,因为该记录不存在", "修改失败", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String sqlstr = "delete from province_info where province = ?";
                try {
                    presql = con.prepareStatement(sqlstr);
                    presql.setString(1, provincetext.getText());
//                presql.setString(2, pwtext.getText());
                    int ok = presql.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除成功", "删除成功", JOptionPane.WARNING_MESSAGE);
                    con.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "删除失败", "删除失败", JOptionPane.WARNING_MESSAGE);

                }
            }
        }

    }

//    public static void main(String[] args) {
//        JFrame jFrame=new JFrame();
//        DeleteView kapian = new DeleteView();
//        jFrame.add(kapian);
//        jFrame.setVisible(true);
//    }

}