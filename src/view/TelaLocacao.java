package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Cliente;
import model.Reserva;
import model.Carro;
import controller.LocacaoController;

public class TelaLocacao extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private Reserva reserva;
	private Carro carro;
	private LocacaoController controller;

	
	private final JLabel lblNome = new JLabel("Nome: "),
			lblCPF = new JLabel("CPF: "),
			lblCNH = new JLabel("CNH: "),
			lblVeiculo = new JLabel("Veículo: "),
			lblPlaca = new JLabel("Placa: "),
			lblCategoria = new JLabel("Categoria: "),
			lblDiaria = new JLabel("Valor Diária: R$"),
			lblRetirada = new JLabel("Retirada: "),
			lblDevolucao = new JLabel("Devolução: "),
			lblTotal = new JLabel("Valor Total: R$");

	private final JTextField txtNome = new JTextField(),
			txtCPF = new JTextField(),
			txtCNH = new JTextField(),
			txtVeiculo = new JTextField(),
			txtPlaca = new JTextField(),
			txtCategoria = new JTextField(),
			txtDiaria = new JTextField(),
			txtRetirada = new JTextField(),
			txtDevolucao = new JTextField(),
			txtTotal = new JTextField();
			
	
	public TelaLocacao(Cliente cliente, Carro carro) {
		
		this.cliente = cliente;
		this.carro = carro;
		
		txtNome.setText(cliente.getNome());
		txtCPF.setText(cliente.getCPF());
		txtCNH.setText(cliente.getCNH());

		txtVeiculo.setText(carro.getModelo());
		txtPlaca.setText(carro.getPlaca());
		txtCategoria.setText(carro.getCategoria().getNome_categoria());
		txtDiaria.setText(carro.getCategoria().getValor_fixo_diaria().toString());
		
		this.controller = new LocacaoController(this, cliente, carro);
		
		montar();
		
	    revalidate();
	    repaint();
	}
	
	public TelaLocacao(Reserva reserva, Carro carro) {

	    this.reserva = reserva;
	    this.carro = carro;
	    this.cliente = reserva.getCliente();

	    txtNome.setText(reserva.getCliente().getNome());
	    txtCPF.setText(reserva.getCliente().getCPF());
	    txtCNH.setText(reserva.getCliente().getCNH());

	    txtVeiculo.setText(carro.getModelo());
	    txtPlaca.setText(carro.getPlaca());

	    txtCategoria.setText(carro.getCategoria().getNome_categoria());
	    txtDiaria.setText(carro.getCategoria().getValor_fixo_diaria().toString());
	    
	    this.controller = new LocacaoController(this, this.cliente, carro);
	    
	    montar();
	    revalidate();
	    repaint();
	}
	
	private void montar() {
		
		txtNome.setEditable(false);          
	    txtNome.setBorder(null);             
	    txtNome.setOpaque(false);            
	    txtNome.setFocusable(false); 
	    
		txtCPF.setEditable(false);          
	    txtCPF.setBorder(null);             
	    txtCPF.setOpaque(false);            
	    txtCPF.setFocusable(false); 
	    
		txtCNH.setEditable(false);          
	    txtCNH.setBorder(null);             
	    txtCNH.setOpaque(false);            
	    txtCNH.setFocusable(false); 
	    
	    txtVeiculo.setEditable(false);          
	    txtVeiculo.setBorder(null);             
	    txtVeiculo.setOpaque(false);            
	    txtVeiculo.setFocusable(false); 
	    
	    txtPlaca.setEditable(false);          
	    txtPlaca.setBorder(null);             
	    txtPlaca.setOpaque(false);            
	    txtPlaca.setFocusable(false); 
	    
	    txtCategoria.setEditable(false);          
	    txtCategoria.setBorder(null);             
	    txtCategoria.setOpaque(false);            
	    txtCategoria.setFocusable(false); 
	    
	    txtDiaria.setEditable(false);          
	    txtDiaria.setBorder(null);             
	    txtDiaria.setOpaque(false);            
	    txtDiaria.setFocusable(false);
	    
	    txtTotal.setEditable(false);          
	    txtTotal.setBorder(null);             
	    txtTotal.setOpaque(false);            
	    txtTotal.setFocusable(false); 
	    
	    
	    setLayout(new BorderLayout());
	    
	    JPanel centro = new JPanel();
	    centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		
	    JPanel painelCliente = new JPanel(new GridLayout(3, 2, 10, 10));
	    painelCliente.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
	    painelCliente.add(lblNome);
	    painelCliente.add(txtNome);
	    painelCliente.add(lblCPF);
	    painelCliente.add(txtCPF);
	    painelCliente.add(lblCNH);
	    painelCliente.add(txtCNH);
		
		
	    JPanel veiculo = new JPanel(new GridLayout(4, 2, 10, 10));
	    veiculo.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));
	    veiculo.add(lblVeiculo);
	    veiculo.add(txtVeiculo);
	    veiculo.add(lblPlaca);
	    veiculo.add(txtPlaca);
	    veiculo.add(lblCategoria);
	    veiculo.add(txtCategoria);
	    veiculo.add(lblDiaria);
	    veiculo.add(txtDiaria);
		
		
	    JPanel locacao = new JPanel(new GridLayout(3, 2, 10, 10));
	    locacao.setBorder(BorderFactory.createTitledBorder("Dados da Locação"));
	    locacao.add(lblRetirada);
	    locacao.add(txtRetirada);
	    locacao.add(lblDevolucao);
	    locacao.add(txtDevolucao);
	    locacao.add(lblTotal);
	    locacao.add(txtTotal);
		
		JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton confirma = new JButton("Confirmar Locação");
		
		painelBotao.add(confirma);
		
		centro.add(painelCliente);
		centro.add(veiculo);
		centro.add(locacao);
		
		centro.add(painelBotao);
		add(centro, BorderLayout.CENTER);
		
		
		confirma.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	 controller.confirmar(txtRetirada.getText(), txtDevolucao.getText()); 
		    }
		});
		
	}
	
	public void setTxtTotal(String texto) {
	    txtTotal.setText(texto);
	}
}
