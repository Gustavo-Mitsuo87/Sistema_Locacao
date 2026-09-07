package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Cliente;
import model.Reserva;
import util.Conexao;

public class ReservaDAO {
private Reserva mapear(ResultSet rs) throws SQLException {
	Reserva reserva = new Reserva();
	reserva.setCodigo_reserva(rs.getInt("codigo_reserva"));
	//Para tratar os fk da tabela
	Cliente cliente = new Cliente();
	cliente.setCPF(rs.getString("cpf_clie"));
	reserva.setCliente(cliente);
	
	Categoria categoria = new Categoria();
	categoria.setId_categoria(rs.getInt("id_categoria"));
	reserva.setCategoria(categoria);
	reserva.setData_inicio_reserv(rs.getDate("data_inicio_reserva"));
	reserva.setData_fim_reserv(rs.getDate("data_fim_reserva"));
	reserva.setData_reserva(rs.getDate("data_reserva"));
	//ATIVA, CONVERTIDA OU CANCELADA
	reserva.setStatus_reserva(rs.getString("status_reserva"));
	return reserva;
	
	
}

private List<Reserva> consultar(String sql, Object parametro) throws SQLException {
	List<Reserva> lista = new ArrayList<Reserva>();
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

public Reserva buscarPorCodigo(int codigo) throws SQLException {
	List<Reserva> lista = consultar("SELECT * FROM reserva WHERE codigo_reserva = ?", Integer.valueOf(codigo));
	return lista.isEmpty() ? null : lista.get(0);
}
public List<Reserva> listarTodos() throws SQLException {
	return consultar("SELECT * FROM reserva", null);
}
}
