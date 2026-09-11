package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CarroController;

import model.Carro;
import model.Cliente;
import model.Reserva;


public class TelaConsultaCarro extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Reserva reserva;
	private final JTextField txtBusca = new JTextField(20);
	private final DefaultTableModel modelo_tabela = new DefaultTableModel(
			new Object[] {"Placa", "Modelo", "Marca", "Ano", "id_categoria", "Status"}, 0
			) {
		public boolean isCellEditable(int l, int c) {
			return false;
		}
	};
	private final JTable tabela = new JTable(modelo_tabela);
	private final CarroController controller;

	

	
	private void montar() {
		JPanel j = new JPanel(new GridBagLayout());
		j.setBorder(BorderFactory.createTitledBorder("Consulta de Carros"));
		
		GridBagConstraints organizador = new GridBagConstraints();
		organizador.insets = new Insets(3,4,3,4);
		organizador.anchor = GridBagConstraints.WEST;
		//adicionar(j, organizador, 0, "Digite o código da reserva:", txtBusca);
		organizador.gridx = 1;
		organizador.gridy = 6;
		JButton buscar = new JButton("Buscar");
		JButton selecionar = new JButton("Selecionar Reserva");
		JPanel pesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pesquisa.add(new JLabel("Digite a placa:"));
		pesquisa.add(txtBusca);
		pesquisa.add(buscar);
		JPanel centro = new JPanel(new BorderLayout());
		centro.add(pesquisa, BorderLayout.NORTH);
		centro.add(new JScrollPane(tabela), BorderLayout.CENTER);
		centro.add(selecionar, BorderLayout.SOUTH);
		add(centro, BorderLayout.CENTER);
		tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		buscar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.buscar();
					}
				}
				);
		selecionar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.selecionarLinha();
					}
				}
				);
		
		
	}
	public void limpar() {
		txtBusca.setText("");
	}
	
	public void preencherTabela(List<Carro> lista) {
		modelo_tabela.setRowCount(0);
		int i;
		for(i = 0; i < lista.size(); i++) {
			Carro carro = lista.get(i);
			modelo_tabela.addRow(new Object[] {
					carro.getPlaca(),
					carro.getModelo(),
					carro.getMarca(),
					carro.getAno(),
					carro.getCategoria().getId_categoria(),
					carro.getStatus_disponibilidade(),
		
					});
		}
	}
	
	
	
	public TelaConsultaCarro(Cliente cliente) {
		//Para receber o cliente da próxima tela
		this.cliente = cliente;
		System.out.println("Cliente recebido:");
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("CPF: " + cliente.getCPF());
		setLayout(new BorderLayout(8,8));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		controller = new CarroController(this, cliente);
		montar();
		controller.carregarTabela();
	}
	
	public TelaConsultaCarro(Reserva reserva) {
		//Para receber o cliente da próxima tela
		this.reserva = reserva;
		System.out.println("Reserva recebido:");
		System.out.println("Código: " + reserva.getCodigo_reserva());
		System.out.println("Data: " + reserva.getData_inicio_reserv());
		setLayout(new BorderLayout(8,8));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		controller = new CarroController(this, reserva);
		montar();
		controller.carregarTabela();
	}
	
	public JTextField getTxtBusca() {
		return txtBusca;
	}
	public JTable getTabela() {
		return tabela;
	}
}
