import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


public class ErrorHandler {

    public static final String NO_NUMBER_ERROR = "נא הזן מספר";
    public static final String NO_DATE_ERROR = "תאריך לא חוקי";
    private static final Color ERROR_COLOR = Color.RED;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);

    private static final ErrorHandler instance = new ErrorHandler();

    private ErrorHandler() {
    }

    public static ErrorHandler getInstance() {
        return instance;
    }

    public void setError(JLabel textArea, String error) {
        textArea.setForeground(ERROR_COLOR);
        textArea.setFont(DEFAULT_FONT);
        textArea.setToolTipText(null);
        textArea.setText(error);
    }

}
