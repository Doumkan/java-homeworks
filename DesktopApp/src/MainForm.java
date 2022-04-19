import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton collapseButton;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField fullNameTF;
    private JLabel fullNameLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JButton expandButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainForm() {
        fullNameTF.setVisible(false);
        fullNameLabel.setVisible(false);
        expandButton.setVisible(false);
        firstNameTF.addActionListener(pressEnterKey());
        lastNameTF.addActionListener(pressEnterKey());
        fullNameTF.addActionListener(pressEnterKey());

        collapseButton.addActionListener(e -> activateExpand());
        expandButton.addActionListener(e -> activateCollapse());
    }

    private void activateExpand() {
        if (!checkValidInput()) {
            displayErrorMsg();
            return;
        }
        firstNameTF.setVisible(false);
        lastNameTF.setVisible(false);
        firstNameLabel.setVisible(false);
        lastNameLabel.setVisible(false);
        fullNameTF.setVisible(true);
        fullNameLabel.setVisible(true);
        fullNameTF.setText(firstNameTF.getText() + " " + lastNameTF.getText());
        collapseButton.setVisible(false);
        expandButton.setVisible(true);
    }

    private void activateCollapse() {
        String[] splitted = fullNameTF.getText().split("\\s");
        if (splitted.length <= 1 || splitted.length > 4) {
            displayErrorMsg();
            return;
        }
        for (String s : splitted) {
            if (s.length() == 0 || s.matches("(.*)?\\d+(.*)?")) {
                displayErrorMsg();
                return;
            }
        }

        String[] arr = fullNameTF.getText().split("\\s", 2);
        String fName = arr[0];
        String lName = arr[1];
        fullNameTF.setVisible(false);
        fullNameLabel.setVisible(false);
        firstNameLabel.setVisible(true);
        firstNameTF.setVisible(true);
        firstNameTF.setText(fName);
        lastNameLabel.setVisible(true);
        lastNameTF.setVisible(true);
        lastNameTF.setText(lName);
        expandButton.setVisible(false);
        collapseButton.setVisible(true);
    }

    private boolean checkValidInput() {
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String regex = "(.*)?\\d+(.*)?";
        if(firstName.length() == 0 || lastName.length() == 0) {
            return false;
        }
        return !firstName.matches(regex) && !lastName.matches(regex);
    }

    private ActionListener pressEnterKey() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (collapseButton.isVisible()) {
                    activateExpand();
                } else {
                    activateCollapse();
                }
            }
        };
    }

    private void displayErrorMsg() {
        JOptionPane.showMessageDialog(
                mainPanel,
                "All fields are mandatory, and should contain at least two words with no digits",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
