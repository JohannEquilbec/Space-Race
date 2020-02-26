package test;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Test {
	
	public static void main(String [] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		f.add(p);
		p.setFocusable(true);
		p.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});
		p.setPreferredSize(new Dimension(800,600));
		f.pack();
		f.setVisible(true);
	}

}
