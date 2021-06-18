package pro.admin.query;
import tools.GetDBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ConditionQuery extends JPanel implements ActionListener {
    JRadioButton jRb1;//按学号查找
    JRadioButton jRb2;//按省份查找
    JTextField jtf;
    JPanel jPanel0;
    JPanel jPanel1;
    JPanel jPanel3;
    Connection con;
    String [] tableHead;
    String [][]content;//通用查询
    JTable table;
    Query findrecord=new Query();
    int flag=0;
    public ConditionQuery() {
        table = new JTable();
        findrecord = new Query();
        setLayout(new GridLayout(1,1));

        jRb1 = new JRadioButton("按学号查找");
        jRb2 = new JRadioButton("按省份查找");
        ButtonGroup group = new ButtonGroup();
        group.add(jRb1);
        group.add(jRb2);
        jPanel0=new JPanel();
        jPanel0.add(jRb1);
        jPanel0.add(jRb2);
        jPanel1 = new JPanel();
        JLabel jl = new JLabel("检索字段:");
        jPanel1.add(jl);
        jtf = new JTextField(10);
        jPanel1.add(jtf);
        JButton jb = new JButton("查找");
        jPanel3=new JPanel();
        jPanel3.setLayout(new GridLayout(4,1));
        jPanel3.add(jPanel0);
        jPanel3.add(jPanel1);
        JPanel jPanel4=new JPanel();
        jPanel4.setLayout(new FlowLayout());
        jPanel4.add(jb);
        jPanel3.add(jPanel4);
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL("select * from province_info");
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
//        table =new JTable(content,tableHead);
        table.setEnabled(false);
        add(table);
        add(jPanel3);

        jb.addActionListener(this);//添加监听事件
        setVisible(true);

    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        ConditionQuery cq = new ConditionQuery();
        jFrame.add(cq);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //先获取用户选择了哪个条件,再获取Text框中用户输入的数据
        String info = "";
        String new_info="";
        for (Component c : this.jPanel0.getComponents()) {//获取用户选择的省份
            if (c instanceof JRadioButton) {
                if (((JRadioButton) c).isSelected()) {
                    info += ((JRadioButton) c).getText();
                    if(info.equals("按省份查找")){
                        new_info+="province";
                    }
                    else if(info.equals("按学号查找")){
                        new_info+="id";
                    }
                }
            }
        }
        con = GetDBConnection.connectionDB("ncre", "root", "0617");
        //如果没选 要进行相应处理
        String sqlStr = "select * from user where " + new_info + " = '" + jtf.getText()+"'";//省份要有单引号
        findrecord.setDatabaseName("ncre");
        findrecord.setSQL(sqlStr);
        content= findrecord.getRecord();//记录二维的
        tableHead= findrecord.getColumnName();//表头
        TableModel tableModel=new DefaultTableModel(content,tableHead);
        //				将TableModel对象传入Table表格
        table.setModel(tableModel);
        table.setEnabled(false);
        table.validate();
        table.updateUI();


    }
}
