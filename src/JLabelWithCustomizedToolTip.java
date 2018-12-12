import javax.swing.JLabel;
import javax.swing.JToolTip;

/**
 * Allowing to add custom Tool Tip
 */
public class JLabelWithCustomizedToolTip extends JLabel {

    public JLabelWithCustomizedToolTip(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    @Override
    public JToolTip createToolTip() {
        return (new CustomizedToolTip(this));
    }
}
