import javax.swing.*;
import java.util.stream.Stream;

public class InputValidator {

    // Check if a specific JTextField is empty
    static boolean isFieldEmpty(JTextField field) {
        return field.getText().isEmpty();
    }

    // Check if any JTextFields is empty
    static boolean anyFieldEmpty(JTextField... fields) {
        // Use Java Stream API to check if any field is empty
        return Stream.of(fields).anyMatch(InputValidator::isFieldEmpty);
    }
}