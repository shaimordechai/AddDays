import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class HintTextBox {
    static final Color HINT_COLOR = Color.GRAY;
    static  final Font HINT_FONT = new Font("Arial", Font.PLAIN, 16);

    public void setHint(JTextComponent textComponent, String hint){
        textComponent.setForeground(HINT_COLOR);
        textComponent.setFont(HINT_FONT);
        textComponent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textComponent.setText(hint);
        textComponent.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if(textComponent.getText().trim().length() == 0){
                    textComponent.setForeground(HINT_COLOR);
                    textComponent.setText(hint);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if(textComponent.getText().equals(hint)){
                    textComponent.setText("");
                }
            }
        });
    }
}
