
package atm;
public class BankDatabase{
    
    private Account[] accounts;

    // initialise accounts
    public BankDatabase(){
        accounts = new Account[2];
        accounts[0] = new Account(12345, 54321, 1000.0f, 1200.0f);
        accounts[1] = new Account(98765, 56789, 200.0f, 200.0f);
    }
    private Account getAccount(int accountNumber){
        // loop for matching account number
        for(Account currentAccount: accounts){
            if(currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        }

        return null;
    }
    public double getAvailableBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getAvailableBalance();
    }
    public double getTotalBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN){
        Account userAccount = getAccount(userAccountNumber);
        if(userAccount != null)
            return userAccount.validatePIN(userPIN);
        else
            return false;
    }
    public void credit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).credit(amount);
    }
    public void debit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).debit(amount);
    }
}
