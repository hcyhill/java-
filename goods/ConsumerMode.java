import java.sql.SQLException;

public class ConsumerMode extends Mode {
    public ConsumerMode() {
    }

    @Override
    protected void mode(String account) throws SQLException {
        System.out.println("======Consumer Menu======");
        System.out.println("(1)Products\n" + "(2)Shopping Cart\n" + "(3)Tracking Orders\n" + "(4)Exit");
        int a = input.nextInt();
        switch (a) {
            case 1:
                aMenu.products(account);
                System.out.println("(1)Buy\n" + "(2)Back");
                a = input.nextInt();
                switch (a) {
                    case 1:
                        aMenu.buy(account);
                    case 2:
                        mode(account);
                        break;
                }
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
                            aMenu.pay(account, money);
                            again = false;
                            break;
                        case 3:
                            mode(account);
                            break;
                        default:
                            System.out.println("Try Again");
                    }
                }
                break;
            case 3:
                int x = aMenu.order(account);
                if (x == 1)
                    aMenu.orderlist(account);
                mode(account);
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