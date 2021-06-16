import pro.admin.query.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//public class GeneralQuery2 {
//    public static void main(String[] args) {
//        String [] tableHead;
//        String [][]content;
//        JTable table;
//        JFrame win =new JFrame();
//        View.Query findrecord=new View.Query();
//        findrecord.setDatabaseName("ncre");
//        findrecord.setSQL("select * from province_info");
//        content= findrecord.getRecord();//记录二维的
//        tableHead= findrecord.getColumnName();//表头
//        table =new JTable(content,"省份");
//        table.setEnabled(false);
//        win.add(new JScrollPane(table));
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
//        //窗口居中
//        win.setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);
//        win.pack();//自适应调整
//        int size=14;
//        table.setFont(new Font("Serif",Font.PLAIN,size));
//        win.setVisible(true);
//
//        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//
//
//    }
//}
class MyRadio {
//    private JFrame jFrame = new JFrame("选择省份");// 定义一个窗体
//    private Container container = jFrame.getContentPane();// 得到窗体容器
//    private JPanel panel = new JPanel();// /定义一个面板
    public MyRadio() {
        String [][]content;
        JFrame win =new JFrame();
        Container container = win.getContentPane();
        JPanel panel = new JPanel();
        Query findrecord=new Query();
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        ButtonGroup group = new ButtonGroup();
        for (int i =0;i<content.length;i++){
            JRadioButton jbi=new JRadioButton(content[i][0]);
            panel.add(jbi);
            group.add(jbi);
        }
        panel.setBorder(BorderFactory.createTitledBorder("请选择省份"));// 定义一个面板的边框显示条
        panel.setLayout(new GridLayout( (int)Math.ceil(content.length/3), 3));// 定义排版，n行三列
        container.add(panel);// 加入面板
        win.setBounds(200,200,530, 480);// 设置窗体大小
        win.setVisible(true);// 显示窗体
        win.addWindowListener(new WindowAdapter() { // 加入事件监听
            public void windowClosing(WindowEvent arg0) { // 复写窗口关闭的方法
                System.exit(1);// 系统退出
            }
        });

    }
}

public class GeneralQuery2 {
    public static void main(String[] args) {
        new MyRadio();
    }
}