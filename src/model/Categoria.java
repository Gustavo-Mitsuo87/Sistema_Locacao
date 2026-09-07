package model;

import java.math.BigDecimal;

public class Categoria {
	private int id_categoria;
	private String nome_categoria;
	private String descricao;
	private BigDecimal valor_fixo_diaria;
	private BigDecimal valor_caucao;
	private BigDecimal valor_seguro;
	
	public Categoria() {}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor_fixo_diaria() {
		return valor_fixo_diaria;
	}

	public void setValor_fixo_diaria(BigDecimal valor_fixo_diaria) {
		this.valor_fixo_diaria = valor_fixo_diaria;
	}

	public BigDecimal getValor_caucao() {
		return valor_caucao;
	}

	public void setValor_caucao(BigDecimal valor_caucao) {
		this.valor_caucao = valor_caucao;
	}

	public BigDecimal getValor_seguro() {
		return valor_seguro;
	}

	public void setValor_seguro(BigDecimal valor_seguro) {
		this.valor_seguro = valor_seguro;
	};
	
}
