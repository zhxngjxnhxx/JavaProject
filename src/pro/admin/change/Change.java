package pro.admin.change;

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
//修改先检查有没有该记录
public class Change extends JPanel implements ActionListener{

    JButton b0,b1,b2;
    Panel cardPanel = new Panel();
    Panel controlpaPanel = new Panel();
    JPanel jPanel1,jPanel2;
    int flag=1;
    //定义卡片布局对象
    CardLayout card = new CardLayout();
    //定义字符数组，为卡片命名
    String cardName[]={"修改用户信息","修改考试信息"};
    Connection con;
    JTextField idtext,new_id,new_password,provincetext,new_province;
    //定义构造函数
    public Change() {

        setLayout(new BorderLayout());
        setSize(400, 300);
        setVisible(true);

        //设置cardpanel面板为卡片布局
        cardPanel.setLayout(card);
        //面板1
        jPanel1 =new JPanel();
        jPanel1.setLayout(new GridLayout(4,1));

        JPanel jPanel10=new JPanel();
        jPanel10.setLayout(new FlowLayout());
        jPanel10.add(new JLabel("原学号:"));
        idtext=new JTextField(10);
        jPanel10.add(idtext);

        JPanel jPanel11=new JPanel();
        jPanel11.setLayout(new FlowLayout());
        jPanel11.add(new JLabel("新学号:"));
        new_id=new JTextField(10);
        jPanel11.add(new_id);

        JPanel jPanel12=new JPanel();
        jPanel12.setLayout(new FlowLayout());
        jPanel12.add(new JLabel("新密码:"));
        new_password=new JTextField(10);
        jPanel12.add(new_password);

        jPanel1.add(jPanel10);
        jPanel1.add(jPanel11);
        jPanel1.add(jPanel12);
        cardPanel.add(cardName[0],jPanel1);

        jPanel2 =new JPanel();
        jPanel2.setLayout(new GridLayout(2,1));
        JPanel jPanel20=new JPanel();
        jPanel20.setLayout(new FlowLayout());
        jPanel20.add(new JLabel("旧省份名:"));
        provincetext=new JTextField(10);
        jPanel20.add(provincetext);

        JPanel jPanel21=new JPanel();
        jPanel21.setLayout(new FlowLayout());
        jPanel21.add(new JLabel("新省份名:"));
        new_province=new JTextField(10);
        jPanel21.add(new_province);

        jPanel2.add(jPanel20);
        jPanel2.add(jPanel21);

        cardPanel.add(cardName[1],jPanel2);
        //实例化按钮对象
        b0=new JButton("修改用户信息");
        b1=new JButton("修改考试信息");
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
        b2=new JButton("修改");
        b2.addActionListener(this);
        add(b2,BorderLayout.SOUTH);
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
            if(flag==1){
                con = GetDBConnection.connectionDB("ncre", "root", "0617");
                PreparedStatement presql = null;
                ResultSet rs;
                if (con == null) {
                    return;
                }
                String sqlstr = "update  user set id = ?,password =? where id='"+idtext.getText()+"' ";
                try {
                    presql = con.prepareStatement(sqlstr);
                    presql.setString(1, new_id.getText());
                    presql.setString(2,new_password.getText());
                    int ok = presql.executeUpdate();
                    JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.WARNING_MESSAGE);
                    con.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "修改失败", "修改失败", JOptionPane.WARNING_MESSAGE);

                }

            }
            if(flag==2){
                con = GetDBConnection.connectionDB("ncre", "root", "0617");
                PreparedStatement presql = null;
                ResultSet rs;
                if (con == null) {
                    return;
                }
                String sqlstr = "update  province_info set province = ? where province='"+provincetext.getText()+"' ";
                try {
                    presql = con.prepareStatement(sqlstr);
                    presql.setString(1, new_province.getText());
//                presql.setString(2, pwtext.getText());
                    int ok = presql.executeUpdate();
                    JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.WARNING_MESSAGE);
                    con.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "修改失败", "修改失败", JOptionPane.WARNING_MESSAGE);

                }
            }
        }


    }

    public static void main(String[] args) {
//        JFrame jFrame=new JFrame();
//        Change kapian = new Change();
//        jFrame.add(kapian);
//        jFrame.setVisible(true);
        Connection con;
        con=GetDBConnection.connectionDB("ncre","root","0617");
        String sqlstr = "update user set id = ?,password =? where id='"+"130"+"' ";
        try {
            PreparedStatement presql = con.prepareStatement(sqlstr);
            presql.setString(1, "133");
            presql.setString(2,"1");
            int ok = presql.executeUpdate();
            JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.WARNING_MESSAGE);
            con.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "修改失败", "修改失败", JOptionPane.WARNING_MESSAGE);
            System.out.println(sqlException);
        }

    }

}