import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

    private static final String TAB = "[\t]";
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.||)(?:0?[13578]|1[02]))\\1|" +
            "(?:(?:29|30)(\\/|-|\\.||)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
            "^(?:29(\\/|-|\\.||)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
            "(?:(?:16|[2468][048]|[3579][26])00))))$|" +
            "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.||)(?:(?:0?[1-9])|" +
            "(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private final ErrorHandler errorHandler = ErrorHandler.getInstance();

    private int limit;
    private String type;
    private String hint;
    private JLabel label;

    JTextFieldLimit(int limit, String type, String hint, JLabel label) {
        super();
        this.limit = limit;
        this.type = type;
        this.hint = hint;
        this.label = label;
        checkTextOnLine();
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null || str.matches(TAB) || (getLength() + str.length()) > limit) {
            System.out.println("shai");
            return;
        }
        super.insertString(offset, str, attr);
    }

    private void checkTextOnLine() {
        addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    String Text = getText(0, getLength());
                    if (Text.matches(type) || Text.equals(hint) || Text.equals("")) {
                        errorHandler.cleanError(label);
                    } else{
                        setError();
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    String Text = getText(0, getLength());
                    if(Text.matches(type)
                            || Text.equals(hint)
                            || Text.equals("")) {
                        errorHandler.cleanError(label);
                    } else{
                        setError();
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void setError() {
        switch (type) {
            case REGEX_NUMBER:
                errorHandler.setError(label, ErrorHandler.NO_NUMBER_ERROR);
                break;
            case REGEX_DATE:
                errorHandler.setError(label, ErrorHandler.NO_DATE_ERROR);
                break;
        }
    }
}
