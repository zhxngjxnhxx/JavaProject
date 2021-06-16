import pro.admin.query.Query;

import javax.swing.*;
import java.awt.*;
public class GeneralQuery {
    public static void main(String[] args) {
        String [] tableHead;
        String [][]content;
        JTable table;
        JFrame win =new JFrame();
        Query findrecord=new Query();
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
        table =new JTable(content,tableHead);
        table.setEnabled(false);
        win.add(new JScrollPane(table));
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //窗口居中
        win.setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);
        win.pack();//自适应调整
        int size=14;
        table.setFont(new Font("Serif",Font.PLAIN,size));
        win.setVisible(true);

        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
