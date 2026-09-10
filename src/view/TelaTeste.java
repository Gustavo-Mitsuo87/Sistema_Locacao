package view;

import javax.swing.JFrame;

public class TelaTeste extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelaTeste() {
		setTitle("Teste");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(new TelaLocacao());
		setVisible(true);
	}
	
}
