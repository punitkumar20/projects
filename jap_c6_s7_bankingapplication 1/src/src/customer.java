import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class customer
{
    public void displayCustomer()
    {
        Scanner obj = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            Statement stmt = con.createStatement();
            System.out.println("Enter your customer I'd : ");
            int customerId = obj.nextInt();
            String query = "select * from customer where cID = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,customerId);
            ResultSet rs = ps.executeQuery();
            System.out.println("************** Customer Details *************");
            System.out.println("--------------------------------------------------------------------");
            System.out.println();
            System.out.format("%3s %8s %10s %10s %15s","cID","Name","Age","Address","AadharNumber");
            System.out.println();
            while (rs.next()) {
                System.out.format("%3s %8s %10s %10s %15s",rs.getInt(1)+" ",rs.getString(2)+ " ",rs.getInt(3)+" ",rs.getString(4) + " " , rs.getInt(5));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addValuesintoCustomer()
    {
        accountType act = new accountType();
        Scanner scn = new Scanner(System.in);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            System.out.println("Please enter your details below::");
            System.out.println("Enter your age");
            int age = scn.nextInt();
            if (age>=18) {
                System.out.println("Enter your Name");
                String name = scn.next();
                System.out.println("Enter your Address");
                String address = scn.next();
                System.out.println("Enter Aadhar number");
                long adhaar = scn.nextLong();
                String query = "insert into customer (Name,age,address,Aadhar) values(?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, address);
                ps.setLong(4, adhaar);
                int check = ps.executeUpdate();
                System.out.println();
                System.out.println("What type of account you wants to open ? ");
                act.displayAccountType();
                System.out.println();
                int accType = scn.nextInt();
                Random rn = new Random();
                long accNo = rn.nextLong(10000000000l, 999999999999l);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate accD = LocalDate.now();
                String accDate=dtf.format(accD);
                int balance = 0;
                int ifsc = 10010;
                String q2="insert into account (Account_no,Account_type,Account_open_date,balance, ifsc_code) values(?,?,?,?,?);";
                PreparedStatement ps2 = con.prepareStatement(q2);
                ps2.setLong(1, accNo);
                ps2.setInt(2, accType);
                ps2.setString(3, accDate);
                ps2.setInt(4, balance);
                ps2.setInt(5, ifsc );
                int check2 = ps2.executeUpdate();
                System.out.println("Your Account has been successfully created and your account number is "+accNo);

            }
            else
            {
                System.out.println("Your age is less than 18, not eligible to open account.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}