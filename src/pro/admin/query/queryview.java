package pro.admin.query;

import javax.swing.*;
import java.awt.*;

public class queryview extends JPanel{//查询 应该有通用查询 条件查询
    String [] tableHead;
    String [][]content;//通用查询
    JTable table;
    Query findrecord=new Query();
    public queryview(){
        setLayout(new GridLayout());
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
        table =new JTable(content,tableHead);
        table.setEnabled(false);
        add(table);
        //窗口居中
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);

        int size=14;
        table.setFont(new Font("Serif",Font.PLAIN,size));
        setVisible(true);


    }

}
