import java.util.Scanner;
import java.sql.*;

public class Goods {
    Scanner input = new Scanner(System.in);
    private static Connection dbCon = Connect.getConnection();// 連接數據庫
    Statement stmt = dbCon.createStatement();
    ResultSet rs = null;

    public Goods(String account) throws SQLException { // 貨物
        
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
        System.out.println("(1)Buy\n" + "(2)Back");
        int a = input.nextInt();
        switch (a) {
            case 1:
                buy(account);
                /*rs = stmt.executeQuery("select * from goods");
                System.out.print("選擇商品 : ");
                int num;
                String goodsName = input.next();
                try {
                    while (rs.next()) {
                        while (goodsName.equals(rs.getString("gName"))) { // 為什麼不進去if判斷式???????
                            String goodsQuantity = rs.getString("gQuantity");
                            String goodsWeight = rs.getString("gWeight");
                            String goodsWrite = rs.getString("gWrite");
                            boolean storage = true;
                            boolean q = goodsQuantity.equals("-");
                            boolean w = goodsWeight.equals("-");
                            boolean wr = goodsWrite.equals("TRUE");
                            while (storage) {
                                while (!q && w) { // 只有數量
                                    if (wr) {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        quantity(account, goodsName, num, goodsQuantity, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        quantity(account, goodsName, num, goodsQuantity);
                                        storage = false;
                                        break;
                                    }

                                }
                                while (q && !w) { // 只有重量
                                    if (wr) {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        weight(account, goodsName, num, goodsWeight, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        weight(account, goodsName, num, goodsWeight);
                                        storage = false;
                                        break;
                                    }
                                }
                                while (!q && !w) {
                                    if (wr) {
                                        choose(account, goodsName, goodsQuantity, goodsWeight, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        choose(account, goodsName, goodsQuantity, goodsWeight);
                                        storage = false;
                                        break;
                                    }
                                }
                            }
                            Memberdata aMemberdata = new Memberdata();
                            aMemberdata.memberLogin(account);
                        }
                    }
                } finally {
                    try {
                        stmt.close();
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                }*/
                break;
            case 2:
                new Memberdata().memberLogin(account);
                break;
        }

    }

    public void buy(String account) throws SQLException{
        rs = stmt.executeQuery("select * from goods");
                System.out.print("選擇商品 : ");
                int num;
                String goodsName = input.next();
                try {
                    while (rs.next()) {
                        while (goodsName.equals(rs.getString("gName"))) { // 為什麼不進去if判斷式???????
                            String goodsQuantity = rs.getString("gQuantity");
                            String goodsWeight = rs.getString("gWeight");
                            String goodsWrite = rs.getString("gWrite");
                            boolean storage = true;
                            boolean q = goodsQuantity.equals("-");
                            boolean w = goodsWeight.equals("-");
                            boolean wr = goodsWrite.equals("TRUE");
                            while (storage) {
                                while (!q && w) { // 只有數量
                                    if (wr) {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        quantity(account, goodsName, num, goodsQuantity, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        quantity(account, goodsName, num, goodsQuantity);
                                        storage = false;
                                        break;
                                    }

                                }
                                while (q && !w) { // 只有重量
                                    if (wr) {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        weight(account, goodsName, num, goodsWeight, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        System.out.println("輸入數量 : ");
                                        num = input.nextInt();
                                        weight(account, goodsName, num, goodsWeight);
                                        storage = false;
                                        break;
                                    }
                                }
                                while (!q && !w) {
                                    if (wr) {
                                        choose(account, goodsName, goodsQuantity, goodsWeight, goodsWrite);
                                        storage = false;
                                        break;
                                    } else {
                                        choose(account, goodsName, goodsQuantity, goodsWeight);
                                        storage = false;
                                        break;
                                    }
                                }
                            }
                            Memberdata aMemberdata = new Memberdata();
                            aMemberdata.memberLogin(account);
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

    public void choose(String account, String goodsName, String goodsQuantity, String goodsWeight) throws SQLException {
        System.out.println("Please either choose quantity or weight.");
        System.out.println("(1)quantity\n" + "(2)weight\n");
        int num = input.nextInt();
        boolean storage = true;
        while (storage) {
            switch (num) {
                case 1:
                    System.out.println("輸入數量 : ");
                    num = input.nextInt();
                    quantity(account, goodsName, num, goodsQuantity);
                    storage = false;
                    break;
                case 2:
                    System.out.println("輸入數量 : ");
                    num = input.nextInt();
                    weight(account, goodsName, num, goodsWeight);
                    storage = false;
                    break;
            }
        }
    }

    public void choose(String account, String goodsName, String goodsQuantity, String goodsWeight, String goodsWrite)
            throws SQLException {
        System.out.println("Please either choose quantity or weight.");
        System.out.println("(1)quantity\n" + "(2)weight\n");
        int num = input.nextInt();
        boolean storage = true;
        while (storage) {
            switch (num) {
                case 1:
                    System.out.println("輸入數量 : ");
                    num = input.nextInt();
                    quantity(account, goodsName, num, goodsQuantity, goodsWrite);
                    storage = false;
                    break;
                case 2:
                    System.out.println("輸入數量 : ");
                    num = input.nextInt();
                    weight(account, goodsName, num, goodsWeight, goodsWrite);
                    storage = false;
                    break;
            }
        }
    }

    public void quantity(String account, String x, int y, String z) throws SQLException {
        int sum = y * Integer.valueOf(z);
        dbWrite(account, x, y, sum);
    }

    public void quantity(String account, String x, int y, String z, String goodsWrite) throws SQLException {
        int sum = y * Integer.valueOf(z);
        System.out.println("自定義文字: ");
        String s = input.next();
        dbWrite(account, x, y, sum, s);
    }

    public void weight(String account, String x, int y, String z) throws SQLException {
        int sum = y * Integer.valueOf(z);
        dbWrite(account, x, y, sum);
    }

    public void weight(String account, String x, int y, String z, String goodsWrite) throws SQLException {
        int sum = y * Integer.valueOf(z);
        System.out.println("自定義文字: ");
        String s = input.next();
        dbWrite(account, x, y, sum, s);
    }

    public void dbWrite(String account, String x, int y, int sum) throws SQLException {
        Statement stmt = dbCon.createStatement();
        try {
            stmt.executeUpdate("insert into shoppingcart (account,goods,num,price,write) VALUES ('" + account + "','"
                    + x + "','" + y + "','" + sum + "','null')");
            System.out.println("已加入購物車");
        } catch (SQLException e) {
        }
    }

    public void dbWrite(String account, String x, int y, int sum, String s) throws SQLException {
        Statement stmt = dbCon.createStatement();
        try {

            stmt.executeUpdate("insert into shoppingcart (account,goods,num,price,write) VALUES ('" + account + "','"
                    + x + "','" + y + "','" + sum + "','" + s + "')");
            System.out.println("已加入購物車");
        } catch (SQLException e) {
        }
    }
}