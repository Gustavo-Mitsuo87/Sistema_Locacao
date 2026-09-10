package controller;


import view.TelaPrincipal;
import view.TelaConsultaReserva;

import javax.swing.JOptionPane;

import view.TelaCadastroCliente;
import view.TelaConsultaCarro;

public class PrincipalController {

	private TelaPrincipal tela;

	public PrincipalController(TelaPrincipal t) {
		this.tela = t;
	}

	public void novaLocacao() {
		
	    int resposta = JOptionPane.showConfirmDialog(
	            null,
	            "O cliente já possui uma pré-reserva?",
	            "Nova Locação",
	            JOptionPane.YES_NO_OPTION
	        );

	        if (resposta == JOptionPane.YES_OPTION) {
	            tela.mostrarTela(new TelaConsultaReserva());
	        } else {
	            tela.mostrarTela(new TelaCadastroCliente());
	        }

	}

	public void consultaReserva() {
		tela.mostrarTela(new TelaConsultaReserva());
		
	}

	public void consutaCarro() {
		tela.mostrarTela(new TelaConsultaCarro());
		
	}
	
}
