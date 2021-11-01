import java.math.BigDecimal;
import java.util.Scanner;

public class View {

    public static void showMenu(){
        System.out.println("Choose your option:");
        System.out.println("1.Insert amount £ :");
        System.out.println("2.Buy item");
        System.out.println("3.Exit");
    }
    public static int chooseFromMenu(){
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        return userChoice;
    }
    public static BigDecimal readAmountFromUser(){
        Scanner scan = new Scanner(System.in);
        BigDecimal money = scan.nextBigDecimal();
        return money;
    }
}
