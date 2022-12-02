import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class transaction
{
    public void displayTransaction()
    {
        Scanner obj = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            Statement stmt = con.createStatement();
            System.out.println("Enter your Account number : ");
            long accountno = obj.nextLong();
            String query = "select * from transaction where Account_No = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,accountno);
            ResultSet rs = ps.executeQuery();
            System.out.println("------------ Transaction Details ---------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.format("%5s %10s %15s %15s %10s","TxnID","TxnDate","AccountNum","Operation","Amount");
            System.out.println();
            while (rs.next()) {
                System.out.format("%5s %10s %15s %15s %10s",rs.getInt(1)+" ",rs.getString(2)+" ",rs.getLong(3)+" ",rs.getString(4)+" ",rs.getInt(5)+"\n");
            }
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deposit(long AccountNumber,int amt)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            Statement stmt = con.createStatement();
            String query = "select balance from Account where Account_no= " + AccountNumber + " ;";
            ResultSet rs = stmt.executeQuery(query);
            int newBal = 0;
            while (rs.next()) {
                newBal = rs.getInt(1) + amt;
            }
            String q2 = "update account set balance= ? where Account_no=?;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, newBal);
            ps2.setLong(2, AccountNumber);
            ps2.executeUpdate();
            System.out.println("Amount has been successfully credited to your account.");
            System.out.println("Now, New balance in your account is :- "+newBal);
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
    public void withdraw(long AccountNumber,int amt)
    {
        String result=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            Statement stmt = con.createStatement();
            String query = "select balance from Account where Account_no= " + AccountNumber + " ;";
            ResultSet rs = stmt.executeQuery(query);
            int newBal = 0;
            while (rs.next()) {
                if (rs.getInt(1) <= 0 || (rs.getInt(1)-amt)<0) {
                    System.out.println();
                    result = "Insufficient balance";
                    System.out.println(result);
                    //System.out.println("Insufficient balance");
                    System.out.println();
                } else {
                    newBal = rs.getInt(1) - amt;
                    result = "Sufficient balance";
                    System.out.println(result);
                }
            }
                if (result.equalsIgnoreCase("Sufficient balance")) {
                    String q2 = "update account set balance= ? where Account_no=?;";
                    PreparedStatement ps2 = con.prepareStatement(q2);
                    ps2.setInt(1, newBal);
                    ps2.setLong(2, AccountNumber);
                    ps2.executeUpdate();
                    System.out.println("Amount has been successfully withdrawn from your account.");
                    System.out.println("Now, New balance in your account is :- " + newBal);
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
    public void updateTransactions(long AccountNo, int amount, String opn)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BankProject";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            String q2 = "insert into transaction (txnDate, Account_no, operation, amount) values(?,?,?,?);";
            PreparedStatement ps3 = con.prepareStatement(q2);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate tDt = LocalDate.now();
            String txnDt=dtf.format(tDt);
            ps3.setString(1, txnDt);
            ps3.setLong(2, AccountNo);
            ps3.setString(3, opn);
            ps3.setInt(4, amount);
            int check2 = ps3.executeUpdate();
            System.out.println("Record has been successfully added to Transactions Table.");
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
    public void moneyTransfer(long accountNo)
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("enter the amount you want to transfer");
        int amount=sc.nextInt();
        System.out.println("enter the account number you want to transfer which account");
        long accNo2=sc.nextLong();
        System.out.println("amount debit from this account ");
        System.out.println();
        withdraw(accountNo, amount);
        System.out.println("amount credit to this account ");
        System.out.println();
        deposit(accNo2,amount);
    }
}
