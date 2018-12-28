
package atm;
public class Account{
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

  
    // initialise attributes
    public Account(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance){
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }
  
    public boolean validatePIN(int userPIN){
        if(userPIN == pin)
            return true;
        else
            return false;
    }
 
    public double getAvailableBalance(){
        return availableBalance;
    }
    public double getTotalBalance(){
        return totalBalance;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public void credit(double amount){
        totalBalance += amount;
    }
    public void debit(double amount){
        availableBalance -= amount;
        totalBalance -= amount;
    }

}
