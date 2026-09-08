package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class TelaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public TelaPrincipal() {
		super("Locadora");
		setTitle("Sistema de Locadora");
		setLayout(new BorderLayout()); 
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		montar();
	}
	
	public void montar() {
		
		JLabel title = new JLabel("SISTEMA DE LOCAÇÃO", SwingConstants.CENTER);
		title.setFont(title.getFont().deriveFont(Font.BOLD, 30f));
		title.setBorder(BorderFactory.createEmptyBorder(90, 0, 10, 0));
		add(title, BorderLayout.NORTH);
		
		JPanel area = new JPanel();
		area.setLayout(new GridLayout(0, 1, 0, 15));
				
		area.setPreferredSize(new Dimension(300, 220)); 

		JLabel info = new JLabel("Clique em um dos botões: ",  SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(Font.BOLD, 16f));
		area.add(info);
		
		JButton nova = new JButton("Nova Locação"),
				consulta = new JButton("Consultar Reservas"),
				carro = new JButton("Consultar Carros");
		
		nova.setFont(new Font("", Font.BOLD, 13));
		consulta.setFont(new Font("", Font.BOLD, 13));
		carro.setFont(new Font("", Font.BOLD, 13));
		
		area.add(nova);
		area.add(consulta);
		area.add(carro);

		
		JPanel centralizador = new JPanel(new GridBagLayout());
		centralizador.add(area);

		add(centralizador, BorderLayout.CENTER);
	}
	
}
