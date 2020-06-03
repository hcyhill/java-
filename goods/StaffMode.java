import java.sql.*;

public class StaffMode extends Mode {
    public StaffMode() {
    }

    @Override
    protected void mode(String account) throws SQLException {
        System.out.println("======Staff Menu======");
        System.out.println("(1)Products\n" + "(2)Shopping Cart\n" + "(3)Tracking Orders\n" + "(4)Add Product\n"
                + "(5)Checking Order\n" + "(6)Exit");
        int a = input.nextInt();
        switch (a) {
            case 1:
                aMenu.products(account);
                buySelect(account);
                break;
            case 2:
                int money = aMenu.shoppingCart(account);
                shopSelect(account,money);
                break;
            case 3:
                int x = aMenu.order(account);
                if (x == 1)
                    aMenu.orderlist(account);
                mode(account);
                break;
            case 4:
                aMenu.addProduct();
                mode(account);
                break;
            case 5:
                aMenu.checkingOrder();
                mode(account);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Try Again");
                mode(account);
        }
    }

    public void buySelect(String account) throws SQLException {
        System.out.println("(1)Buy\n" + "(2)Back");
        int a = input.nextInt();
        switch (a) {
            case 1:
                aMenu.buy(account);
            case 2:
                mode(account);
                break;
        }
    }
    public void shopSelect(String account,int money) throws SQLException{
        boolean again = true;
        while (again) {
            System.out.println("======================================================================");
            System.out.println("(1)Products\n" + "(2)Checkout\n" + "(3)Back");
            int a = input.nextInt();
            switch (a) {
                case 1:
                    aMenu.products(account);
                    buySelect(account);
                    again = false;
                    break;
                case 2:
                    aMenu.pay(account, money);
                    again = false;
                case 3:
                    mode(account);
                    break;
                default:
                    System.out.println("Try Again");
            }
        }
    }
}