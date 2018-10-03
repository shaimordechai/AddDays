import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.text.JTextComponent;

public class CalculatorFE {

    // Constants
    private static final String TIP_TEXT_RESULT_FIELD = "<html><font size =5>לחץ להעתיק</html>";
    private static final String TIP_TEXT_DATE_FIELD = "<html><p><font size =5>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);
    private static final int DEFAULT_DELAY = ToolTipManager.sharedInstance().getInitialDelay(); // Delay until the ToolTip is displayed

    // Components
    private static final JFrame window = new JFrame("Date Calculator");

    private static final JPanel panel = new JPanel();
    private static final JPanel daysLine = new JPanel();
    private static final JPanel dateAndInfo = new JPanel();
    private static final JPanel nearDatePanel = new JPanel();
    private static final JPanel dateLine = new JPanel();
    private static final JPanel calculateLine = new JPanel();
    private static final JPanel resultLine = new JPanel();


    private static final MyTextComponent days = new MyTextComponent(1, 7);
    private static final JLabel nearDays = new JLabel("היום ה-");

    private static ImageIcon icon = new ImageIcon("src/qm2.png");
    private static final JLabel info = new JLabel(icon);

    private static final MyTextComponent date = new MyTextComponent(1, 7);
    private static final JLabel nearDate = new JLabel("מתאריך");

    private static final JButton calculate = new JButton("חשב");
    private static final JLabel empty = new JLabel();

    private static final JLabel result = new JLabel();

    // =================================================
    // ===========Constructor:=========================
    // =================================================

    public CalculatorFE(){

        nearDays.setFont(DEFAULT_FONT);
        days.setFont(DEFAULT_FONT);
        days.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        days.setHint("מספר", ComponentOrientation.RIGHT_TO_LEFT);
        nearDate.setFont(DEFAULT_FONT);
        date.setFont(DEFAULT_FONT);
        date.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        date.setHint("dd/mm/yyyy", ComponentOrientation.LEFT_TO_RIGHT);
        calculate.setFont(DEFAULT_FONT);
        calculate.setFocusable(false);
        window.setLayout(new FlowLayout());
        panel.setLayout(new VerticalLayout(20));
        dateAndInfo.setLayout(new HorizontalLayout(10));
        nearDatePanel.setLayout(new HorizontalLayout());

        daysLine.setLayout(new FlowLayout(3, 16, 3));
        daysLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        daysLine.add(nearDays);
        daysLine.add(days);

        dateAndInfo.add(info);
        dateAndInfo.add(date);

        nearDatePanel.add(nearDate);

        dateLine.setLayout(new FlowLayout(3, 10, 3));
        dateLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        dateLine.add(nearDatePanel);
        dateLine.add(dateAndInfo);

        calculateLine.setLayout(new HorizontalLayout(27));
        calculateLine.add(empty);
        calculateLine.add(calculate);

        resultLine.setLayout(new FlowLayout(1, 0, 5));
        resultLine.add(result);

        addToolTip(info, TIP_TEXT_DATE_FIELD);

        panel.add(daysLine);
        panel.add(dateLine);
        panel.add(calculateLine);
        panel.add(resultLine);

        window.setLocationRelativeTo(null);
        window.add(panel);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        panel.requestFocus();
    }

    /**
     * Add ToolTip to JLabel component and allows copying text
     * @param label
     * @param tipText
     */
    public static void addToolTip(JLabel label, String tipText) {
        label.setToolTipText(tipText);
        label.addMouseListener(new MouseListener() {
            String prevText = null;

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(tipText.equals(TIP_TEXT_RESULT_FIELD)){
                    label.setText(TIP_TEXT_COPY_RESULT_FIELD);
                    label.setToolTipText(null);
                }
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection stringSelection = new StringSelection(result.getText());
                clipboard.setContents(stringSelection, null);
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

    public static JButton getCalculate() {
        return calculate;
    }

    public static String getDateText() {
        return date.getText();
    }

    public static JLabel getResult() {
        return result;
    }

    public static void setResultText(String res) {
        addToolTip(result, TIP_TEXT_RESULT_FIELD);
        result.setFont(DEFAULT_FONT);
        result.setForeground(Color.BLACK);
        result.setText(res);
    }

    public static String getDaysText() {
        return days.getText();
    }
}
