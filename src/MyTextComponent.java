import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

// Allowing to add Hint to textArea component

public class MyTextComponent extends JTextArea {

	private static final Color HINT_COLOR = Color.GRAY;
	private static final Font HINT_FONT = new Font("Arial", Font.PLAIN, 18);

	private Color prevForeground;
	private Font prevFont;
	private ComponentOrientation prevComponentOrientation;
	MyTextComponent(){
		super();
		setAlignmentY(LEFT_ALIGNMENT);
	}

	public void setHint(final String hint, final ComponentOrientation componentOrientation) {
		{
			System.out.println(this.getClass().getSimpleName() + ".setHint");
			prevForeground = this.getForeground();
			prevFont = this.getFont();
			prevComponentOrientation = this.getComponentOrientation();
			printHint(hint, componentOrientation);
			this.addFocusListener(new FocusListener() {
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
	}


	private void printHint(final String hint, final ComponentOrientation componentOrientation) {
		System.out.println(this.getClass().getSimpleName() + ".printHint");
		this.setForeground(HINT_COLOR);
		this.setFont(HINT_FONT);
		this.setComponentOrientation(componentOrientation);
		this.setText(hint);		
	}

	private void cleanHint() {
		System.out.println(this.getClass().getSimpleName() + ".cleanHint");
		setForeground(prevForeground);
		setFont(prevFont);
		setComponentOrientation(prevComponentOrientation);
		setText(null);
	}
}
