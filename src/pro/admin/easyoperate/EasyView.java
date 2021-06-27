package pro.admin.easyoperate;

import pro.admin.query.ConditionQuery;
import pro.admin.query.Generalquery;

import javax.swing.*;
import java.awt.*;

public class EasyView extends JPanel
{
    JTabbedPane p;
    public EasyView()
    {
        setLayout(new GridLayout());
        p = new JTabbedPane();
        p.add("用户信息", new EasyOperate(1));
        p.add("省份信息", new EasyOperate(2));
        p.validate();
        setVisible(true);
        add(p);
    }
}
