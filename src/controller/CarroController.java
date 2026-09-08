package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.CarroDAO;
import model.Carro;
import view.TelaConsultaCarro;


public class CarroController {
	private final TelaConsultaCarro tela;
	private final CarroDAO dao;
	
	public CarroController(TelaConsultaCarro tela) {
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
	        //Aqui você assume Mitsuo
	        //telaLocacao = new TelaLocacao(reserva);
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
}}
