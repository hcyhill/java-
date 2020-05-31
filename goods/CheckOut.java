import java.sql.*;
import java.util.Scanner;

public class CheckOut {
    Scanner input = new Scanner(System.in);
    private static Connection dbCon = Connect.getConnection();// 連接數據庫

    public CheckOut() {

    }

    public int Amount(int x) {
        System.out.println("==============================結帳==================================");
        if (x < 400) {
            System.out.println("未達最低金額,無法宅配");
            return x;
        } else if (x >= 400 && x < 1200) {
            x = x + 200;
            System.out.println("運費200元");
            System.out.println("總金額 : " + x);
            return x;
        } else
            System.out.println("免運費");
        System.out.println("總金額 : " + x);
        return x;
    }

    public void record(String account, int x) throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = stmt.executeQuery("select * from shoppingcart"); // shoppingcaat table

        String name = null;
        int id = (int) (Math.random() * 99999999);
        try { // id和帳號 => order
            stmt.executeUpdate("insert into ordersearch (search,account) VALUES ('" + id + "','" + account + "')");
        } catch (SQLException e) {
            System.out.println("ordersearch放入失敗");
        }
        try {
            stmt = dbCon.createStatement();
            ResultSet rs3 = stmt.executeQuery("select * from member"); // member table
            while (rs3.next()) {
                if (account.equals(rs3.getString("mAccount"))) {
                    name = rs3.getString("mName");
                }
            }
        } catch (SQLException e) {
        }
        try { // shopping => orderlist
            rs = stmt.executeQuery("select * from shoppingcart");
            boolean key = true;
            String address = null;
            while (rs.next()) {
                if (account.equals(rs.getString("account"))) {
                    while (key) {
                        address = input.next();
                        key = false;
                    }
                    String goods = rs.getString("goods");
                    String price = rs.getString("price");
                    String write = rs.getString("write");
                    stmt.executeUpdate(
                            "insert into orderlist (oID,oName,oAccount,oAddress,content,price,write) VALUES ('" + id
                                    + "','" + name + "','" + account + "','" + address + "','" + goods + "','" + price
                                    + "','" + write + "')");
                    stmt.executeUpdate("delete shoppingcart where account='" + account + "' ");
                }
            }
        } catch (SQLException e) {
            System.out.println("orderlist放入失敗");
        }
    }
}