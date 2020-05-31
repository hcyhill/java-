import java.sql.*;

public class StaffMode extends Mode {
    private static Connection dbCon = Connect.getConnection();

    public StaffMode() {
        System.out.println("======Staff Menu======");
    }

    @Override
    protected void mode(String account) throws SQLException {
        System.out.println("(1)Products\n" + "(2)Shopping Cart\n" + "(3)Tracking Orders\n" + "(4)Add Product\n"
                + "(5)Checking Order\n" + "(6)Exit");
        int a = input.nextInt();
        switch (a) {
            case 1:
                aMenu.products(account);
                break;
            case 2:
                int money = aMenu.shoppingCart(account);
                boolean again = true;
                while (again) {
                    System.out.println("(1)Products\n" + "(2)Checkout\n" + "(3)Back");
                    a = input.nextInt();
                    switch (a) {
                        case 1:
                            aMenu.products(account);
                            break;
                        case 2:
                            aMenu.pay(account,money);
                            break;
                        case 3:
                            new Memberdata().memberLogin(account);
                            break;
                        default:
                            System.out.println("Try Again");
                    }
                }
                break;
            case 3:
                System.out.println("訂單編號:");
                aMenu.order(account);
                System.out.println("========================");
                System.out.print("輸入編號:");
                String id = input.next();
                System.out.println("========================");
                aMenu.orderlist(account,id);
                break;
            case 4:
                addProduct();
                break;
            case 5:
                checkingOrder();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Try Again");
                mode(account);

        }

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
        System.out.print("訂單編號:");
        String id = input.next();
        String orderID = null;
        try {
            boolean key = true;
            while (rs.next()) {
                orderID = rs.getString("oID");
                if (id.equals(orderID)) {
                    while (key) {
                        System.out.println("姓名 : " + rs.getString("oName") + "\n" + "電話 : " + rs.getString("oAccount")
                                + "\n" + "地址 : " + rs.getString("oAddress"));
                        key = false;
                    }
                    System.out.println(rs.getString("content") + rs.getString("price"));
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