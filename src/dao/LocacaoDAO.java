package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Locacao;
import util.Conexao;

public class LocacaoDAO {

	public void inserir(Locacao locacao) throws SQLException {
		String sql = "INSERT INTO locacao " +
				"(cpf_clie, placa, id_func, data_inicio_loc, data_fim_loc, " +
				"valor_seguro, valor_caucao, valor_fixo_diaria, valor_locacao, valor_total) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet gerado = null;

		try {
			conexao = Conexao.abrir();
			stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, locacao.getCliente().getCPF());
			stmt.setString(2, locacao.getCarro().getPlaca());
			stmt.setInt(3, locacao.getId_func());
			stmt.setDate(4, locacao.getData_inicio_loc());
			stmt.setDate(5, locacao.getData_fim_loc());
			stmt.setBigDecimal(6, locacao.getValor_seguro());
			stmt.setBigDecimal(7, locacao.getValor_caucao());
			stmt.setBigDecimal(8, locacao.getValor_fixo_diaria());
			stmt.setBigDecimal(9, locacao.getValor_locacao());
			stmt.setBigDecimal(10, locacao.getValor_total());

			stmt.executeUpdate();

			gerado = stmt.getGeneratedKeys();
			if (gerado.next()) {
				locacao.setId_locacao(gerado.getInt(1));
			}

		} finally {
			Conexao.fechar(conexao, stmt, gerado);
		}
	}
}