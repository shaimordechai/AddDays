import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class CalculatorFE {

    // Constants
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|" +
            "(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
            "^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
            "(?:(?:16|[2468][048]|[3579][26])00))))$|" +
            "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|" +
            "(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private static final String TIP_TEXT_RESULT_FIELD = "לחץ להעתיק";
    private static final String TIP_TEXT_DATE_FIELD = "<html><p><font size =3>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final String TITLE = "calculate day";
    private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
    private static final String NEAR_DAYS_TEXT = "היום ה-";
    private static final String NEAR_DATE_TEXT = "מתאריך";
    private static final String DAYS_HINT = "מספר";
    private static final String CALCULATE = "חשב";
    private static final String CLEAN = "נקה";
    private static final String DATE_HINT = "dd/mm/yyyy";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font INFO_FONT = new Font("Arial", Font.BOLD, 15);
    private static final Color RESULT_COLOR = new Color(86, 189, 96);
    private static final int LIMIT_DAYS_INPUT = 4;
    private static final int MIN_DAYS_INPUT = 1;
    private static final int LIMIT_DATE_INPUT = 10;
    private static final int MIN_DATE_INPUT = 6;
    private static final int DEFAULT_DELAY = ToolTipManager.sharedInstance().getInitialDelay(); // Delay until the ToolTip is displayed
    //private static final ImageIcon INFO_ICON = new ImageIcon("src/qm2.png");

    // Sizes
    private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
    private final double SCREEN_HEGHIT = SCREEN_SIZE.getHeight();
    private final int CALCULATOR_WIDTH = (int) (SCREEN_WIDTH * 0.2);
    private final int CALCULATOR_HEIGHT = (int) (SCREEN_HEGHIT * 0.3);
    private final int LABEL_WIDTH = (int) (CALCULATOR_WIDTH * 0.3);
    private final int COMPONENT_HEIGHT = (int) (CALCULATOR_HEIGHT * 0.1);
    private final int TEXT_BOX_WIDTH = (int) (CALCULATOR_WIDTH * 0.4);
    private final int INFO_WIDTH_HEIGHT = (int) (CALCULATOR_WIDTH * 0.1);
    private final int BUTTON_WIDTH = (int) (CALCULATOR_WIDTH * 0.35);
    private final int RESULT_WIDTH = (int) (CALCULATOR_WIDTH * 0.5);
    private final int HORIZONTAL_MARGIN = (int) (CALCULATOR_WIDTH * 0.07);

    // Axis
    private final int NEAR_DAYS_X_AXIS = CALCULATOR_WIDTH - LABEL_WIDTH - HORIZONTAL_MARGIN;
    private final int NEAR_DAYS_Y_AXIS = COMPONENT_HEIGHT;
    private final int NEAR_DATE_X_AXIS = CALCULATOR_WIDTH - LABEL_WIDTH - HORIZONTAL_MARGIN;
    private final int NEAR_DATE_Y_AXIS = COMPONENT_HEIGHT * 3;
    private final int DAYS_FIELD_X_AXIS = NEAR_DAYS_X_AXIS - TEXT_BOX_WIDTH - HORIZONTAL_MARGIN;
    private final int DAYS_FIELD_Y_AXIS = COMPONENT_HEIGHT;
    private final int DATE_FIELD_X_AXIS = NEAR_DATE_X_AXIS - TEXT_BOX_WIDTH - HORIZONTAL_MARGIN;
    private final int DATE_FIELD_Y_AXIS = COMPONENT_HEIGHT * 3;
    private final int INFO_X_AXIS = DATE_FIELD_X_AXIS - INFO_WIDTH_HEIGHT;
    private final int INFO_Y_AXIS = DATE_FIELD_Y_AXIS;
    private final int CLEAN_X_AXIS = (int) (CALCULATOR_WIDTH * 0.9 - BUTTON_WIDTH);
    private final int CLEAN_Y_AXIS = COMPONENT_HEIGHT * 5;
    private final int CALCULATE_X_AXIS = (int) (CALCULATOR_WIDTH * 0.8 - BUTTON_WIDTH * 2);
    private final int CALCULATE_Y_AXIS = COMPONENT_HEIGHT * 5;
    private final int RESULT_X_AXIS = (int) (CALCULATOR_WIDTH - RESULT_WIDTH * 1.5);
    private final int RESULT_Y_AXIS = COMPONENT_HEIGHT * 7;

    // Components
    private final JFrame window = new JFrame(TITLE);
    private final JLabel daysJLabel = new JLabel(NEAR_DAYS_TEXT);
    private final JLabel dateJLabel = new JLabel(NEAR_DATE_TEXT);
    private final HintTextArea daysTextArea = new HintTextArea();
    private final HintTextArea dateTextArea = new HintTextArea();
    private final JButton calculateButton = new JButton(CALCULATE);
    private final JButton cleanButton = new JButton(CLEAN);
    private final JLabelWithCustomizedToolTip resultJLWCTT = new JLabelWithCustomizedToolTip(null, JLabel.CENTER);
    //private final JTextPane resultField = new JTextPane();
    //private final JLabelWithCustomizedToolTip infoJLWCTT = new JLabelWithCustomizedToolTip(INFO_ICON);
    private final JLabelWithCustomizedToolTip infoJLWCTT = new JLabelWithCustomizedToolTip("?", JLabel.CENTER);

    // Flags
    private boolean copyEnableFlag = true;

    // =================================================
    // ===========Constructor:=========================
    // =================================================

    public CalculatorFE() {
        this.setFonts();
        this.setSizes();
        this.initButtonsAndListeners();
        this.setComponentOrientation();
        this.setLocation();
        this.addToolTip(infoJLWCTT, TIP_TEXT_DATE_FIELD);
        this.addComponentsToWindow();
        this.printScreen();
    }

    private void setLocation() {
        this.daysJLabel.setLocation(NEAR_DAYS_X_AXIS, NEAR_DAYS_Y_AXIS);
        this.dateJLabel.setLocation(NEAR_DATE_X_AXIS, NEAR_DATE_Y_AXIS);
        this.daysTextArea.setLocation(DAYS_FIELD_X_AXIS, DAYS_FIELD_Y_AXIS);
        this.dateTextArea.setLocation(DATE_FIELD_X_AXIS, DATE_FIELD_Y_AXIS);
        this.infoJLWCTT.setLocation(INFO_X_AXIS, INFO_Y_AXIS);
        this.cleanButton.setLocation(CLEAN_X_AXIS, CLEAN_Y_AXIS);
        this.calculateButton.setLocation(CALCULATE_X_AXIS, CALCULATE_Y_AXIS);
        this.resultJLWCTT.setLocation(RESULT_X_AXIS, RESULT_Y_AXIS);
    }

    private void setSizes() {
        this.window.setSize(CALCULATOR_WIDTH, CALCULATOR_HEIGHT);
        this.daysJLabel.setSize(LABEL_WIDTH, COMPONENT_HEIGHT);
        this.dateJLabel.setSize(LABEL_WIDTH, COMPONENT_HEIGHT);
        this.daysTextArea.setSize(TEXT_BOX_WIDTH, COMPONENT_HEIGHT);
        this.dateTextArea.setSize(TEXT_BOX_WIDTH, COMPONENT_HEIGHT);
        this.infoJLWCTT.setSize(INFO_WIDTH_HEIGHT, INFO_WIDTH_HEIGHT);
        this.cleanButton.setSize(BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.calculateButton.setSize(BUTTON_WIDTH, COMPONENT_HEIGHT);
        this.resultJLWCTT.setSize(RESULT_WIDTH, COMPONENT_HEIGHT);
    }

    private void printScreen() {
        this.window.setLayout(null);
        this.window.setLocationRelativeTo(null);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setVisible(true);
        this.window.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                checkInputOnline();
                initFields();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

        });
    }

    private void addComponentsToWindow() {
        this.window.add(daysJLabel);
        this.window.add(dateJLabel);
        this.window.add(daysTextArea);
        this.window.add(dateTextArea);
        this.window.add(infoJLWCTT);
        this.window.add(cleanButton);
        this.window.add(calculateButton);
        this.window.add(resultJLWCTT);
    }

    private void setComponentOrientation() {
        this.daysJLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.dateJLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.resultJLWCTT.setHorizontalAlignment(JLabel.CENTER);
    }

    private void initButtonsAndListeners() {
        this.calculateButton.setFocusable(false);
        this.calculateButton.setEnabled(false);
        this.calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        this.cleanButton.setFocusable(false);
        this.cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initFields();
            }
        });
    }

    private void setFonts() {
        System.out.println(this.getClass().getSimpleName() + ".setFont");
        this.daysJLabel.setFont(DEFAULT_FONT);
        this.dateJLabel.setFont(DEFAULT_FONT);
        this.daysTextArea.setFont(DEFAULT_FONT);
        this.dateTextArea.setFont(DEFAULT_FONT);
        this.infoJLWCTT.setFont(INFO_FONT);
        this.calculateButton.setFont(DEFAULT_FONT);
        this.cleanButton.setFont(DEFAULT_FONT);
        this.resultJLWCTT.setFont(DEFAULT_FONT);
    }

    public void initFields() {
        System.out.println(this.getClass().getSimpleName() + ".initFields");
        this.setHints();
        this.jumpTab(daysTextArea, dateTextArea);
        this.jumpTab(dateTextArea, daysTextArea);
        this.resultJLWCTT.setText(null);
        this.window.requestFocus();
    }

    private void checkInputOnline() {
        System.out.println(this.getClass().getSimpleName() + ".checkInputOnline");
        this.daysTextArea.setDocument(new JTextFieldLimit(LIMIT_DAYS_INPUT, MIN_DAYS_INPUT, REGEX_NUMBER, DAYS_HINT, resultJLWCTT, calculateButton));
        this.dateTextArea.setDocument(new JTextFieldLimit(LIMIT_DATE_INPUT, MIN_DATE_INPUT, REGEX_DATE, DATE_HINT, resultJLWCTT, calculateButton));
    }

    private void setHints() {
        System.out.println(this.getClass().getSimpleName() + ".setHints");
        this.daysTextArea.setHint(DAYS_HINT, ComponentOrientation.RIGHT_TO_LEFT);
        this.dateTextArea.setHint(DATE_HINT, ComponentOrientation.LEFT_TO_RIGHT);
    }

    private void jumpTab(JComponent from, final JComponent to) {
        from.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_TAB:
                        to.requestFocus();
                        break;
                    case KeyEvent.VK_ENTER:
                        if (calculateButton.isEnabled()) {
                            calculateResult();
                        }
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * Add ToolTip to JLabel component and allows copying text
     *
     * @param label   - Next to him would be appears the ToolTip
     * @param tipText - The text that appears in the ToolTip
     */
    private void addToolTip(final JLabel label, final String tipText) {
        System.out.println(this.getClass().getSimpleName() + ".addToolTip");
        label.setToolTipText(tipText);
        label.addMouseListener(new MouseListener() {
            private String prevText = null;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                copyResult();
            }

            // Copies the current text and prints a message
            private void copyResult() {
                if (tipText.equals(TIP_TEXT_RESULT_FIELD) && copyEnableFlag) {
                    label.setToolTipText(null);
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();
                    StringSelection stringSelection = new StringSelection(resultJLWCTT.getText());
                    clipboard.setContents(stringSelection, null);
                    label.setText(TIP_TEXT_COPY_RESULT_FIELD);
                    copyEnableFlag = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                prevText = label.getText();
                ToolTipManager.sharedInstance().setInitialDelay(0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyEnableFlag = true;
                ToolTipManager.sharedInstance().setInitialDelay(DEFAULT_DELAY);
                if (tipText.equals(TIP_TEXT_RESULT_FIELD)) {
                    label.setText(prevText);
                    label.setToolTipText(tipText);
                }
            }
        });
    }

    public void calculateResult() {
        System.out.println(this.getClass().getSimpleName() + ".calculateResult");
        this.setDateText(Calculator.calculate("0", this.dateTextArea.getText()));
        this.setResultText(Calculator.calculate(daysTextArea.getText(), this.dateTextArea.getText()));
    }

    // =================================================
    // ===========Getters and setters:==================
    // =================================================

    public JButton getCalculate() {
        return this.calculateButton;
    }

    public JButton getClean() {
        return this.cleanButton;
    }

    public String getDateText() {
        return this.dateTextArea.getText();
    }

    public void setDateText(String text) {
        this.dateTextArea.setFont(DEFAULT_FONT);
        this.dateTextArea.setText(text);
    }

    public JLabel getResult() {
        return resultJLWCTT;
    }

    public void setResultText(String res) {
        addToolTip(this.resultJLWCTT, TIP_TEXT_RESULT_FIELD);
        this.resultJLWCTT.setFont(DEFAULT_FONT);
        this.resultJLWCTT.setForeground(RESULT_COLOR);
        this.resultJLWCTT.setText(res);
    }

    public String getDaysText() {
        return this.daysTextArea.getText();
    }

}
