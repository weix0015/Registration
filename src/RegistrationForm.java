import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegistrationForm extends JDialog {
    private JTextField tfName, tfEmail, tfPhone, tfAddress;
    private JPasswordField pfPassword, pfConfirmPassword;
    private JButton btnRegister, btnCancel;
    private JPanel registerPanel;
    private User user;

    // Constructor to registration form
    public RegistrationForm(JFrame parent) {
        super(parent);
        initializeUI();
        setupListeners();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // user interface registration form
    private void initializeUI() {
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
    }

    // listeners for the register and cancel buttons
    private void setupListeners() {
        btnRegister.addActionListener(e -> registerUser());
        btnCancel.addActionListener(e -> dispose());
    }

    // Check if any required field is empty
    private boolean isAnyFieldEmpty() {
        return InputValidator.anyFieldEmpty(tfName, tfEmail, tfPhone, tfAddress) ||
                InputValidator.isFieldEmpty(pfPassword);
    }

    // Clear the form fields
    private void clearFormFields() {
        tfName.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        pfPassword.setText("");
        pfConfirmPassword.setText("");
    }

    // error message to any field is empty
    private void registerUser() {
        if (isAnyFieldEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all information",
                    "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // if password and confirm password does not match error message
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());


        if (!Objects.equals(password, confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Clear the form fields
        if (DatabaseManager.addUser(tfName.getText(), tfEmail.getText(), tfPhone.getText(),
                tfAddress.getText(), password)) {
            clearFormFields();

            JOptionPane.showMessageDialog(this,
                    "User registered successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUser() {
        return user;
    }
}

