
package atm;
import java.util.Scanner;

public class Keypad{
    private Scanner sc;

    public Keypad(){
        sc = new Scanner(System.in);
    }
    public int getInput(){
        return sc.nextInt();
    }
}
