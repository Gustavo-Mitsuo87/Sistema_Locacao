package model;

public class Carro {

	private String placa;
	private String modelo;
	private String marca;
	private int ano;
	private Categoria categoria;
	private String status_disponibilidade;
	
	public Carro() {};
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getStatus_disponibilidade() {
		return status_disponibilidade;
	}
	public void setStatus_disponibilidade(String status_disponibilidade) {
		this.status_disponibilidade = status_disponibilidade;
	}
	
	
	
}
