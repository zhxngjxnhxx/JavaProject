package pro.admin.query;

import javax.swing.*;
import java.awt.*;

public class Mainquery extends JPanel {
    JTabbedPane p;
    Generalquery ckv;
    ConditionQuery cq;
    public Mainquery() {
        ckv=new Generalquery();
        cq=new ConditionQuery();
        setLayout(new GridLayout());
        p = new JTabbedPane();
        p.add("通用查询", ckv);
        p.add("条件查询",cq);
        p.validate();
        setVisible(true);
        add(p);
    }

}
