package pro.admin.easyoperate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EasyOperate extends JPanel{

    int flag;//标志访问哪一个表 1代表 学生信息 2代表 省份信息
    DefaultTableModel tableModel;		// 默认显示的表格
    JButton add,del,exit,save;		// 各处理按钮
    JTable table;		// 表格

    JPanel panelUP;	//增加信息的面板

    // 构造函数
    public EasyOperate(int flag){
//        this.setBounds(300, 200, 600, 450);		// 设置窗体大小
//        this.setTitle("测试");		// 设置窗体名称
        this.setLayout(new BorderLayout());	// 设置窗体的布局方式
        this.flag=flag;
        // 新建各按钮组件
        add = new JButton("增加");
        del = new JButton("删除");
        save = new JButton("保存");
        exit = new JButton("退出");

        panelUP = new JPanel();		// 新建按钮组件面板
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式

        // 将各按钮组件依次添加到面板中
        panelUP.add(add);
        panelUP.add(del);
        panelUP.add(save);
        panelUP.add(exit);

        // 取得数据库的表的各行数据
        Vector rowData = PutinStorage.getRows(this.flag);//在此决定是哪个表
        // 取得数据库的表的表头数据
        Vector columnNames = PutinStorage.getHead(this.flag);//在此决定是哪个表


        // 新建表格
        tableModel = new DefaultTableModel(rowData,columnNames);
        table = new JTable(tableModel);

        JScrollPane s = new JScrollPane(table);

        // 将面板和表格分别添加到窗体中
        this.add(panelUP,BorderLayout.NORTH);
        this.add(s);

        // 事件处理
        MyEvent();

        this.setVisible(true);		// 显示窗体
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 // 设置窗体可关闭
    }

    // 事件处理
    public void MyEvent(){

        // 增加
        add.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 增加一行空白区域
                tableModel.addRow(new Vector());
            }

        });

        // 删除
        del.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // 删除指定行
                int rowcount = table.getSelectedRow();
                if(rowcount >= 0){
                    tableModel.removeRow(rowcount);
                }
            }

        });

        /**
         * 保存
         * 解决办法是直接将表中的全部数据删除，
         * 将表格中的所有内容获取到,
         * 然后将表格数据重新写入表
         */
        save.addActionListener(new ActionListener(){//点击保存

            @Override
            public void actionPerformed(ActionEvent e) {
                int column = table.getColumnCount();		// 表格列数
                int row = table.getRowCount();		// 表格行数

                // value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                    for (int i = 0; i < row; i++)
                    {
                        for (int j = 0; j < column; j++)
                        {
                            try//数据处理
                            {
                            value[i][j] = table.getValueAt(i, j).toString();
                            }
                            catch ( Exception e2){
                                JOptionPane.showMessageDialog(null,"请重新检查数据是否格式错误","失败",JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                    }

                }

                // 以下均为对数据库的操作
                String sql_url = "jdbc:mysql://localhost:3306/ncre";
                String name = "root";		//用户名
                String password = "0617";	//密码
                Connection conn;
                PreparedStatement preparedStatement = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");		//连接驱动
                    conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
                    if(!conn.isClosed())
                        System.out.println("成功连接数据库");

                    // 删除表中所有数据
                    String  str="";
                    if(flag==1)preparedStatement = conn.prepareStatement("delete from user_copy where true");//删除所有的内容
                    else if(flag==2) preparedStatement = conn.prepareStatement("delete from province_info_copy where true");
                    preparedStatement.executeUpdate();

                    // 将value数组中的数据依次存放到表中
                    for(int i = 0; i < row; i++){
                        //要改的
                       //不同的表 sql语句不同
                        String sqlstr="";
                        if(flag==1) sqlstr="insert into user_copy values('" + value[i][0] + "','" + value[i][1] +"','" + value[i][2]+"','" + value[i][3]+"'," + value[i][4] + ")";
                        else if(flag==2)  sqlstr="insert into province_info_copy values('" + value[i][0] + "'," + value[i][1] + "," + value[i][2] + ")";

                        //                        String sqlstr ="insert into user_test values('123','13215', '2021-06-18' ,'hns',0)";
                        System.out.println(sqlstr);
                        preparedStatement = conn.prepareStatement(sqlstr);
                        preparedStatement.executeUpdate();
                    }

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("未成功加载驱动。");
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("未成功打开数据库。");
//                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,"请重新检查数据是否格式错误，可能部分数据丢失","意外中断",JOptionPane.WARNING_MESSAGE);
                    return;

                }

                // 保存后退出
//                System.exit(0);
            }
        });

        // 退出
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }

        });
    }

}
