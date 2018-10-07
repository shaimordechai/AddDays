import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JToolTip;

public class MyJToolTip extends JToolTip {

    public MyJToolTip(JComponent component) {
        super();
        setComponent(component);
        setBackground(Color.white);
        setForeground(Color.gray);
    }
}
