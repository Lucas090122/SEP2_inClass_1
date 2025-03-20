import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCalculateTotalCost() {
        String input = "1\n2\n10.5\n2\n5.0\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ShoppingCart.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("36.0"));
    }
}
