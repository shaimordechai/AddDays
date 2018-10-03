import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class MyTextComponent extends JTextArea {
    static final Color HINT_COLOR = Color.GRAY;
    static final Font HINT_FONT = new Font("Arial", Font.PLAIN, 20);

    public MyTextComponent(){
        super();
    }

    public MyTextComponent(int rows, int columns){
        super(rows, columns);
        this.setWrapStyleWord(true);
        this.setLineWrap(false);
    }
    public void setHint(String hint, ComponentOrientation componentOrientation) {
        {
            Color prevForeground = this.getForeground();
            Font prevFont = this.getFont();
            ComponentOrientation prevComponentOrientation = this.getComponentOrientation();
            setForeground(HINT_COLOR);
            setFont(HINT_FONT);
            setComponentOrientation(componentOrientation);
            setText(hint);
            addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().trim().length() == 0) {
                        setForeground(HINT_COLOR);
                        setFont(HINT_FONT);
                        setComponentOrientation(componentOrientation);
                        setText(hint);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(hint)) {
                        setForeground(prevForeground);
                        setFont(prevFont);
                        setComponentOrientation(prevComponentOrientation);
                        setText(null);
                    }
                }
            });
        }
    }
}
