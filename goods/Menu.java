import java.sql.*;

public class Menu {
    private static Connection dbCon = Connect.getConnection();

    public Menu() {

    }

    public void products(String account) throws SQLException {
        new Goods(account);
    }

    public int shoppingCart(String account) throws SQLException {
        int money = new ShoppingCart().shoppingCart(account);
        return money;
    }

    public void order(String account) throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = null;
        String orderAccount = null;
        boolean key = true;
        try {
            rs = stmt.executeQuery("select * from ordersearch");
            while (rs.next()) {
                orderAccount = rs.getString("account");
                if (account.equals(orderAccount)) {
                    System.out.println(rs.getString("search"));
                    key = false;
                } else {
                    if (rs.isLast() && key) {// 判斷是否為最後一筆資料
                        System.out.println("查無資料");
                        break;
                    }
                }
            }

        } finally {
            try {
                stmt.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    public void orderlist(String account, String id) throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = null;
        String orderAccount = null;
        String orderID = null;
        int i = 0;
        try {
            rs = stmt.executeQuery("select * from orderlist");
            boolean key = true;
            while (rs.next()) {
                orderAccount = rs.getString("oAccount");
                orderID = rs.getString("oID");
                if (account.equals(orderAccount) && id.equals(orderID)) {
                    i++;
                    while (key) {
                        System.out.println("編號 : " + rs.getString("oID") + "\n" + "姓名 : " + rs.getString("oName") + "\n"
                                + "電話 : " + rs.getString("oAccount") + "\n" + "地址 : " + rs.getString("oAddress"));
                        key = false;
                    }
                    System.out.printf("%-10s\t%10s\t\n", "(" + i + ")"+rs.getString("content"),rs.getString("price")+"元");
                }
            }

        } finally {
            try {
                stmt.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    public void pay(String account,int money) throws SQLException {
       int x = new CheckOut().Amount(money);
       new CheckOut().record(account,x);

    }

}