import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

class ItemActionsDAOTest {

    @Test
    void testshowAvailableProducts() {

        // I use System.setOut(PrintStream) to temporarily replace standard out with an in memory PrintStream backed by a ByteArrayOutputStream and use that to get the output.
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream tps = new PrintStream(baos);

        System.setOut(tps);
        ItemActionsDAO.showAvailableProducts();
        System.setOut(original);
        tps.flush();

        assertThat("1,Coca-Cola,0.70,10,", baos.toString(),containsString("1,Coca-Cola,0.70,10,"));
    }
}