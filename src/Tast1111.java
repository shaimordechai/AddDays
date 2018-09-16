import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tast1111 {
	public static void main(String[] args) {
		JFrame WINDOW = new JFrame();
		ImageIcon image = new ImageIcon("D:\\Downloads\\qm.png");
		JLabel INFO = new JLabel(image);
		WINDOW.setLayout(new FlowLayout());
		WINDOW.add(INFO);
		WINDOW.pack();
		WINDOW.setVisible(true);
	}

}
