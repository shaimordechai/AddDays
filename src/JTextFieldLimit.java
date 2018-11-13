import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// Allowing to limited number of characters in the JText and check JText input online

public class JTextFieldLimit extends PlainDocument {

	private static final String TAB_OR_ENTER = "[r\n\t]";
	private static final String REGEX_NUMBER = "[0-9]+";
	private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|" +
			"(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
			"^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
			"(?:(?:16|[2468][048]|[3579][26])00))))$|" +
			"^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|" +
			"(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	private final ErrorHandler errorHandler = ErrorHandler.getInstance();

	private int limit;
	private int min;
	private String type;
	private String hint;
	private JLabel labelField;
	private JButton calculateButton;

	private static boolean validateDaysField = false;
	private static boolean validateDateField = false;

	JTextFieldLimit(int limit, int min, String type, String hint, JLabel label, JButton button) {
		super();
		this.limit = limit;
		this.min = min;
		this.type = type;
		this.hint = hint;
		this.labelField = label;
		this.calculateButton = button;
		checkOnline();
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		System.out.println(this.getClass().getSimpleName() + ".insertString");
		if (str == null || str.matches(TAB_OR_ENTER) || (getLength() + str.length()) > limit) {
			return;
		}
		super.insertString(offset, str, attr);
	}

	private void checkOnline() {
		System.out.println(this.getClass().getSimpleName() + ".checkOnline");
		addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					String input = getText(0, getLength());
					checkInput(input);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} finally{
					if(isValidate()){
						calculateButton.setEnabled(true);
					} else{
						calculateButton.setEnabled(false);
					}
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					String input = getText(0, getLength());
					checkInput(input);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} finally{
					if(isValidate()){
						calculateButton.setEnabled(true);
					} else{
						calculateButton.setEnabled(false);
					}
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					String input = getText(0, getLength());
					checkInput(input);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} finally{
					if(isValidate()){
						calculateButton.setEnabled(true);
					} else{
						calculateButton.setEnabled(false);
					}
				}
			}
		});
	}

	private void checkInput(String text) {
		System.out.println(this.getClass().getSimpleName() + ".checkInput");
		if(text.matches(type) && getLength() >= min){
			errorHandler.cleanError(labelField);
			setValidate(true);
		} else if (text.equals(hint) || text.trim().equals("")) {
			errorHandler.cleanError(labelField);
			setValidate(false);
		} else{
			setError();
			setValidate(false);
		}
	}

	private void setValidate(boolean status) {
		System.out.println(this.getClass().getSimpleName() + ".setValidate");
		switch (type) {
		case REGEX_NUMBER:
			validateDaysField = status;
			break;
		case REGEX_DATE:
			validateDateField = status;
			break;
		default:
			break;
		}
	}

	private boolean isValidate() {
		System.out.println(this.getClass().getSimpleName() + ".isValidate");
		return validateDateField && validateDaysField;
	}

	private void setError() {
		switch (type) {
		case REGEX_NUMBER:
			errorHandler.setError(labelField, ErrorHandler.NO_NUMBER_ERROR);
			break;
		case REGEX_DATE:
			errorHandler.setError(labelField, ErrorHandler.NO_DATE_ERROR);
			break;
		}
	}
}
