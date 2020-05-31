import java.util.Scanner;
import java.sql.*;

public class Goods {
    Scanner input = new Scanner(System.in);
    private static Connection dbCon = Connect.getConnection();// 連接數據庫
    Statement stmt = dbCon.createStatement();
    ResultSet rs = null;

    public Goods() throws SQLException {

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
                    System.out.println("輸入重量 : ");
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
                    System.out.println("輸入重量 : ");
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