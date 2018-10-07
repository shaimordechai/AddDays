import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;

public class CalculatorFE {

    // Constants
    //private static final String TIP_TEXT_RESULT_FIELD = "<html><p><font size =4>לחץ<br>להעתיק</html>";
    private static final String TIP_TEXT_RESULT_FIELD = "לחץ להעתיק";
    private static final String TIP_TEXT_DATE_FIELD = "<html><p><font size =3>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
    private static final String NEAR_DAYS_TEXT = "היום ה-";
    private static final String NEAR_DATE_TEXT = "מתאריך";
    private static final String DAYS_HINT = "מספר";
    private static final String DATE_HINT = "dd/mm/yyyy";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.ITALIC, 20);
    private static final Color RESULT_COLOR = new Color(86, 189, 96);
    private static  final int LIMIT_DAYS_INPUT = 4;
    private static  final int LIMIT_DATE_INPUT = 10;

    private static final int DEFAULT_ROWS = 1;
    private static final int DEFAULT_COLUMNS = 7;
    private static final int WINDOW_GAP = 20;
    private static final int DATE_AND_INFO_GAP = 10;
    private static final int BETWEEN_LINES_V_GAP = 10;
    private static final int DAYS_LINE_H_GAP = 18;
    private static final int DATE_LINE_H_GAP = 10;
    private static final int BUTTONS_LINE_H_GAP = 20;
    private static final int NO_GAP = 0;

    private static final int DEFAULT_DELAY = ToolTipManager.sharedInstance().getInitialDelay(); // Delay until the ToolTip is displayed
    private static final ImageIcon INFO_ICON = new ImageIcon("src/qm2.png");

    // Components
    private final  JFrame window = new JFrame("Date Calculator");
    private final JLabel nearDays = new JLabel(NEAR_DAYS_TEXT);
    private final JLabel nearDate = new JLabel(NEAR_DATE_TEXT);
    private final MyTextComponent days = new MyTextComponent(DEFAULT_ROWS, DEFAULT_COLUMNS);
    private final MyTextComponent date = new MyTextComponent(DEFAULT_ROWS, DEFAULT_COLUMNS);
    private final JButton calculate = new JButton("חשב");
    private final JButton clean = new JButton("נקה");
    private final JPanel daysLine = new JPanel();
    private final JPanel dateAndInfo = new JPanel();
    private final JPanel nearDatePanel = new JPanel();
    private final JPanel dateLine = new JPanel();
    private final JPanel buttonsLine = new JPanel();
    private final JPanel resultLine = new JPanel();
    private final JPanel emptyLine = new JPanel();
    private final JLabel result = new JLabel(){
        @Override
        public JToolTip createToolTip(){
            return (new MyJToolTip(this));
        }
    };
    private final JLabel info = new JLabel(INFO_ICON){
        @Override
                public JToolTip createToolTip(){
            return (new MyJToolTip(this));
        }
    };
    private final JLabel empty = new JLabel();
    private final JLabel empty1 = new JLabel();

    // =================================================
    // ===========Constructor:=========================
    // =================================================

    public CalculatorFE(){

        SetFonts();
        initFields();
        InitButtons();
        setLayouts();
        setComponentOrientation();
        addComponentsToLines();
        addLinesToWindow();
        addToolTip(info, TIP_TEXT_DATE_FIELD);
        printScreen();
    }

    private void printScreen() {
        window.setLocationRelativeTo(null);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        window.requestFocus();    }

    private void addLinesToWindow() {
        window.add(daysLine);
        window.add(dateLine);
        window.add(buttonsLine);
        window.add(resultLine);
        window.add(emptyLine);
    }

    private void addComponentsToLines() {
        daysLine.add(nearDays);
        daysLine.add(days);
        dateAndInfo.add(info);
        dateAndInfo.add(date);
        nearDatePanel.add(nearDate);
        dateLine.add(nearDatePanel);
        dateLine.add(dateAndInfo);
        buttonsLine.add(empty);
        buttonsLine.add(clean);
        buttonsLine.add(calculate);
        resultLine.add(result);
        emptyLine.add(empty1);
    }

    private void setComponentOrientation() {
        daysLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        dateLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        buttonsLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void setLayouts() {
        window.setLayout(new VerticalLayout(WINDOW_GAP));
        dateAndInfo.setLayout(new HorizontalLayout(DATE_AND_INFO_GAP));
        nearDatePanel.setLayout(new HorizontalLayout());
        daysLine.setLayout(new FlowLayout(FlowLayout.LEADING, DAYS_LINE_H_GAP, BETWEEN_LINES_V_GAP));
        dateLine.setLayout(new FlowLayout(FlowLayout.LEADING, DATE_LINE_H_GAP, BETWEEN_LINES_V_GAP));
        buttonsLine.setLayout(new FlowLayout(FlowLayout.LEADING, BUTTONS_LINE_H_GAP, BETWEEN_LINES_V_GAP));
        resultLine.setLayout(new FlowLayout(FlowLayout.CENTER, NO_GAP, BETWEEN_LINES_V_GAP));
        emptyLine.setLayout(new FlowLayout(FlowLayout.CENTER, NO_GAP, BETWEEN_LINES_V_GAP));
    }

    private void InitButtons() {
        calculate.setFocusable(false);
        clean.setFocusable(false);
    }

    private void SetFonts() {
        nearDays.setFont(DEFAULT_FONT);
        nearDate.setFont(DEFAULT_FONT);
        days.setFont(DEFAULT_FONT);
        date.setFont(DEFAULT_FONT);
        calculate.setFont(DEFAULT_FONT);
        clean.setFont(DEFAULT_FONT);
    }

    public void initFields() {
        setDocumentToTextArea();
        setHints();
        jumpTab(days, date);
        jumpTab(date, days);
        result.setText("");
        window.requestFocus();
    }

    private void setDocumentToTextArea(){
        days.setDocument(new JTextFieldLimit(LIMIT_DAYS_INPUT));
        date.setDocument(new JTextFieldLimit(LIMIT_DATE_INPUT));
    }

    private void setHints(){
        days.setHint(DAYS_HINT, ComponentOrientation.RIGHT_TO_LEFT);
        date.setHint(DATE_HINT,ComponentOrientation.LEFT_TO_RIGHT);
    }

    private void jumpTab(JComponent from, JComponent to){
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
     * @param label - Next to him would be appears the ToolTip
     * @param tipText - The text that appears in the ToolTip
     */
    private void addToolTip(JLabel label, String tipText) {
        label.setToolTipText(tipText);
        label.addMouseListener(new MouseListener() {
            String prevText = null;

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                copyResult();
            }

            // Copies the current text and prints a message
            private void copyResult() {
                if (tipText.equals(TIP_TEXT_RESULT_FIELD)) {
                    label.setToolTipText(null);
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();
                    StringSelection stringSelection = new StringSelection(result.getText());
                    clipboard.setContents(stringSelection, null);
                    label.setText(TIP_TEXT_COPY_RESULT_FIELD);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                prevText = label.getText();
                ToolTipManager.sharedInstance().setInitialDelay(0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolTipManager.sharedInstance().setInitialDelay(DEFAULT_DELAY);
                if(tipText.equals(TIP_TEXT_RESULT_FIELD)) {
                    label.setText(prevText);
                }
            }
        });
    }

    // =================================================
    // ===========Getters and setters:==================
    // =================================================

    public void setDateText(String text){
        date.setFont(DEFAULT_FONT);
        date.setText(text);
    }

    public JButton getCalculate() {
        return calculate;
    }

    public JButton getClean() {
        return clean;
    }

    public String getDateText() {
        return date.getText();
    }

    public JLabel getResult() {
        return result;
    }

    public void setResultText(String res) {
        addToolTip(result, TIP_TEXT_RESULT_FIELD);
        result.setFont(DEFAULT_FONT);
        result.setForeground(RESULT_COLOR);
        result.setText(res);
    }

    public String getDaysText() {
        return days.getText();
    }
}
