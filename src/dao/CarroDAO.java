package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Carro;
import model.Categoria;
import util.Conexao;

public class CarroDAO {
	private Carro mapear(ResultSet rs) throws SQLException {
		Carro carro = new Carro();
		carro.setPlaca(rs.getString("placa"));
		carro.setModelo(rs.getString("modelo"));
		carro.setMarca(rs.getString("marca"));
		carro.setAno(rs.getInt("ano"));
		
		Categoria categoria = new Categoria();
		categoria.setId_categoria(rs.getInt("id_categoria"));
		carro.setCategoria(categoria);
		carro.setStatus_disponibilidade(rs.getString("status_disponibilidade"));
		return carro;
		
		
	}

	private List<Carro> consultar(String sql, Object parametro) throws SQLException {
		List<Carro> lista = new ArrayList<Carro>();
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

	public List<Carro> buscarPorPlaca(String placa) throws SQLException {
		return consultar("SELECT * FROM carro WHERE TRIM(placa) LIKE ?",
      "%" + placa.trim() + "%");
	}
	public List<Carro> listarTodos() throws SQLException {
		return consultar("SELECT * FROM carro", null);
	}
}
