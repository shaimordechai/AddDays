import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

/**
 * Allowing to add Hint to textArea component
 */
public class HintTextArea extends JTextArea {

    private static final Color HINT_COLOR = Color.GRAY;
    private static final Font HINT_FONT = new Font("Arial", Font.PLAIN, 18);

    private Color prevForeground;
    private Font prevFont;
    private ComponentOrientation prevComponentOrientation;

    HintTextArea() {
        super();
        this.setAlignmentY(TOP_ALIGNMENT);
    }

    public void setHint(final String hint, final ComponentOrientation componentOrientation) {
        System.out.println(this.getClass().getSimpleName() + ".setHint");
        this.prevForeground = getForeground();
        this.prevFont = getFont();
        this.prevComponentOrientation = getComponentOrientation();
        printHint(hint, componentOrientation);
        addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().trim().length() == 0) {
                    printHint(hint, componentOrientation);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    cleanHint();
                }
            }
        });
    }

    private void printHint(final String hint, final ComponentOrientation componentOrientation) {
        System.out.println(this.getClass().getSimpleName() + ".printHint");
        setForeground(HINT_COLOR);
        setFont(HINT_FONT);
        setComponentOrientation(componentOrientation);
        setText(hint);
    }

    private void cleanHint() {
        System.out.println(this.getClass().getSimpleName() + ".cleanHint");
        setForeground(prevForeground);
        setFont(prevFont);
        setComponentOrientation(prevComponentOrientation);
        setText(null);
    }
}