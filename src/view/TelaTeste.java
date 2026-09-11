package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		add(new TelaCadastroCliente());
		setVisible(true);
	}
	
	
	
}
