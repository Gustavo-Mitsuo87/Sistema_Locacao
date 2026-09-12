package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Cliente;
import model.Carro;

import javax.swing.*;

public class TelaResumoLocacao extends JPanel {

	private static final long serialVersionUID = 1L;

	private TelaPrincipal principal;

	public TelaResumoLocacao(TelaPrincipal principal, int codigo, Cliente cliente, Carro carro, String retirada,
			String devolucao, String total) {
		this.principal = principal;
		montar();
		preencher(codigo, cliente, carro, retirada, devolucao, total);
	}

	private void preencher(int codigo, Cliente cliente, Carro carro, String retirada, String devolucao, String total) {
		txtCodigo.setText(String.valueOf(codigo));
		txtNome.setText(cliente.getNome());
		txtVeiculo.setText(carro.getModelo());
		txtPlaca.setText(carro.getPlaca());
		txtCategoria.setText(carro.getCategoria().getNome_categoria());
		txtRetirada.setText(retirada);
		txtDevolucao.setText(devolucao);
		txtTotal.setText(total);
	}

	private final JLabel lblCodigo = new JLabel("Código da locação: "), lblNome = new JLabel("Cliente: "),
			lblVeiculo = new JLabel("Veiculo: "), lblPlaca = new JLabel("Placa: "),
			lblCategoria = new JLabel("Categoria: "), lblRetirada = new JLabel("Retirada: "),
			lblDevolucao = new JLabel("Devolução: "), lblTotal = new JLabel("Valor total: R$");

	private final JTextField txtCodigo = new JTextField(), txtNome = new JTextField(), txtVeiculo = new JTextField(),
			txtPlaca = new JTextField(), txtCategoria = new JTextField(), txtRetirada = new JTextField(),
			txtDevolucao = new JTextField(), txtTotal = new JTextField();

	private void montar() {

		setLayout(new BorderLayout());

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		JPanel lbl = new JPanel(new GridLayout(8, 2, 10, 10));
		lbl.setBorder(BorderFactory.createTitledBorder("LOCAÇÃO REALIZADA"));

		txtCodigo.setEditable(false);
		txtCodigo.setBorder(null);
		txtCodigo.setOpaque(false);
		txtCodigo.setFocusable(false);

		txtNome.setEditable(false);
		txtNome.setBorder(null);
		txtNome.setOpaque(false);
		txtNome.setFocusable(false);

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

		txtRetirada.setEditable(false);
		txtRetirada.setBorder(null);
		txtRetirada.setOpaque(false);
		txtRetirada.setFocusable(false);

		txtDevolucao.setEditable(false);
		txtDevolucao.setBorder(null);
		txtDevolucao.setOpaque(false);
		txtDevolucao.setFocusable(false);

		txtTotal.setEditable(false);
		txtTotal.setBorder(null);
		txtTotal.setOpaque(false);
		txtTotal.setFocusable(false);

		lbl.add(lblCodigo);
		lbl.add(txtCodigo);

		lbl.add(lblNome);
		lbl.add(txtNome);

		lbl.add(lblVeiculo);
		lbl.add(txtVeiculo);

		lbl.add(lblPlaca);
		lbl.add(txtPlaca);

		lbl.add(lblCategoria);
		lbl.add(txtCategoria);

		lbl.add(lblRetirada);
		lbl.add(txtRetirada);

		lbl.add(lblDevolucao);
		lbl.add(txtDevolucao);

		lbl.add(lblTotal);
		lbl.add(txtTotal);

		centro.add(lbl);

		JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton voltar = new JButton("Voltar");
		painelBotao.add(voltar);

		centro.add(painelBotao);
		add(centro, BorderLayout.CENTER);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarTelaPrincipal();
			}
		});
	}

}
