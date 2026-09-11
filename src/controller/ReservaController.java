package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ReservaDAO;

import model.Reserva;
import view.TelaConsultaCarro;
import view.TelaConsultaReserva;
import view.TelaPrincipal;


public class ReservaController {
	private final TelaConsultaReserva tela;
	private final ReservaDAO dao;
	private List<Reserva> lista;
	
	public ReservaController(TelaConsultaReserva tela) {
		this.tela = tela;
		this.dao = new ReservaDAO();
		
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
			if(filtro) {
				Reserva reserva = dao.buscarPorCodigo(Integer.valueOf(tela.getTxtBusca().getText()));
				if(reserva != null) {
					lista = List.of(reserva);
					tela.preencherTabela(lista);
				} else {
					tela.preencherTabela(List.of());
					mensagem("Reserva não encontrada.", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
			    lista =  dao.listarTodos();
				tela.preencherTabela(lista);
			}
			
			
		} catch (SQLException e) {
			erro(e);
		}
	}
	private void erro(Exception e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
	
	private void mensagem(String m, int tipo) {
		JOptionPane.showMessageDialog(tela, m, "Consulta de Reservas", tipo);
	}
	public void selecionarLinha() {
		int linha = tela.getTabela().getSelectedRow();

	    if (linha < 0) {
	        mensagem("Selecione uma reserva.", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    String status = (String) tela.getTabela().getValueAt(linha, 6);
	    if (status.equals("CONVERTIDA") || status.equals("CANCELADA")) {
	        mensagem(
	            "Essa reserva não pode ser selecionada, pois está " + status + ".",
	            JOptionPane.WARNING_MESSAGE
	        );
	        return;
	    }

	    int codigo = (int) tela.getTabela().getValueAt(linha, 0);

	    try {
	        Reserva reserva = dao.buscarPorCodigo(codigo);
	        if (reserva == null) {
	            mensagem("Reserva não encontrada.", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        try {
				//Pegando a lista de clientes e transformando em objeto para ser passado para a próxima tela
				Reserva reserva_selecionada = lista.get(linha);
				TelaPrincipal j_frame = (TelaPrincipal) tela.getTopLevelAncestor();
				j_frame.mostrarTela(new TelaConsultaCarro(reserva_selecionada));
			} catch(Exception e) {
				erro(e);
			}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		mensagem("Não foi possível concluir a operação. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}
	
}}
