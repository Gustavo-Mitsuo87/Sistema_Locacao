package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.Conexao;

public class ClienteDAO {
	//Esse método é puramente para evitar ficar reescrevendo toda hora
 private void preencher(PreparedStatement stmt, Cliente cliente) 
	 throws SQLException {
		 stmt.setString(1, cliente.getCPF().trim());
		 stmt.setString(2, cliente.getNome().trim());
		 stmt.setString(3, cliente.getCNH().trim());
		 stmt.setDate(4, cliente.getData_nasc());
		 stmt.setString(5, cliente.getTelefone().trim());
		 stmt.setString(6, cliente.getEmail().trim());
	 }
 
 public void salvar(Cliente cliente) throws SQLException {
	String sql = "INSERT INTO cliente(cpf_clie, nome_clie, cnh, data_nasc_clie, telefone_clie, email_clie) VALUES (?,?,?,?,?,?)";
	Connection conexao = null;
	PreparedStatement stmt = null;
	try {
		conexao = Conexao.abrir();
		stmt = conexao.prepareStatement(sql);
		preencher(stmt, cliente);
		stmt.executeUpdate();
		} finally {
			Conexao.fechar(conexao, stmt, null);
		}
	}
	
public void atualizar(Cliente cliente) throws SQLException {
	String sql = "UPDATE cliente SET nome_clie = ?, CNH = ?, data_nasc_clie = ?, telefone_clie = ?, email_clie = ? WHERE cpf_clie = ?";
	Connection conexao = null;
	PreparedStatement stmt = null;
	try {
		conexao = Conexao.abrir();
		stmt = conexao.prepareStatement(sql);
		 stmt.setString(1, cliente.getNome().trim());
		 stmt.setString(2, cliente.getCNH().trim());
		 stmt.setDate(3, cliente.getData_nasc());
		 stmt.setString(4, cliente.getTelefone().trim());
		 stmt.setString(5, cliente.getEmail().trim());
		 stmt.setString(6, cliente.getCPF().trim());
		stmt.executeUpdate();
		if(stmt.executeUpdate() == 0) throw new SQLException("Cliente não encontrado");
	} finally {
		Conexao.fechar(conexao, stmt, null);
	}
	
};
//Faz a consulta padrão no MySQL

private Cliente mapear(ResultSet rs) throws SQLException {
	Cliente cliente = new Cliente();
	cliente.setCPF(rs.getString("cpf_clie"));
	cliente.setNome(rs.getString("nome_clie"));
	cliente.setCNH(rs.getString("CNH"));
	cliente.setData_nasc(rs.getDate("data_nasc_clie"));
	cliente.setTelefone(rs.getString("telefone_clie"));
	cliente.setEmail(rs.getString("email_clie"));
	return cliente;
	
}

private List<Cliente> consultar(String sql, Object parametro) throws SQLException {
	List<Cliente> lista = new ArrayList<Cliente>();
	Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		conexao = Conexao.abrir();
		stmt = conexao.prepareStatement(sql);
		if (parametro instanceof Integer) stmt.setInt(1, ((Integer) parametro).intValue());
		if (parametro instanceof String) stmt.setString(1, (String) parametro);
		rs = stmt.executeQuery();
		while(rs.next()) lista.add(mapear(rs));
		return lista;
		
	} finally {
		Conexao.fechar(conexao, stmt, rs);
	}
}
public List<Cliente> listarTodos() throws SQLException {
	return consultar("SELECT * FROM cliente ORDER BY nome_clie", null);
}

 }

