import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.text.JTextComponent;

public class FrontEnd {

    private static final HintTextBox HINT_TEXT_BOX = new HintTextBox();
    private static final String DAYS_FIELD_HINT = "מספר";
    private static final String DATE_FIELD_HINT = "dd/mm/yyyy";
    private static final String TIP_TEXT_DATE_FIELD = "<html><p><font size =5>dd/mm/yyyy<br/>dd/mm/yy<br/>dd.mm.yyyy<br/>dd.mm.yy<br/>ddmmyyyyy<br/>ddmmyy</html>";
    private static final String TIP_TEXT_RESULT_FIELD = "לחץ להעתיק";
    private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 25);
    private static final Font RESULT_FONT = new Font("Arial", Font.PLAIN, 25);
    private static final Color RESULT_COLOR = new Color(8, 186, 68);

    private static final ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
    private static final int defaultDelay = toolTipManager.getInitialDelay();

    private static final JFrame window = new JFrame("Calc");
    private static final JPanel panel = new JPanel();
    private static final JLabel nearDaysField = new JLabel("היום ה...");
    private static final JLabel nearDateField = new JLabel("מתאריך");
    private static final JLabel result = new JLabel();

    private static final JTextArea daysField = new JTextArea();
    private static final JTextArea dateField = new JTextArea(1,8);

    private static final JButton calculate = new JButton("חשב");
    private static ImageIcon icon = new ImageIcon("src/qm2.png");;
    private static final JLabel info = new JLabel(icon);

    /*private static ImageIcon resizeIcon(){
        ImageIcon image =
        Image img = image.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB );
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 20,20, null);
        return new ImageIcon(bi);
    }*/

    public JLabel getNearDaysField() {
        return nearDaysField;
    }

    public JLabel getNearDateField() {
        return nearDateField;
    }

    public  JLabel getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result.setForeground(RESULT_COLOR);
        this.result.setFont(RESULT_FONT);
        this.result.setText(result);
        this.result.setToolTipText(TIP_TEXT_RESULT_FIELD);
    }

    public JTextArea getDaysField() {
        return daysField;
    }

    public void setTextComponent(JTextComponent textComponent, String text){
        textComponent.setFont(DEFAULT_FONT);
        textComponent.setText(text);
    }

    public static JTextArea getDateField() {
        return dateField;
    }

    public static JButton getCalculate() {
        return calculate;
    }

    public FrontEnd() {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nearDaysField.setFont(DEFAULT_FONT);
        HINT_TEXT_BOX.setHint(daysField, DAYS_FIELD_HINT);
        nearDateField.setFont(DEFAULT_FONT);
        HINT_TEXT_BOX.setHint(dateField, DATE_FIELD_HINT);
        calculate.setFont(DEFAULT_FONT);
        calculate.setFocusable(false);


        addToolTip(info, TIP_TEXT_DATE_FIELD, TIP_TEXT_DATE_FIELD);
        addToolTip(result, TIP_TEXT_RESULT_FIELD, TIP_TEXT_COPY_RESULT_FIELD);

        /*info.setToolTipText(TIP_TEXT_DATE_FIELD);
        info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toolTipManager.setInitialDelay(0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toolTipManager.setInitialDelay(prevInitDelay);
            }
        });
        result.setBackground(panel.getBackground());
        result.setFocusable(false);
        result.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection stringSelection = new StringSelection(result.getText());
                clipboard.setContents(stringSelection, null);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toolTipManager.setInitialDelay(0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toolTipManager.setInitialDelay(prevInitDelay);
            }
        });*/

        // My edit
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal arrangement
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(nearDaysField)
                                .addComponent(nearDateField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 20, 20)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(daysField)
                                .addComponent(dateField).addComponent(calculate).addComponent(result))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 20, 20)
                .addComponent(info)
        );

        // Vertical arrangement
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nearDaysField)
                                .addComponent(daysField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nearDateField)
                                .addComponent(dateField).addComponent(info))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addComponent(calculate)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 25, 25)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(result))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 40, 40)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, dateField, daysField, calculate);


        // Display the window.
        window.setLocationRelativeTo(null);
        window.add(panel);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        window.requestFocus();

    }

    private void addToolTip(JLabel label, String tipTextBeforeClicked, String tipTextAfterClicked) {
        label.setToolTipText(tipTextBeforeClicked);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setToolTipText(tipTextAfterClicked);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection stringSelection = new StringSelection(result.getText());
                clipboard.setContents(stringSelection, null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toolTipManager.setInitialDelay(0);
                toolTipManager.mousePressed(null);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setToolTipText(tipTextBeforeClicked);
                toolTipManager.setInitialDelay(defaultDelay);
            }
        });
    }
}