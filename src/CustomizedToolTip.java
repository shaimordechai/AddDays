import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JToolTip;

public class CustomizedToolTip extends JToolTip {

    public CustomizedToolTip(JComponent component) {
        super();
        setComponent(component);
        setBackground(Color.WHITE);
        setForeground(Color.GRAY);
    }
}
