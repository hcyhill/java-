import java.util.Scanner;
import java.sql.SQLException;

public class Login {

    Scanner input = new Scanner(System.in);

    public Login() {

    }

    public void loginMenu() throws SQLException {
        System.out.println("=====Good Life Foods=====\n" + "(1)Log In\n" + "(2)Sign Up\n" +"(3)Exit");
        int a = input.nextInt();
        switch (a) {
            case 1:
                log();
                break;
            case 2:
                sign();
                System.out.println("(1)Log In\n" + "Press any key to end\n");
                a = input.nextInt();
                if (a == 1)
                    log();
                else
                    System.exit(0);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Try Again");
                loginMenu();

        }
    }

    public void log() throws SQLException {
        System.out.println("============Log In============");
        System.out.print("Account(phone number) : ");
        String aAccount = input.next();
        System.out.print("Password : ");
        String aPassword = input.next();
        new Memberdata().memberLogin(aAccount, aPassword);

    }

    public void sign() throws SQLException {
        System.out.println("============Sign up============");
        String privilege = "false";
        boolean key = true;
        while (key) {
            System.out.print("Name : ");
            String bName = input.next();
            System.out.print("Account(phone number) : ");
            String bAccount = input.next();
            System.out.print("Password : ");
            String bPassword = input.next();
            System.out.print("Comfirm Password : ");
            String cPassword = input.next();
            if (!bAccount.startsWith("09"))
                System.out.println("Sign-up Failed");
            else if (bAccount.length() != 10)
                System.out.println("Sign-up Failed");
            else if (!bPassword.equals(cPassword))
                System.out.println("Sign-up Failed");
            else {
                new Memberdata().memberSignup(bName, bAccount, bPassword, privilege);
                key = false;
            }
        }
    }
}