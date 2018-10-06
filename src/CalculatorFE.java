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
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

public class CalculatorFE {

    // Constants
    private static final String TIP_TEXT_RESULT_FIELD = "<html><font size =5>לחץ להעתיק</html>";
    private static final String TIP_TEXT_DATE_FIELD = "<html><p><font size =5>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.ITALIC, 20);
    private static final Color RESULT_COLOR = Color.GREEN;
    private static final int DEFAULT_DELAY = ToolTipManager.sharedInstance().getInitialDelay(); // Delay until the ToolTip is displayed

    // Components
    private final JPanel panel = new JPanel();
    private final MyTextComponent days = new MyTextComponent(1, 7);
    private final MyTextComponent date = new MyTextComponent(1, 7);
    private final JButton calculate = new JButton("חשב");
    private final JButton clean = new JButton("נקה");
    private final JLabel result = new JLabel();

    // =================================================
    // ===========Constructor:=========================
    // =================================================

    public CalculatorFE(){

        JFrame window = new JFrame("Date Calculator");

        JPanel daysLine = new JPanel();
        JPanel dateAndInfo = new JPanel();
        JPanel nearDatePanel = new JPanel();
        JPanel dateLine = new JPanel();
        JPanel calculateLine = new JPanel();
        JPanel resultLine = new JPanel();
        JPanel emptyLine = new JPanel();

        JLabel nearDays = new JLabel("היום ה-");

        ImageIcon icon = new ImageIcon("src/qm2.png");
        JLabel info = new JLabel(icon);

        JLabel nearDate = new JLabel("מתאריך");

        JLabel empty = new JLabel();
        JLabel empty1 = new JLabel();

        nearDays.setFont(DEFAULT_FONT);
        nearDate.setFont(DEFAULT_FONT);
        initFields();
        calculate.setFont(DEFAULT_FONT);
        calculate.setFocusable(false);
        clean.setFont(DEFAULT_FONT);
        clean.setFocusable(false);
        window.setLayout(new FlowLayout());
        panel.setLayout(new VerticalLayout(20));
        dateAndInfo.setLayout(new HorizontalLayout(10));
        nearDatePanel.setLayout(new HorizontalLayout());

        daysLine.setLayout(new FlowLayout(FlowLayout.LEADING, 18, 10));
        daysLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        daysLine.add(nearDays);
        daysLine.add(days);

        dateAndInfo.add(info);
        dateAndInfo.add(date);

        nearDatePanel.add(nearDate);

        dateLine.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        dateLine.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        dateLine.add(nearDatePanel);
        dateLine.add(dateAndInfo);

        calculateLine.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
        calculateLine.add(empty);
        calculateLine.add(calculate);
        calculateLine.add(clean);

        resultLine.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));
        resultLine.add(result);

        emptyLine.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));
        emptyLine.add(empty1);

        addToolTip(info, TIP_TEXT_DATE_FIELD);

        panel.add(daysLine);
        panel.add(dateLine);
        panel.add(calculateLine);
        panel.add(resultLine);
        panel.add(emptyLine);

        window.setLocationRelativeTo(null);
        window.add(panel);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        panel.requestFocus();
    }

    public void initFields() {
        days.setDocument(new JTextFieldLimit(4));
        days.setFont(DEFAULT_FONT);
        days.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        days.setHint("מספר", ComponentOrientation.RIGHT_TO_LEFT);

        date.setDocument(new JTextFieldLimit(10));
        date.setFont(DEFAULT_FONT);
        date.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        date.setHint("dd/mm/yyyy", ComponentOrientation.LEFT_TO_RIGHT);
        result.setText("");
        panel.requestFocus();
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
