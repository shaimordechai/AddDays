import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class ErrorHendler {

    static final Color ERROR_COLOR = Color.RED;
    static final String NO_NUMBER_ERROR = "נא הזן מספר";
    static final String NO_DATE_ERROR = "תאריך לא חוקי";

    public  void  setError (JLabel textArea, String error){
        textArea.setForeground(ERROR_COLOR);
        textArea.setText(error);
    }

    public static String getNoNumberError() {
        return NO_NUMBER_ERROR;
    }

    public static String getNoDateError() {
        return NO_DATE_ERROR;
    }
}
