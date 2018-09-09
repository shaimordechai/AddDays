import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jdesktop.swingx.prompt.PromptSupport;

public class FrontEnd {

	private static final Color BOARD_COLOR = Color.WHITE;
	private static final Font FONT = new Font("Arial", Font.BOLD, 16);
	private static final HintTextBox HINT_TEXT_BOX = new HintTextBox();
	private  static  final String DAYS_FIELD_HINT = "היום ה...";
	private  static  final String DATE_FIELD_HINT = "מתאריך";
	private  static  final String RESULT_FIELD_HINT = "יוצא ב...";
	private static final String CALCULATE_TEXT = "חשב";

	private final int WIDTH = 300;
	private final int HEIGHT = 300;

	private final JFrame WINDOW = new JFrame("");
	private  final JTextArea DAYS_FIELD = new JTextArea();
	private final JLabel NEAR_DAYS_FIELD = new JLabel();
	private final JTextArea DATE_FIELD = new JTextArea();
	private final JLabel NEAR_DATE_FIELD = new JLabel();
	private final JButton CALCULATE = new JButton();
	private final JTextArea RESULT_FIELD = new JTextArea();


	public FrontEnd(){
		WINDOW.setLayout(null);

		DAYS_FIELD.setBounds(100,25,120,20);
		DAYS_FIELD.setFont(FONT);
		HINT_TEXT_BOX.setHint(DAYS_FIELD, DAYS_FIELD_HINT);
		//PromptSupport.setPrompt(DAYS_FIELD_HINT, DAYS_FIELD);

		DATE_FIELD.setBounds(100,85,120,20);
		DATE_FIELD.setFont(FONT);
		HINT_TEXT_BOX.setHint(DATE_FIELD, DATE_FIELD_HINT);
		DATE_FIELD.requestFocus();

		CALCULATE.setFocusable(false);
		CALCULATE.setBounds(100,140,120, 20);
		CALCULATE.setFont(FONT);
		CALCULATE.setText(CALCULATE_TEXT);

		RESULT_FIELD.setFocusable(false);
		RESULT_FIELD.setBounds(100, 200, 120, 20);
		RESULT_FIELD.setFont(FONT);
		HINT_TEXT_BOX.setHint(RESULT_FIELD, RESULT_FIELD_HINT);


		WINDOW.add(DAYS_FIELD);
		WINDOW.add(DATE_FIELD);
		WINDOW.add(CALCULATE);
		WINDOW.add(RESULT_FIELD);

		WINDOW.setVisible(true);
		WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WINDOW.setSize(WIDTH, HEIGHT);
		WINDOW.setLocationRelativeTo(null);
		WINDOW.setResizable(false);
	}

	public JTextArea getDateField(){
		return DATE_FIELD;
	}

	public JTextArea getRESULT_FIELD(){
		return RESULT_FIELD;
	}

	public void setDateField(String date){
		DATE_FIELD.setText(date);
	}

	public JTextArea getDaysField(){
		return DAYS_FIELD;
	}

	public JButton getCalculateButton (){
		return CALCULATE;
	}

	public void setResult(String res){
		RESULT_FIELD.setText(res);
	}
}
