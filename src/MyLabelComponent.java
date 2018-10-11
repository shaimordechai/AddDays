import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

public class MyLabelComponent extends JLabel {

    MyLabelComponent(){
        super();
    }
    MyLabelComponent(ImageIcon icon){
        super(icon);
    }
    @Override
    public JToolTip createToolTip(){
        return (new MyJToolTip(this));
    }
}
