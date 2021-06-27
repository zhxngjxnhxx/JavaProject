package pro.admin;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import pro.admin.change.Change;
import pro.admin.delete.DeleteView;
import pro.admin.easyoperate.EasyOperate;
import pro.admin.easyoperate.EasyView;
import pro.admin.insert.insertview;
import pro.admin.query.ConditionQuery;
import pro.admin.query.Generalquery;
import pro.admin.query.Mainquery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import tools.*;
public class AdminView extends JFrame implements ActionListener {
    JTabbedPane p;

    public AdminView () {
        Mainquery mq =new Mainquery();
        insertview iv =new insertview();
        DeleteView dv=new DeleteView();
        Change chg =new Change();
        EasyView ev =new EasyView();
        setLayout(new GridLayout());
        p = new JTabbedPane();


        p.add("查询", mq);
        p.add("插入", iv);
        p.add("删除", dv);
        p.add("修改", chg);
        p.add("可视化操作",ev);
        p.validate();
        add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        }catch(Exception e) {
            System.out.println(e);
        }
        InitGlobalFont.DoInitGlobalFont(new Font("宋体", Font.PLAIN, 18));
        AdminView adv=new AdminView ();
        adv.setVisible(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        adv.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
    }
}
