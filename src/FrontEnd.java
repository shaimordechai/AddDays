import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class FrontEnd {

    private static final HintTextBox HINT_TEXT_BOX = new HintTextBox();
    private static final String DAYS_FIELD_HINT = "מספר";
    private static final String DATE_FIELD_HINT = "dd/mm/yyyy";
    private static final String TIP_TEXT = "<html>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final Font FONT = new Font("Arial", Font.PLAIN, 14);
    //private static final Color DEFAULT_COLOR = new Color(114, 68, 240);
    private static final Color RESULT_COLOR = Color.GREEN;
    private static final JFrame WINDOW = new JFrame("Calc\n");
    private static final JPanel PANEL = new JPanel();
    private static final JLabel NEAR_DAYS_FIELD = new JLabel("היום ה...");
    private static final JLabel NEAR_DATE_FIELD = new JLabel("מתאריך");
    private static final JTextArea RESULT = new JTextArea(1, 8);

    private static final JTextArea DAYS_FIELD = new JTextArea();
    private static final JTextArea DATE_FIELD = new JTextArea(1,8);

    private static final JButton CALCULATE = new JButton("חשב");
    private static final JLabel INFO = new JLabel("?");

    /*private static List<JComponent> components = new ArrayList<JComponent>(){{
        add(new JLabel("היום ה..."));
        add(new JLabel("מתאריך"));
        add(new JLabel());
        add(new JTextArea());
        add(new JTextArea(1,8));
        add(new JButton("חשב"));
    }};*/



    public static JLabel getNearDaysField() {
        return NEAR_DAYS_FIELD;
    }

   /* public static void setNearDaysField(JLabel nearDaysField) {
        NEAR_DAYS_FIELD = nearDaysField;
    }*/

    public static JLabel getNearDateField() {
        return NEAR_DATE_FIELD;
    }

    public static JTextArea getResult() {
        return RESULT;
    }

    public static void setResult(String result) {
        RESULT.setForeground(RESULT_COLOR);
        RESULT.setFont(FONT);
        RESULT.setText(result);
    }

    public static JTextArea getDaysField() {
        return DAYS_FIELD;
    }

    public  static  void setTextComponent(JTextComponent textComponent, String text){
       // textComponent.setForeground(DEFAULT_COLOR);
        textComponent.setFont(FONT);
        textComponent.setText(text);
    }
   /* public static void setDaysField(String days) {
        DAYS_FIELD.setText(days);
    }*/

    public static JTextArea getDateField() {
        return DATE_FIELD;
    }

    /*public static void setDate(String date) {
        DATE_FIELD.setText(date);
    }*/

    public static JButton getCalculate() {
        return CALCULATE;
    }

    /*public static void setCalculate(JButton CALCULATE) {
        FrontEnd.CALCULATE = CALCULATE;
    }*/

    public FrontEnd() {
        // Create and set up the window.
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NEAR_DAYS_FIELD.setFont(FONT);
        HINT_TEXT_BOX.setHint(DAYS_FIELD, DAYS_FIELD_HINT);
        NEAR_DATE_FIELD.setFont(FONT);
        HINT_TEXT_BOX.setHint(DATE_FIELD, DATE_FIELD_HINT);
        CALCULATE.setFont(FONT);
        CALCULATE.setFocusable(false);
        INFO.setFont(new Font("Arial", Font.BOLD, 10));
        INFO.setToolTipText(TIP_TEXT);
        RESULT.setBackground(PANEL.getBackground());
        RESULT.setFocusable(false);

        // My edit
        PANEL.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GroupLayout layout = new GroupLayout(PANEL);
        PANEL.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal arrangement
        layout.setHorizontalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(NEAR_DAYS_FIELD)
                                .addComponent(NEAR_DATE_FIELD))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 20, 20)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(DAYS_FIELD)
                                .addComponent(DATE_FIELD).addComponent(CALCULATE).addComponent(RESULT))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 20, 20)
                .addComponent(INFO)
        );

        // Vertical arrangement
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(NEAR_DAYS_FIELD)
                                .addComponent(DAYS_FIELD))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(NEAR_DATE_FIELD)
                                .addComponent(DATE_FIELD)
                                .addComponent(INFO))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addComponent(CALCULATE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(RESULT))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 40, 40)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, DATE_FIELD, DAYS_FIELD, CALCULATE);


        // Display the window.
        WINDOW.setLocationRelativeTo(null);
        WINDOW.add(PANEL);
        WINDOW.pack();
        WINDOW.setResizable(false);
        WINDOW.setVisible(true);
        WINDOW.requestFocus();

    }
}