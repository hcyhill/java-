import java.sql.*;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);
    private static Connection dbCon = Connect.getConnection();
    Statement stmt = null;
    ResultSet rs = null;

    public Menu() {

    }

    public void products(String account) throws SQLException {
        stmt = dbCon.createStatement();
        rs = stmt.executeQuery("select * from goods");
        System.out.println("==============================商品表==================================");
        System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t\n", "商品", "價格/數量", "", "價格/重量", "");
        try {

            while (rs.next()) {
                System.out.printf("%-10s\t%10s\t%-10s\t%10s\t%-10s\t\n", rs.getString("gName"),
                        rs.getString("gQuantity"), "元/個", rs.getString("gWeight"), "元/克");
            }
            System.out.println("======================================================================");

        } catch (SQLException e) {

        }
    }

    public void buy(String account) throws SQLException {
        rs = stmt.executeQuery("select * from goods");
        System.out.print("選擇商品 : ");
        int num;
        String goodsName = input.next();
        Goods g = new Goods();
        try {
            while (rs.next()) {
                if (goodsName.equals(rs.getString("gName"))) { // 為什麼不進去if判斷式???????
                    String goodsQuantity = rs.getString("gQuantity");
                    String goodsWeight = rs.getString("gWeight");
                    String goodsWrite = rs.getString("gWrite");
                    boolean q = goodsQuantity.equals("-");
                    boolean w = goodsWeight.equals("-");
                    boolean wr = goodsWrite.equals("TRUE");
                    if (!q && w) {
                        if (wr) {
                            System.out.print("輸入數量 : ");
                            num = input.nextInt();
                            g.quantity(account, goodsName, num, goodsQuantity, goodsWrite);
                            break;
                        } else {
                            System.out.print("輸入數量 : ");
                            num = input.nextInt();
                            g.quantity(account, goodsName, num, goodsQuantity);
                            break;
                        }
                    } else if (q && !w) {
                        if (wr) {
                            System.out.print("輸入重量 : ");
                            num = input.nextInt();
                            g.weight(account, goodsName, num, goodsWeight, goodsWrite);
                            break;
                        } else {
                            System.out.print("輸入重量 : ");
                            num = input.nextInt();
                            g.weight(account, goodsName, num, goodsWeight);
                            break;
                        }
                    } else if (!q && !w) {
                        if (wr) {
                            g.choose(account, goodsName, goodsQuantity, goodsWeight, goodsWrite);
                            break;
                        } else {
                            g.choose(account, goodsName, goodsQuantity, goodsWeight);
                            break;
                        }
                    }
                }else{
                    System.out.println("選擇失敗");
                }
            }
        }
    finally {
            try {
                stmt.close();
            } catch (SQLException error) {
                //System.out.println("選擇失敗");
                error.printStackTrace();
            }
        }

    }

    public int shoppingCart(String account) throws SQLException {
        int money = new ShoppingCart().shoppingCart(account);
        return money;
    }

    public int order(String account) throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = null;
        String orderAccount = null;
        boolean key = true;
        System.out.println("========================");
        System.out.println("訂單編號:");
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
                        return 0;
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
        return 1;
    }

    public void orderlist(String account) throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = null;
        String orderAccount = null;
        String orderID = null;
        int i = 0;

        System.out.println("========================");
        System.out.print("輸入編號:");
        String id = input.next();
        System.out.println("========================");
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
                    System.out.printf("%-10s\t%10s\t\n", "(" + i + ")" + rs.getString("content"),
                            rs.getString("price") + "元");
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

    public void pay(String account, int money) throws SQLException {
        int x = new CheckOut().Amount(money);
        if (x != 0)
            new CheckOut().record(account, x);
    }

    public void addProduct() throws SQLException {
        Statement stmt = dbCon.createStatement();
        System.out.print("商品名稱:");
        String goodsName = input.next();
        System.out.print("商品價格(以數量計算):");
        String goodsQuantity = input.next();
        System.out.print("商品價格(以重量計算):");
        String goodsWeight = input.next();
        System.out.print("客製化文字(true/false):");
        String goodsWrite = input.next();

        try {
            stmt.executeUpdate("insert into goods(gName,gQuantity,gWeight,gWrite) values ('" + goodsName + "','"
                    + goodsQuantity + "','" + goodsWeight + "','" + goodsWrite + "')");
        } finally {
            try {
                stmt.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }

    }

    public void checkingOrder() throws SQLException {
        Statement stmt = dbCon.createStatement();
        ResultSet rs = stmt.executeQuery("select * from orderlist");
        System.out.println("========================");
        System.out.print("訂單編號:");
        String id = input.next();
        System.out.println("========================");
        String orderID = null;
        int i = 0;
        try {
            boolean key = true;
            while (rs.next()) {
                orderID = rs.getString("oID");
                if (id.equals(orderID)) {
                    i++;
                    while (key) {
                        System.out.println("編號 : " + id + "\n" + "姓名 : " + rs.getString("oName") + "\n" + "電話 : "
                                + rs.getString("oAccount") + "\n" + "地址 : " + rs.getString("oAddress"));
                        key = false;
                    }
                    System.out.printf("%-10s\t%10s\t\n", "(" + i + ")" + rs.getString("content"),
                            rs.getString("price") + "元");
                } else {
                    if (rs.isLast() && key) {// 判斷是否為最後一筆資料
                        System.out.println("查無此訂單");
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
}