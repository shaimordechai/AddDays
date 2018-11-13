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
import javax.swing.JTextPane;
import javax.swing.ToolTipManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

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
	private static final String TITEL = "calculate day";
	private static final String TIP_TEXT_COPY_RESULT_FIELD = "הועתק";
	private static final String NEAR_DAYS_TEXT = "היום ה-";
	private static final String NEAR_DATE_TEXT = "מתאריך";
	private static final String DAYS_HINT = "מספר";
	private static final String CALCULAATE = "חשב";
	private static final String CLEAN = "נקה";
	private static final String DATE_HINT = "dd/mm/yyyy";
	private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);
	private static final Font INFO_FONT = new Font("Arial", Font.BOLD, 15);
	private static final Color RESULT_COLOR = new Color(86, 189, 96);
	private static  final int LIMIT_DAYS_INPUT = 4;
	private static  final int MIN_DAYS_INPUT = 1;
	private static  final int LIMIT_DATE_INPUT = 10;
	private static  final int MIN_DATE_INPUT = 6;
	private static final int DEFAULT_DELAY = ToolTipManager.sharedInstance().getInitialDelay(); // Delay until the ToolTip is displayed
	//private static final ImageIcon INFO_ICON = new ImageIcon("src/qm2.png");

	// Sizes
	private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
	private final double DSCREEN_HEGHIT = SCREEN_SIZE.getHeight();
	private final int CALCULATOR_WIDTH = (int)(SCREEN_WIDTH * 0.15);
	private final int CALCULATOR_HEGHIT = (int)(DSCREEN_HEGHIT * 0.25);
	private final int LABEL_WIDTH = (int) (CALCULATOR_WIDTH * 0.3);
	private final int COMPONENT_HEGHIT = (int) (CALCULATOR_HEGHIT * 0.1);
	private final int TEXT_BOX_WIDTH = (int) (CALCULATOR_WIDTH * 0.4);
	private final int INFO_WIDTH_HEGHIT = (int) (CALCULATOR_WIDTH * 0.1);
	private final int BUTTON_WIDTH = (int) (CALCULATOR_WIDTH * 0.35);
	private final int RESULT_WIDTH = (int) (CALCULATOR_WIDTH * 0.5);
	private final int HORIZONTAL_MARGIN = (int) (CALCULATOR_WIDTH * 0.07);

	// Axis
	private final int NEAR_DAYS_X_AXIS = CALCULATOR_WIDTH - LABEL_WIDTH - HORIZONTAL_MARGIN;
	private final int NEAR_DAYS_Y_AXIS = COMPONENT_HEGHIT;
	private final int NEAR_DATE_X_AXIS = CALCULATOR_WIDTH - LABEL_WIDTH - HORIZONTAL_MARGIN;
	private final int NEAR_DATE_Y_AXIS = COMPONENT_HEGHIT * 3;
	private final int DAYS_FIELD_X_AXIS = NEAR_DAYS_X_AXIS - TEXT_BOX_WIDTH - HORIZONTAL_MARGIN;
	private final int DAYS_FIELD_Y_AXIS = COMPONENT_HEGHIT;
	private final int DATE_FIELD_X_AXIS = NEAR_DATE_X_AXIS - TEXT_BOX_WIDTH - HORIZONTAL_MARGIN;
	private final int DATE_FIELD_Y_AXIS = COMPONENT_HEGHIT * 3;
	private final int INFO_X_AXIS = DATE_FIELD_X_AXIS - INFO_WIDTH_HEGHIT;
	private final int INFO_Y_AXIS = DATE_FIELD_Y_AXIS;
	private final int CLEAN_X_AXIS = (int) (CALCULATOR_WIDTH * 0.9 - BUTTON_WIDTH);
	private final int CLEAN_Y_AXIS = COMPONENT_HEGHIT * 5;
	private final int CALCULATE_X_AXIS = (int) (CALCULATOR_WIDTH * 0.8 - BUTTON_WIDTH * 2);
	private final int CALCULATE_Y_AXIS = COMPONENT_HEGHIT * 5;
	private final int RESULT_X_AXIS = (int) (CALCULATOR_WIDTH - RESULT_WIDTH * 1.5);
	private final int RESULT_Y_AXIS = COMPONENT_HEGHIT * 7;

	// Components
	private final JFrame window = new JFrame(TITEL);
	private final JLabel nearDays = new JLabel(NEAR_DAYS_TEXT);
	private final JLabel nearDate = new JLabel(NEAR_DATE_TEXT);
	private final MyTextComponent daysField = new MyTextComponent();
	private final MyTextComponent dateField = new MyTextComponent();
	private final JButton calculateButton = new JButton(CALCULAATE);
	private final JButton cleanButton = new JButton(CLEAN);
	private final MyLabelComponent resultField = new MyLabelComponent(null, JLabel.CENTER);
	//private final JTextPane resultField = new JTextPane();


	//private final MyLabelComponent info = new MyLabelComponent(INFO_ICON);
	private final MyLabelComponent info = new MyLabelComponent("?", JLabel.CENTER);

	// Flags
	private boolean copyEnableFlag = true;


	// =================================================
	// ===========Constructor:=========================
	// =================================================

	public CalculatorFE(){

		setFonts();
		setSizes();
		InitButtons();
		setComponentOrientation();
		setLocation();
		addToolTip(info, TIP_TEXT_DATE_FIELD);
		addComponentsToWindow();
		printScreen();
	}

	private void setLocation() {
		nearDays.setLocation(NEAR_DAYS_X_AXIS, NEAR_DAYS_Y_AXIS);
		nearDate.setLocation(NEAR_DATE_X_AXIS, NEAR_DATE_Y_AXIS);
		daysField.setLocation(DAYS_FIELD_X_AXIS, DAYS_FIELD_Y_AXIS);
		dateField.setLocation(DATE_FIELD_X_AXIS, DATE_FIELD_Y_AXIS);
		info.setLocation(INFO_X_AXIS, INFO_Y_AXIS);
		cleanButton.setLocation(CLEAN_X_AXIS, CLEAN_Y_AXIS);
		calculateButton.setLocation(CALCULATE_X_AXIS, CALCULATE_Y_AXIS);
		resultField.setLocation(RESULT_X_AXIS, RESULT_Y_AXIS);
	}

	private void setSizes() {
		window.setSize(CALCULATOR_WIDTH, CALCULATOR_HEGHIT);
		nearDays.setSize(LABEL_WIDTH, COMPONENT_HEGHIT);
		nearDate.setSize(LABEL_WIDTH, COMPONENT_HEGHIT);
		daysField.setSize(TEXT_BOX_WIDTH, COMPONENT_HEGHIT);
		dateField.setSize(TEXT_BOX_WIDTH, COMPONENT_HEGHIT);
		info.setSize(INFO_WIDTH_HEGHIT, INFO_WIDTH_HEGHIT);
		cleanButton.setSize(BUTTON_WIDTH, COMPONENT_HEGHIT);
		calculateButton.setSize(BUTTON_WIDTH, COMPONENT_HEGHIT);
		resultField.setSize(RESULT_WIDTH, COMPONENT_HEGHIT);
	}

	private void printScreen() {
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				checkInputOnline();
				initFields();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void addComponentsToWindow() {
		window.add(nearDays);
		window.add(nearDate);
		window.add(daysField);
		window.add(dateField);
		window.add(info);
		window.add(cleanButton);
		window.add(calculateButton);
		window.add(resultField);
	}

	private void setComponentOrientation() {
		nearDays.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		nearDate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultField.setHorizontalAlignment(JLabel.CENTER);
	}

	private void InitButtons() {
		calculateButton.setFocusable(false);
		calculateButton.setEnabled(false);
		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcolateResult();
			}
		});

		cleanButton.setFocusable(false);
		cleanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initFields();
			}
		});
	}

	private void setFonts() {
		System.out.println(this.getClass().getSimpleName() + ".setFont");
		nearDays.setFont(DEFAULT_FONT);
		nearDate.setFont(DEFAULT_FONT);
		daysField.setFont(DEFAULT_FONT);
		dateField.setFont(DEFAULT_FONT);
		info.setFont(INFO_FONT);
		calculateButton.setFont(DEFAULT_FONT);
		cleanButton.setFont(DEFAULT_FONT);
		resultField.setFont(DEFAULT_FONT);
	}

	public void initFields() {
		System.out.println(this.getClass().getSimpleName() + ".initFields");
		setHints();
		jumpTab(daysField, dateField);
		jumpTab(dateField, daysField);
		resultField.setText(null);
		window.requestFocus();
	}

	private void checkInputOnline(){
		System.out.println(this.getClass().getSimpleName() + ".checkInputOnline");
		daysField.setDocument(new JTextFieldLimit(LIMIT_DAYS_INPUT, MIN_DAYS_INPUT, REGEX_NUMBER, DAYS_HINT, resultField, calculateButton));
		dateField.setDocument(new JTextFieldLimit(LIMIT_DATE_INPUT, MIN_DATE_INPUT, REGEX_DATE, DATE_HINT, resultField, calculateButton));
	}

	private void setHints(){
		System.out.println(this.getClass().getSimpleName() + ".setHints");
		daysField.setHint(DAYS_HINT, ComponentOrientation.RIGHT_TO_LEFT);
		dateField.setHint(DATE_HINT,ComponentOrientation.LEFT_TO_RIGHT);
	}

	private void jumpTab(JComponent from, final JComponent to){
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
						if(calculateButton.isEnabled()) {
							calcolateResult();
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
	 * @param label - Next to him would be appears the ToolTip
	 * @param tipText - The text that appears in the ToolTip
	 */
	private void addToolTip(final JLabel label, final String tipText) {
		System.out.println(this.getClass().getSimpleName() + ".addToolTip");
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
				if (tipText.equals(TIP_TEXT_RESULT_FIELD) && copyEnableFlag) {
					label.setToolTipText(null);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Clipboard clipboard = toolkit.getSystemClipboard();
					StringSelection stringSelection = new StringSelection(resultField.getText());
					clipboard.setContents(stringSelection, null);
					label.setText(TIP_TEXT_COPY_RESULT_FIELD);
					copyEnableFlag = false;
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
				copyEnableFlag = true;
				ToolTipManager.sharedInstance().setInitialDelay(DEFAULT_DELAY);
				if(tipText.equals(TIP_TEXT_RESULT_FIELD)) {
					label.setText(prevText);
					label.setToolTipText(tipText);
				}
			}
		});
	}

	public void calcolateResult() {
		System.out.println(this.getClass().getSimpleName() + ".calcolateResult");
		setDateText(Calculator.calculate("0", dateField.getText()));
		setResultText(Calculator.calculate(daysField.getText(), dateField.getText()));
	}

	// =================================================
	// ===========Getters and setters:==================
	// =================================================

	public void setDateText(String text){
		dateField.setFont(DEFAULT_FONT);
		dateField.setText(text);
	}

	public JButton getCalculate() {
		return calculateButton;
	}

	public JButton getClean() {
		return cleanButton;
	}

	public String getDateText() {
		return dateField.getText();
	}

	public JLabel getResult() {
		return resultField;
	}

	public void setResultText(String res) {
		addToolTip(resultField, TIP_TEXT_RESULT_FIELD);
		resultField.setFont(DEFAULT_FONT);
		resultField.setForeground(RESULT_COLOR);
		resultField.setText(res);
	}

	public String getDaysText() {
		return daysField.getText();
	}

}
