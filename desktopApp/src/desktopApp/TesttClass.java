package desktopApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class TesttClass extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesttClass frame = new TesttClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TesttClass() throws Exception {
		
		JTextField text;
		JButton button;
		JLabel label;
		
		
		JFrame jfr= new JFrame("TEKNOSUZGEC");
		jfr.getContentPane().setLayout(new FlowLayout());
		jfr.setSize(400,150);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text = new JTextField(10);
		jfr.getContentPane().add(text);
		
		button = new JButton("ALGORÝTMAYI ÇALIÞTIR");
		jfr.getContentPane().add(button);
		
		label = new JLabel("LÜtfen yorum  girin.");
		jfr.getContentPane().add(label);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				CumleTestSinif cts = new CumleTestSinif();
				String cumlee = text.getText().toString();
				
				try {
					cts.CumleTestEt(cumlee);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//label.setText("Merhaba " + cumle);
			}
		});		
		jfr.setVisible(true);
	}
	
	
	
	
	
	
	

}
