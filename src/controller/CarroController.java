package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.CarroDAO;
import model.Carro;
import model.Cliente;
import model.Reserva;
import view.TelaConsultaCarro;
import view.TelaLocacao;
import view.TelaPrincipal;
import view.TelaTeste;


public class CarroController {
	private final TelaConsultaCarro tela;
	private final CarroDAO dao;
	private Cliente cliente;
	private Reserva reserva;
	
	public CarroController(TelaConsultaCarro tela, Cliente cliente) {
		this.cliente = cliente;
		this.tela = tela;
		this.dao = new CarroDAO();
	}
	
	public CarroController(TelaConsultaCarro tela, Reserva reserva) {
		this.reserva = reserva;
		this.tela = tela;
		this.dao = new CarroDAO();
	}
	//Selecionar e buscar
	public void carregarTabela() {
		consultar(false);
	}
	
	public void buscar() {
		consultar(true);
	}
	
	private void consultar(boolean filtro) {
		try {
			List<Carro> l = filtro
			        ? dao.buscarPorPlaca(tela.getTxtBusca().getText())
			        : dao.listarTodos();
			      tela.preencherTabela(l);
			
			
		} catch (SQLException e) {
			erro(e);
		}
	}
	private void erro(Exception e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
	
	private void mensagem(String m, int tipo) {
		JOptionPane.showMessageDialog(tela, m, "Consulta de Carros", tipo);
	}
	public void selecionarLinha() {
		int linha = tela.getTabela().getSelectedRow();

	    if (linha < 0) {
	        mensagem("Selecione uma carro.", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    String status = (String) tela.getTabela().getValueAt(linha, 5);
	    if (status.equals("ALUGADO") || status.equals("EM MANUTENÇÃO")) {
	        mensagem(
	            "Esse carro não pode ser selecionado, pois está " + status + ".",
	            JOptionPane.WARNING_MESSAGE
	        );
	        return;
	    }

	    String placa = (String) tela.getTabela().getValueAt(linha, 0);

	    try {
	        List<Carro> carro = dao.buscarPorPlaca(placa);
	        if (carro == null) {
	            mensagem("Carro não encontrado.", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	      
	        try {
				//Pegando a lista de clientes e transformando em objeto para ser passado para a próxima tela
				Carro carro_selecionado = carro.get(0);
				TelaPrincipal j_frame = (TelaPrincipal) tela.getTopLevelAncestor();
				if (cliente != null) {
					j_frame.mostrarTela(new TelaLocacao(cliente, carro_selecionado));
				} else {
					j_frame.mostrarTela(new TelaLocacao(reserva, carro_selecionado));
				}
				
			} catch(Exception e) {
				erro(e);
			}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
}}
