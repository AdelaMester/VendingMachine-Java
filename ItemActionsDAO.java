import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

public class ItemActionsDAO {

    static String filepath = "C:/Users/bogda/Desktop/courses/microservices/BasicJava/Assessment3/src/shop.csv";

    public static void showAvailableProducts(){
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(filepath);

            // create csvReader object of type CSVReader
            // filereader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] row;

            // we read the data line by line
            while ((row = csvReader.readNext()) != null) {
                if(row[3].equals(String.valueOf(0))){
                    continue;
                }
                for (String cell : row) {
                    System.out.print(cell + ",");
                }
                System.out.println("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static BigDecimal buyProduct(int productId, BigDecimal money) {
        BigDecimal result = null;
        try {
            FileReader filereader = new FileReader(filepath);

            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> csvBody = csvReader.readAll();

            //validation for inventory
            if (csvBody.get(productId)[3].compareTo("0") == 0) {
                NoItemInventoryException.howToRecover();
                returnChange(money);
                System.exit(0);
            }
            // get CSV row column and subtract the QUANTITY user is buying
            csvBody.get(productId)[3] = String.valueOf(Integer.parseInt(csvBody.get(productId)[3]) - 1);

            //user's money minus item price, saved in result variable
            result = money.subtract(new BigDecimal(csvBody.get(productId)[2]));

            //validation to check if money are sufficient
            if (result.compareTo(new BigDecimal(0)) < 0) {
                InsufficientFundsException.howToRecover();
                System.out.println("You have left £" + money);
                returnChange(money);
                System.exit(0);
            } else {
                System.out.println("Enjoy your purchase!");
                System.out.println("You have left £" + result);
            }
            csvReader.close();


            // Write to CSV file which is open
            CSVWriter writer = new CSVWriter(new FileWriter(filepath));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void returnChange(BigDecimal money){

        double amount = (money.doubleValue()) * 100;
        int quarter = 0;
        int dime = 0;
        int nickle = 0;
        int pennies = 0;

        while(amount != 0){
            if (amount >= 25)
            {
                amount =- 25;
                quarter++;
            }
            if (amount >= 10)
            {
                amount =- 10;
                dime++;
            }
            if (amount >= 5)
            {
                amount =- 5;
                nickle++;
            }
            if (amount >= 1)
            {
                amount =- 1;
                pennies++;
            }
        }
        System.out.println("Your change is: "+quarter+" quarters " + dime + " dimes " + nickle + " nickles "+ pennies + " pennies!");
    }
}
