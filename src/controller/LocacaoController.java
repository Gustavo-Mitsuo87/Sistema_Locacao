package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.LocacaoDAO;
import model.Carro;
import model.Cliente;
import model.Locacao;
import view.TelaLocacao;
import view.TelaPrincipal;
import view.TelaResumoLocacao;

public class LocacaoController {

	private final TelaLocacao tela;
	private final Cliente cliente;
	private final Carro carro;
	private final LocacaoDAO dao;

	public LocacaoController(TelaLocacao tela, Cliente cliente, Carro carro) {
		this.tela = tela;
		this.cliente = cliente;
		this.carro = carro;
		this.dao = new LocacaoDAO();
	}

	public void confirmar(String retiradaTexto, String devolucaoTexto) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			Date dataRetirada = sdf.parse(retiradaTexto);
			Date dataDevolucao = sdf.parse(devolucaoTexto);

			long dias = (dataDevolucao.getTime() - dataRetirada.getTime()) / (1000L * 60 * 60 * 24);

			if (dias <= 0) {
				mensagem("A data de devolução deve ser depois da retirada.");
				return;
			}

			BigDecimal diaria = carro.getCategoria().getValor_fixo_diaria();
			BigDecimal total = diaria.multiply(BigDecimal.valueOf(dias));

			Locacao locacao = new Locacao();
			locacao.setCliente(cliente);
			locacao.setCarro(carro);
			locacao.setId_func(1);  // Deixei fixo pra facilitar
			locacao.setData_inicio_loc(new java.sql.Date(dataRetirada.getTime()));
			locacao.setData_fim_loc(new java.sql.Date(dataDevolucao.getTime()));
			locacao.setValor_seguro(carro.getCategoria().getValor_seguro());
			locacao.setValor_caucao(carro.getCategoria().getValor_caucao());
			locacao.setValor_fixo_diaria(diaria);
			locacao.setValor_locacao(total);
			locacao.setValor_total(total); // Está sem somar seguro/caução

			dao.inserir(locacao);

			tela.setTxtTotal(total.toString());

			TelaPrincipal principal = (TelaPrincipal) tela.getTopLevelAncestor();
			principal.mostrarTela(new TelaResumoLocacao(
					principal, locacao.getId_locacao(), cliente, carro,
					retiradaTexto, devolucaoTexto, total.toString()));

		} catch (ParseException e) {
			mensagem("Datas inválidas. Use o formato dd/MM/yyyy.");
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem("Não foi possível salvar a locação.\n" + e.getMessage());
		}
	}

	private void mensagem(String m) {
		JOptionPane.showMessageDialog(tela, m);
	}
}