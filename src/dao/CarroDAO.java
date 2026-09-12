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
		
		categoria.setNome_categoria(rs.getString("nome_categoria"));
		categoria.setValor_fixo_diaria(rs.getBigDecimal("valor_fixo_diaria"));
		categoria.setValor_seguro(rs.getBigDecimal("valor_seguro"));
		categoria.setValor_caucao(rs.getBigDecimal("valor_caucao"));
		
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
		String sql = "SELECT c.*, cat.nome_categoria, cat.valor_fixo_diaria, cat.valor_seguro, cat.valor_caucao " +
		             "FROM carro c " +
		             "INNER JOIN categoria_carro cat ON c.id_categoria = cat.id_categoria " +
		             "WHERE TRIM(c.placa) LIKE ?";
		return consultar(sql, "%" + placa.trim() + "%");
	}


	public List<Carro> listarTodos() throws SQLException {
		String sql = "SELECT c.*, cat.nome_categoria, cat.valor_fixo_diaria, cat.valor_seguro, cat.valor_caucao " +
		             "FROM carro c " +
		             "INNER JOIN categoria_carro cat ON c.id_categoria = cat.id_categoria";
		return consultar(sql, null);
	}
}
