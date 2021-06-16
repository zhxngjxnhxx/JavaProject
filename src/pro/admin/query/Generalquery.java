package pro.admin.query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//还要查考生信息??
//应该把通用查询抽象出一个接口
public class Generalquery extends JPanel implements ActionListener{//查询 应该有通用查询 条件查询
    String [] tableHead;
    String [][]content;//通用查询
    JTable table;
    JButton b1;
    Query findrecord=new Query();
    public Generalquery(){
        setLayout(new BorderLayout());
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
        table =new JTable(content,tableHead);
        table.setEnabled(false);
        add(table);
        JScrollPane scrollpane=new JScrollPane(table);
        add(scrollpane,BorderLayout.CENTER);
        //窗口居中
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);
        int size=16;
        table.setFont(new Font("Serif",Font.PLAIN,size));
        setVisible(true);
        b1=new JButton("查询");
        add(b1,BorderLayout.SOUTH);
        b1.addActionListener(this);
    }
    public static void getQuery(String dbname,String tablename){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setLayout(new BorderLayout());
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
        table =new JTable(content,tableHead);
        table.setEnabled(false);
        table.validate();
        table.updateUI();
    }
}
