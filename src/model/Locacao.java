package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Locacao {

	private int id_locacao;
	private Cliente cliente;
	private Carro carro;
	private int id_func;
	private Date data_inicio_loc;
	private Date data_fim_loc;
	private BigDecimal valor_seguro;
	private BigDecimal valor_caucao;
	private BigDecimal valor_fixo_diaria;
	private BigDecimal valor_locacao;
	private BigDecimal valor_total;

	public Locacao() {}

	public int getId_locacao() {
		return id_locacao;
	}
	public void setId_locacao(int id_locacao) {
		this.id_locacao = id_locacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public int getId_func() {
		return id_func;
	}
	public void setId_func(int id_func) {
		this.id_func = id_func;
	}
	public Date getData_inicio_loc() {
		return data_inicio_loc;
	}
	public void setData_inicio_loc(Date data_inicio_loc) {
		this.data_inicio_loc = data_inicio_loc;
	}
	public Date getData_fim_loc() {
		return data_fim_loc;
	}
	public void setData_fim_loc(Date data_fim_loc) {
		this.data_fim_loc = data_fim_loc;
	}
	public BigDecimal getValor_seguro() {
		return valor_seguro;
	}
	public void setValor_seguro(BigDecimal valor_seguro) {
		this.valor_seguro = valor_seguro;
	}
	public BigDecimal getValor_caucao() {
		return valor_caucao;
	}
	public void setValor_caucao(BigDecimal valor_caucao) {
		this.valor_caucao = valor_caucao;
	}
	public BigDecimal getValor_fixo_diaria() {
		return valor_fixo_diaria;
	}
	public void setValor_fixo_diaria(BigDecimal valor_fixo_diaria) {
		this.valor_fixo_diaria = valor_fixo_diaria;
	}
	public BigDecimal getValor_locacao() {
		return valor_locacao;
	}
	public void setValor_locacao(BigDecimal valor_locacao) {
		this.valor_locacao = valor_locacao;
	}
	public BigDecimal getValor_total() {
		return valor_total;
	}
	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}
}