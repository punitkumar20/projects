import java.util.Scanner;
public class BankApplicationImplementation
{
    public static void main(String[] args)
    {
        String ans;
        customer c=new customer();
        account ac= new account();
        transaction t=new transaction();
        //accountType acT=new accountType();
        Scanner scn = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("******** Welcome to IDBC Bank *********");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();
        do {
        System.out.println("Do you have any account :- yes or no ");

        String acc=scn.next();
            if(acc.equalsIgnoreCase("yes"))
            {

                System.out.println("Please select from following options : - ");
                System.out.println("1.Personal Details.");
                System.out.println("2.Transaction History.");
                System.out.println("3.deposit and withdraw");
                System.out.println("4.Account Details.");
                System.out.println("5.Money Transfer to Another Account");


                int choice = scn.nextInt();


                switch (choice)
                {
                    case 1:
                        System.out.println("");
                        c.displayCustomer();
                        //System.out.println("*************** Account Details ****************");
                        //System.out.println("--------------------------------------------------------------------");
                        //ac.displayAccount();
                        System.out.println();
                        break;
                    case 2:
                        t.displayTransaction();
                        break;
                    case 3:
                        System.out.println("Enter your Account number");
                        long acNo = scn.nextLong();
                        System.out.println("Enter choice : - ");
                        System.out.println("1.Deposit");
                        System.out.println("2.Withdraw");
                        int ch=scn.nextInt();
                        if(ch==1) {
                            String op="Deposit";
                            System.out.println("Enter amount you want to deposit : - ");
                            int amt = scn.nextInt();
                            t.deposit(acNo, amt);
                            t.updateTransactions(acNo,amt,op);
                        }
                        else
                        {
                            String op="withdraw";
                            System.out.println("Enter amount you want to withdraw : -");
                            int amt=scn.nextInt();
                            t.withdraw(acNo,amt);
                            t.updateTransactions(acNo,amt,op);
                        }
                        break;
                    case 4:
                        System.out.println();
                        ac.displayAccount();
                        break;
                    case 5:
                        int amt = 0;
                        String op ="";
                        System.out.println("enter the account number which you will transfer the money");
                        long accN0=scn.nextLong();
                        t.moneyTransfer(accN0);
                        t.updateTransactions(accN0,amt,op);
                }
            }
            else
            {
                System.out.println("------------------ You need to create your account first -----------------");
                System.out.println();
                System.out.println("Do you want to create new account : - yes or no ");
                String s1=scn.next();

                if(s1.equalsIgnoreCase("yes"))
                {
                    c.addValuesintoCustomer();
                    System.out.println("Thanks for ur visit in IDBC.");
                }
                else
                {
                    System.out.println("Thanks for ur visit in IDBC.");
                }
            }
            System.out.println();
           System.out.println("Do you want to go for more options ? Yes Or No");
            ans = scn.next();
        }while (ans.equalsIgnoreCase("yes"));



    }
}
