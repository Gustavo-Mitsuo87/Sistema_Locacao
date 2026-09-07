package view;

import javax.swing.JFrame;

public class TelaTeste extends JFrame{
	public TelaTeste() {
		setTitle("Teste");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(new TelaCadastroCliente());
		setVisible(true);
	}
	
}
