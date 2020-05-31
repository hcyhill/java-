import java.sql.SQLException;
import java.util.Scanner;
public abstract class Mode {
    Menu aMenu = new Menu();
    Scanner input = new Scanner(System.in);
    
    public Mode() {

    }
    protected abstract void mode(String id) throws SQLException;

}