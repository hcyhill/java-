import java.sql.*;

class Memberdata extends Login {

    private String name;
    private String account;
    private String password;
    private String privilege;
    private static Connection dbCon = Connect.getConnection(); // 連接DB

    public Memberdata() {
        // this.name = "";
        // this.account = "";
    }

    public void memberSignup(String name, String account, String password, String privilege) throws SQLException {
        this.name = name;
        this.account = account;
        this.password = password;
        this.privilege = privilege;
        Statement stmt = null;
        try {
            stmt = dbCon.createStatement();
            System.out.println(this.name);
            stmt.executeUpdate("insert into member (mName,mAccount,mPassword,mPrivilege) VALUES ('" + this.name + "','"
                    + this.account + "','" + this.password + "','" + this.privilege + "')");
            System.out.println("Sign-up Successful");
        } finally {
            try {
                stmt.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    public void memberLogin(String account, String password) throws SQLException {
        this.account = account;
        this.password = password;
        Statement stmt = dbCon.createStatement();
        ResultSet rs = stmt.executeQuery("select * from member");

        try {
            while (rs.next()) {// 從數據庫中，一行一行查找，直到沒有下一筆資料
                if (this.account.equals(rs.getString("mAccount")) && this.password.equals(rs.getString("mPassword"))) {// 判斷是否有登入資料
                    if (rs.getString("mPrivilege") == "FALSE") { // 判斷是否為員工
                        // 消費者模式
                        System.out.println("Login Successful!");
                        ConsumerMode cMode = new ConsumerMode();
                        cMode.mode(this.account);
                        break;
                    } else {
                        // 員工模式
                        System.out.println("Login Successful!");
                        StaffMode sMode = new StaffMode();
                        sMode.mode(this.account);
                        break;
                    }
                } else {
                    if (rs.isLast()) {// 判斷是否為最後一筆資料
                        System.out.println("Unknown User Name or Bad Password !!");
                        System.out.println("========================");
                        System.out.println("(1)Log In\n" + "Press any key to end");
                        int a = input.nextInt();
                        if (a == 1) {
                            log();
                        } else
                            System.exit(0);
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