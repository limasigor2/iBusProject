package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Onibus;
import team.com.ibus.Dominio.Usuario;

public class OnibusDAO {

	public Boolean insertOnibus(Onibus onibus){
		try {
			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryInsert = "INSERT INTO onibus (`id_posicao`, `placa`, `cor`) "
								 + "VALUES (null, ?, ?)";
			
			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, onibus.getPlaca());
			preparedStm.setString(2, onibus.getCor());
			preparedStm.executeUpdate();
			
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
