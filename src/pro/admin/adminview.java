package pro.admin;

import pro.admin.change.Change;
import pro.admin.delete.DeleteView;
import pro.admin.insert.insertview;
import pro.admin.query.ConditionQuery;
import pro.admin.query.Generalquery;
import pro.admin.query.Mainquery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tools.*;
public class adminview extends JFrame implements ActionListener {
    JTabbedPane p;

    public adminview() {
        Mainquery mq =new Mainquery();
        insertview iv =new insertview();
        DeleteView dv=new DeleteView();
        Change chg =new Change();
        setLayout(new GridLayout());
        p = new JTabbedPane();

        p.add("查询", mq);
        p.add("插入", iv);
        p.add("删除", dv);
        p.add("修改", chg);
        p.validate();
        add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        InitGlobalFont.DoInitGlobalFont(new Font("宋体", Font.PLAIN, 18));
        adminview adv=new  adminview();
        adv.setVisible(true);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        adv.setBounds(screenWidth/4, screenHeight/4, screenWidth/3, screenHeight/3);
    }
}
