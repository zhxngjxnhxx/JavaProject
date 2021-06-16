import tools.GetDBConnection;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        //Cest.Cest();
        //查询
//        Check.check();
        //修改

        //插入8
//        Insert.Insert();
        //删除
//        Delete.delete();8
        Change.change();
    }
}

class Insert {
    //增
    public static void Insert() {
        Connection con;
        PreparedStatement sql;
        ResultSet rs;
        con = GetDBConnection.connectionDB("students", "root", "0617");
        System.out.print("原来");
        Count.count();
        if (con == null) return;
        String jilu = "('R14','麦克','1987-10-13',1.97)," + "('R15','杰克','1984-6-22',1.96)";
        String sqlStr = "insert into mess values" + jilu;//插入
        try {
            sql = con.prepareStatement(sqlStr);
            int ok = sql.executeUpdate(sqlStr);//插入
            //显示结果
            System.out.println("插入成功");
//            rs = sql.executeQuery("select * from mess");
//            while (rs.next()) {
//                String number = rs.getString(1);
//                String name = rs.getString(2);
//                Date date = rs.getDate(3);
//                float height = rs.getFloat(4);
//                System.out.printf("%s\t", number);
//                System.out.printf("%s\t", name);
//                System.out.printf("%s\t", date);
//                System.out.printf("%s\n", height);
//            }
            Check.check();
            con.close();
        } catch (SQLException e) {
            System.out.println("记录不能重复" + e);
        }
    }
}

class Delete {
    public static void delete() {
        Connection con;
        PreparedStatement sql;
        ResultSet rs;
        con = GetDBConnection.connectionDB("students", "root", "0617");
        if (con == null) return;
        Count.count();
        String sqlStr = "delete from mess where number='R11' ";//删除
        try {
            sql = con.prepareStatement(sqlStr);
            int ok = sql.executeUpdate(sqlStr);
            //先查找
            System.out.println("删除成功");
            //显示结果
            Check.check();
            con.close();
        } catch (SQLException e) {
            System.out.println("记录不存在" + e);
        }
    }
}

class Check {
    public static void check() {
        //查询 可以使用通配符
        Connection con;
        PreparedStatement sql;
        ResultSet rs;
        con = GetDBConnection.connectionDB("students", "root", "0617");
//        String c1 = " year(birthday)<=2001";//引号后必须有空格
//        String c2 = " name Like '张_%'";
//        String c3 = " height >1.65";
//        String sqlStr = "select * from mess where" + c1 + " and " + c2 + " and " + c3 + "order by birthday";
        String sqlStr = "SELECT * FROM mess";
        if (con == null) return;
        try {
            sql = con.prepareStatement(sqlStr, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //rs=sql.executeQuery("SELECT * FROM mess");
            rs = sql.executeQuery(sqlStr);
            rs.last();
            int max = rs.getRow();
            System.out.println("共有" + max + "条记录");
            //System.out.println("共有" + max + "条记录,随机抽取两条");
//            int[] a = tools.GetRandomNumber.getRandomNumber(max, 2);
//            for (int i : a) {
//                rs.absolute(i);
//                String number = rs.getString(1);
//                String name = rs.getString(2);
//                Date date = rs.getDate(3);
//                float height = rs.getFloat(4);
//                System.out.printf("%s\t", number);
//                System.out.printf("%s\t", name);
//                System.out.printf("%s\t", date);
//                System.out.printf("%s\n", height);
//            }
            rs.beforeFirst();
            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);
                float height = rs.getFloat(4);
                System.out.printf("%s\t", number);
                System.out.printf("%s\t", name);
                System.out.printf("%s\t", date);
                System.out.printf("%s\n", height);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

class Count {
    public static void count() {
        Connection con;
        Statement sql;
        ResultSet rs;
        con = GetDBConnection.connectionDB("students", "root", "0617");
        String sqlStr = "SELECT * FROM mess";
        if (con == null) return;
        try {
            sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery(sqlStr);
            rs.last();
            int max = rs.getRow();
            System.out.println("共有" + max + "条记录");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

class Change {
    public static void change() {
        Connection con;
        PreparedStatement sql;
        ResultSet rs;
        String tablename="";
        String value="";
        con = GetDBConnection.connectionDB("students", "root", "0617");
        if (con == null) return;
        Count.count();
        String sqlStr_general = "update "+tablename+" set name='杰克' where name='杰克逊' ";//修改名字
        String sqlStr = "update  mess set name='杰克' where name='杰克逊' ";//修改名字
        try {
            sql = con.prepareStatement(sqlStr);
            int ok = sql.executeUpdate(sqlStr);
            //先查找
            System.out.println(" 修改成功");
            //显示结果
            Check.check();
            con.close();
        } catch (SQLException e) {
            System.out.println("记录不存在" + e);
        }
    }
}
