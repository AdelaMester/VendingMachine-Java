public class NoItemInventoryException extends RuntimeException{

    public static void howToRecover(){
        System.out.println("Sorry! The item is sold out!");
    }
}
