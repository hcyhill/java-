import java.util.Scanner;
import java.sql.*;

public class ShoppingCart {
    Scanner input = new Scanner(System.in);
    private static Connection dbCon = Connect.getConnection();// 連接數據庫
   
    public ShoppingCart() {
	}

	public int shoppingCart(String account) throws SQLException{
        int sum = 0;
        Statement stmt = dbCon.createStatement();
        ResultSet rs = stmt.executeQuery("select * from shoppingcart");
        System.out.println("==============================購物車==================================");
        try {
            while (rs.next()) {
                if (account.equals(rs.getString("account"))){
                    System.out.printf("%-10s\t%10s\t%-10s\t%10s\t%-10s\t\n", rs.getString("goods"),
                        rs.getString("num"), "個(克)", rs.getString("price"), "元");
                        sum += Integer.valueOf(rs.getString("price"));
                }
            }
            System.out.println("\t\t\t\t\t\t\t金額 : " + sum);
        } finally {
            
            try {
                stmt.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return sum;
    }
}