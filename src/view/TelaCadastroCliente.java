
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import controller.ClienteController;
import model.Cliente;

import javax.swing.JButton;

public class TelaCadastroCliente extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField txtCpf = new JTextField(14),
		txtNome = new JTextField(80),
		txtCNH = new JTextField(11),
		txtData_nasc = new JTextField(16),
		txtTelefone = new JTextField(20),
		txtEmail = new JTextField(80);
	private final DefaultTableModel modelo_tabela = new DefaultTableModel(
		new Object[] {"CPF", "Nome", "CNH", "Data de Nascimento", "Telefone", "Email"}, 0	
		) {
		public boolean isCellEditable(int l, int c) {
			return false;
		}
	};
	private final JTable tabela = new JTable(modelo_tabela);
	private final ClienteController controller;
	private void adicionar(JPanel p, GridBagConstraints g, int y, String rotulo, JTextField campo) {
		g.gridx = 0;
		g.gridy = y;
		g.weightx = 0;
		g.fill = GridBagConstraints.NONE;
		p.add(new JLabel(rotulo), g);
		g.gridx = 1;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		p.add(campo, g);
	}
	
	private void montar() {
		JPanel formulario = new JPanel(new GridBagLayout());
		formulario.setBorder(
		BorderFactory.createTitledBorder("Cadastro de Cliente")
			);
		
		GridBagConstraints organizador = new GridBagConstraints();
		//Margin
		organizador.insets = new Insets(3,4,3,4);
		organizador.anchor = GridBagConstraints.WEST;
		adicionar(formulario, organizador, 0, "CPF:", txtCpf);
		adicionar(formulario, organizador, 1, "Nome:", txtNome);
		adicionar(formulario, organizador, 2, "CNH:", txtCNH);
		adicionar(formulario, organizador, 3, "Data de Nascimento:", txtData_nasc);
		adicionar(formulario, organizador, 4, "Telefone:", txtTelefone);
		adicionar(formulario, organizador, 5, "Email:", txtEmail);
		organizador.gridx = 1;
		organizador.gridy = 6;
		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton novo = new JButton("Novo"), 
				salvar = new JButton("Salvar"), 
				limpar = new JButton("Limpar"),
				excluir = new JButton("Excluir");
		botoes.add(novo);
		botoes.add(salvar);
		botoes.add(limpar);
		botoes.add(excluir);
		JPanel topo = new JPanel(new BorderLayout());
		topo.add(formulario, BorderLayout.CENTER);
		topo.add(botoes, BorderLayout.SOUTH);
		add(topo, BorderLayout.NORTH);
		JPanel centro = new JPanel(new BorderLayout());
		centro.add(new JScrollPane(tabela), BorderLayout.CENTER);
		tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		add(centro, BorderLayout.CENTER);
		novo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.novo();
					}
				}
				);
		salvar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.salvar();
					}
				}
				);
		limpar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.limpar();
					}
				}
				);
		
		excluir.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.excluir();
					}
		});
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.selecionarLinha();
			}
		});
		
		
	}
	public void limparFormulario() {
	    txtCpf.setText("");
	    txtNome.setText("");
	    txtCNH.setText("");
	    txtData_nasc.setText("");
	    txtTelefone.setText("");
	    txtEmail.setText("");
	    tabela.clearSelection();
	};
	
	public void definirEdicao(boolean b) {
	    txtCpf.setEditable(b);
	    txtNome.setEditable(b);
	    txtCNH.setEditable(b);
	    txtData_nasc.setEditable(b);
	    txtTelefone.setEditable(b);
	    txtEmail.setEditable(b);
	};
	
	public void cliente_campos(Cliente cliente) {
		txtCpf.setText(String.valueOf(cliente.getCPF()));
		txtNome.setText(cliente.getNome());
		txtCNH.setText(cliente.getCNH());
		//Formatação para questão String/Date
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(cliente.getData_nasc() != null) {
			LocalDate data = cliente.getData_nasc().toLocalDate();
			txtData_nasc.setText(data.format(formatador));
		} else {
			txtData_nasc.setText("");
		}
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
	}
	
	public void preencherTabela(List<Cliente> lista) {
		modelo_tabela.setRowCount(0);
		int i;
		for(i = 0; i < lista.size(); i++) {
			Cliente cliente = lista.get(i);
			modelo_tabela.addRow(new Object[] {
				cliente.getCPF(),
				cliente.getNome(),
				cliente.getCNH(),
				cliente.getData_nasc(),
				cliente.getTelefone(),
				cliente.getEmail(),
					
			});
		}
	};
	
	//Aqui ele starta a tela, definindo as bordas e a montando
	public TelaCadastroCliente() {
		setLayout(new BorderLayout(8,8));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		controller = new ClienteController(this);
		montar();
		controller.carregarTabela();
	}
	public JTextField getTxtCpf() {
	    return txtCpf;
	  }
	
	public JTextField getTxtNome() {
	    return txtNome;
	  }

	
	public JTextField getTxtCNH() {
	    return txtCNH;
	  }

	
	public JTextField getTxtData_nasc() {
	    return txtData_nasc;
	  }

	
	public JTextField getTxtTelefone() {
	    return txtTelefone;
	  }

	
	public JTextField getTxtEmail() {
	    return txtEmail;
	  }
	
	public JTable getTabela() {
		return tabela;
	}

}
