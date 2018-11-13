import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

// Allowing to add custom Tool Tip

public class MyLabelComponent extends JLabel {

    MyLabelComponent(){
        super();
    }
    MyLabelComponent(ImageIcon icon){
        super(icon);
    }
    MyLabelComponent(String text, int horizontalAlignment){
    	super(text, horizontalAlignment);
    }
    @Override
    public JToolTip createToolTip(){
        return (new MyJToolTip(this));
    }
}
