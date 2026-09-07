package model;

import java.sql.Date;

public class Cliente {
 private String CPF;
 private String nome;
 private String CNH;
 private Date data_nasc;
 private String telefone;
 private String email;
 
 //Fiz o construtor porque o cliente será uma das poucas classes desse projeto que terá objeto
 public Cliente(String CPF, String nome, String CNH, Date data_nasc, String telefone, String email) {
	 this.CPF = CPF;
	 this.nome = nome;
	 this.CNH = CNH;
	 this.telefone = telefone;
	 this.email = email;
	 
 }
 public Cliente() {
	// TODO Auto-generated constructor stub
}
 public String getCPF() {
	return CPF;
 }
 public void setCPF(String cPF) {
	CPF = cPF;
 }
 public String getNome() {
	return nome;
 }
 public void setNome(String nome) {
	this.nome = nome;
 }
 public String getCNH() {
	return CNH;
 }
 public void setCNH(String cNH) {
	CNH = cNH;
 }
 public Date getData_nasc() {
	return data_nasc;
 }
 public void setData_nasc(Date data_nasc) {
	this.data_nasc = data_nasc;
 }
 public String getTelefone() {
	return telefone;
 }
 public void setTelefone(String telefone) {
	this.telefone = telefone;
 }
 public String getEmail() {
	return email;
 }
 public void setEmail(String email) {
	this.email = email;
 }
 
 
}
