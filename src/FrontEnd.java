import java.awt.ComponentOrientation;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// Create a GUI window
public class FrontEnd {

    private static final JFrame WINDOW = new JFrame("Calculator Add days to the date\n");
    private static final HintTextBox HINT_TEXT_BOX = new HintTextBox();
    private  static  final String DAYS_FIELD_HINT = "מספר";
    private  static  final String DATE_FIELD_HINT = "DD/MM/YYYY";

    private static JLabel NEAR_DAYS_FIELD = new JLabel("היום ה...");
    private static JLabel NEAR_DATE_FIELD = new JLabel("מתאריך");
    private static JLabel RESULT = new JLabel("                 ");

    private static JTextArea DAYS_FIELD = new JTextArea();
    private static JTextArea DATE_FIELD = new JTextArea();

    private static JButton CALCULATE = new JButton("חשב");

    public static JFrame getWINDOW() {
        return WINDOW;
    }

    public static JLabel getNearDaysField() {
        return NEAR_DAYS_FIELD;
    }

    public static void setNearDaysField(JLabel nearDaysField) {
        NEAR_DAYS_FIELD = nearDaysField;
    }

    public static JLabel getNearDateField() {
        return NEAR_DATE_FIELD;
    }

    public static void setNearDateField(JLabel nearDateField) {
        NEAR_DATE_FIELD = nearDateField;
    }

    public static JLabel getResult() {
        return RESULT;
    }

    public static void setResult(String result) {
        RESULT.setForeground(null);
        RESULT.setText(result);
    }

    public static JTextArea getDaysField() {
        return DAYS_FIELD;
    }

    public static void setDaysField(String days) {
        DAYS_FIELD.setText(days);
    }

    public static JTextArea getDateField() {
        return DATE_FIELD;
    }

    public static void setDate(String date) {
        DATE_FIELD.setText(date);
    }

    public static JButton getCalculate() {
        return CALCULATE;
    }

    public static void setCalculate(JButton CALCULATE) {
        FrontEnd.CALCULATE = CALCULATE;
    }

    public FrontEnd() {

        // Create and set up the window.
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HINT_TEXT_BOX.setHint(DAYS_FIELD, DAYS_FIELD_HINT);
        HINT_TEXT_BOX.setHint(DATE_FIELD, DATE_FIELD_HINT);

        // My edit
        JPanel panel = new JPanel();
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal arrangement
        layout.setHorizontalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(NEAR_DAYS_FIELD)
                                .addComponent(NEAR_DATE_FIELD))
                .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(DAYS_FIELD)
                                .addComponent(DATE_FIELD).addComponent(CALCULATE).addComponent(RESULT))
        );

        // Vertical arrangement
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(NEAR_DAYS_FIELD)
                                .addComponent(DAYS_FIELD))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(NEAR_DATE_FIELD)
                                .addComponent(DATE_FIELD))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(CALCULATE))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(RESULT))
        );

        //  layout.linkSize(SwingConstants.HORIZONTAL, DATE_FIELD, CALCULATE);


        // Display the window.
        WINDOW.setLocationRelativeTo(null);
        WINDOW.add(panel);
        WINDOW.pack();
        WINDOW.setResizable(false);
        WINDOW.setVisible(true);
        WINDOW.requestFocus();

    }
}