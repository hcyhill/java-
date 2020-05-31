import java.sql.SQLException;

public class ConsumerMode extends Mode {
    public ConsumerMode() {
        System.out.println("======Consumer Menu======");
    }

    @Override
    protected void mode(String account) throws SQLException {
        System.out.println("(1)Products\n" + "(2)Shopping Cart\n" + "(3)Tracking Orders\n" + "(4)Exit");
        int a = input.nextInt();
        switch (a) {
            case 1:
                aMenu.products(account);
                break;
            case 2:
                int money = aMenu.shoppingCart(account);
                boolean again = true;
                while (again) {
                    System.out.println("===================================================================");
                    System.out.println("(1)Products\n" + "(2)Checkout\n" + "(3)Back");
                    a = input.nextInt();
                    switch (a) {
                        case 1:
                            aMenu.products(account);
                            again = false;
                            break;
                        case 2:
                            aMenu.pay(account,money);
                            again = false;
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
                aMenu.orderlist(account, id);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Try Again");
                mode(account);

        }

    }

}