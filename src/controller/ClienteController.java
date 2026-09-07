package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import model.Cliente;
import view.TelaCadastroCliente;

public class ClienteController {

	private final TelaCadastroCliente tela;
	private final ClienteDAO dao;
	public boolean editando;
	
	public ClienteController(TelaCadastroCliente tela) {
		this.tela = tela;
		this.dao = new ClienteDAO();
	}
	
	public void novo() {
		tela.limparFormulario();
		tela.definirEdicao(true);
		editando = false;
		tela.getTxtCpf().requestFocus();
	}
	
	public void limpar() {
		tela.limparFormulario();
		tela.definirEdicao(true);
		editando = false;
	}
	
	public void carregarTabela() {
		consultar();
	}
	
	public void salvar() {
		try {
			Cliente cliente = lerFormulario();
			if (editando) {
				dao.atualizar(cliente);
				mensagem("Cliente atualizado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				dao.salvar(cliente);;
				mensagem("Cliente cadastrado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
			}
			limpar();
			carregarTabela();
		} catch (Exception e) {
			erro(e);
		}
		
	}
	private Cliente lerFormulario() {
		Cliente cliente = new Cliente();
		cliente.setCPF(tela.getTxtCpf().getText().trim());
		cliente.setNome(tela.getTxtNome().getText().trim());
		cliente.setCNH(tela.getTxtCNH().getText().trim());
		String txtData = tela.getTxtData_nasc().getText().trim();
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data_convertida = LocalDate.parse(txtData, formatador);
		java.sql.Date dataSql = java.sql.Date.valueOf(data_convertida);
		cliente.setData_nasc(dataSql);
		cliente.setEmail(tela.getTxtEmail().getText().trim());
		cliente.setTelefone(tela.getTxtTelefone().getText().trim());
		return cliente;
	}
	
	private void consultar() {
		try{
			List<Cliente> lista = dao.listarTodos();
			tela.preencherTabela(lista);
		} catch (SQLException e) {
			erro(e);
		}
	}
	
	private void mensagem(String m, int tipo) {
		JOptionPane.showMessageDialog(tela, m, "Gestão de Carros", tipo);
	}
	
	private void erro(Exception e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
	
	public void selecionarLinha() {
		  int linha = tela.getTabela().getSelectedRow();

		    if (linha < 0) return;
		    
		    
		    Cliente cliente = new Cliente();

		    cliente.setCPF((String) tela.getTabela().getValueAt(linha, 0));
		    cliente.setNome((String) tela.getTabela().getValueAt(linha, 1));
		    cliente.setCNH((String) tela.getTabela().getValueAt(linha, 2));
		    cliente.setData_nasc((Date) tela.getTabela().getValueAt(linha, 3));
		    cliente.setTelefone((String) tela.getTabela().getValueAt(linha, 4));
		    cliente.setEmail((String) tela.getTabela().getValueAt(linha, 5));
		    
		    editando = true;
		    tela.getTxtCpf().setEditable(false);

		    tela.cliente_campos(cliente);
	}
}
