
package atm;
public class ATM{
    private boolean userAuthenticated;
    private int currentAccountNumber;

    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;

    // constants for 1main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

   
    //initialization
    public ATM(){
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
    }

    // start ATM
    public void run(){
   
        while(true){
            
            while(!userAuthenticated){
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }

            performTransactions();
            // reset after finished transaction
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }
   
    private void authenticateUser(){
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter your pin: ");
        int pin = keypad.getInput();

       
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        if(userAuthenticated)
            currentAccountNumber = accountNumber;
        else
            screen.displayMessageLine(
                    "Invalid account number of PIN. Please try again.");
    }
    
    private void performTransactions(){
    
        Transaction currentTransaction = null;//for keep transaction sub class

        boolean userExited = false;

      
        while(!userExited){
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();
    
            switch(mainMenuSelection){
                case BALANCE_INQUIRY:
                case WITHDRAWL:
                case DEPOSIT:

                    currentTransaction = createTransaction(mainMenuSelection);

                    currentTransaction.execute();
                break;
                case EXIT:
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true;
                break;
                default:
                    screen.displayMessageLine(
                            "\nYou did not enter a valid selection. Try again.");
                break;
            }
        }
    }
  
    private int displayMainMenu(){
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput();
    }
    // return object of  Transaction subclass
    private Transaction createTransaction(int type){
        Transaction temp = null;

        switch(type){
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(
                        currentAccountNumber, screen, bankDatabase);
            break;
            case WITHDRAWL:
                temp = new Withdrawl(currentAccountNumber, screen,
                        bankDatabase, keypad, cashDispenser);
            break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen,
                        bankDatabase, keypad, depositSlot);
            break;
        }

        return temp;
    }
}
