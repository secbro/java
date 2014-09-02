package learnSwing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class BindKeyTest {
	JFrame jf=new JFrame("Simpel test for bind");
	JTextArea ta=new JTextArea(20,40);
	JButton jb=new JButton("Transfer");
	JTextField tf=new JTextField(40);
	public void init()
	{
		jf.add(ta);
		JPanel jp=new JPanel();
		jp.add(tf);
		jp.add(jb);
		jf.add(jp,BorderLayout.SOUTH);
		
		Action action=new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				tf.setText(ta.getText());
				ta.setText("");
			}
			
		};
		jb.addActionListener(action);
		ta.getInputMap().put(KeyStroke.getKeyStroke('\n',java.awt.event.InputEvent
				.CTRL_MASK), "send");
		ta.getActionMap().put("send", action);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new BindKeyTest().init();
	}
}
