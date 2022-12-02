import java.sql.*;
import java.util.Scanner;

public class account
{
    public void displayAccount()
    {
        Scanner obj = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            Statement stmt = con.createStatement();
            System.out.println("Enter your customer I'd : ");
            long accNum = obj.nextLong();
            System.out.println();
            System.out.println("your account details are displayed below : ");
            String query = "select * from account where cID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,accNum);
            ResultSet rs = ps.executeQuery();
            System.out.println();
            System.out.format("%5s %5s %10s %15s %10s %10s","AccountNumber","AccountType","AccountOpenDate","Balance","IFSCCode","cId");
            System.out.println();
            while (rs.next()) {
                System.out.format("%5s %10s %15s %15s %14s %9s",rs.getLong(1) + " ", rs.getInt(2)+" ",rs.getString(3)+" ",rs.getInt(4) + " ",rs.getInt(5)+" ",rs.getInt(6));
            }
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}