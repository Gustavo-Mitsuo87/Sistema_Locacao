package model;

import java.sql.Date;

public class Reserva {
private int codigo_reserva;
private Cliente cliente;
private Categoria categoria;
private Date data_reserva;
private Date data_inicio_reserv;
private Date data_fim_reserv;
private String status_reserva;

public int getCodigo_reserva() {
	return codigo_reserva;
}
public void setCodigo_reserva(int codigo_reserva) {
	this.codigo_reserva = codigo_reserva;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}
public Date getData_reserva() {
	return data_reserva;
}
public void setData_reserva(Date data_reserva) {
	this.data_reserva = data_reserva;
}
public Date getData_inicio_reserv() {
	return data_inicio_reserv;
}
public void setData_inicio_reserv(Date data_inicio_reserv) {
	this.data_inicio_reserv = data_inicio_reserv;
}
public Date getData_fim_reserv() {
	return data_fim_reserv;
}
public void setData_fim_reserv(Date data_fim_reserv) {
	this.data_fim_reserv = data_fim_reserv;
}
public String getStatus_reserva() {
	return status_reserva;
}
public void setStatus_reserva(String status_reserva) {
	this.status_reserva = status_reserva;
}


}
