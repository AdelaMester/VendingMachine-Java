import java.math.BigDecimal;

public class VendingMachineController {

        public static void execute(){

            BigDecimal money = new BigDecimal(0);
            View.showMenu();
            int userChoice = View.chooseFromMenu();

            while(userChoice <= 0 || userChoice > 3){
                System.out.println("Choose option: 1,2 or 3: ");
                View.showMenu();
                userChoice = View.chooseFromMenu();
            }

            while(userChoice != 3){
                if(userChoice == 1){
                    System.out.println("How much money you want to insert? ");
                    money = money.add(View.readAmountFromUser());
                    System.out.println("Your amount is: £ " + money);
                }
                if(userChoice == 2){
                    if(money.equals(new BigDecimal(0))){
                        System.out.println("You forgot to insert money.");
                        System.out.println("How much money you want to insert? ");
                        money = money.add(View.readAmountFromUser());
                        System.out.println("Your amount is: £ " + money);
                    }
                    ItemActionsDAO.showAvailableProducts();
                    System.out.println("Select your product id: ");
                    int userSelection = View.chooseFromMenu();
                    money = ItemActionsDAO.buyProduct(userSelection, money);
                    ItemActionsDAO.returnChange(money);
                }

                userChoice = View.chooseFromMenu();
            }
        }

}
