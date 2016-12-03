package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Rota;
import team.com.ibus.Dominio.Usuario;

public class RotaDAO {

	public Boolean insertRota(Rota rota){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO rota (posicao_partida, posicao_destino) "
					+ "VALUES (?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setInt(1, rota.getPosPartida());
			preparedStm.setInt(2, rota.getPosDestino());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<Rota> listarRotas(){
		
		ArrayList<Rota> rotas = new ArrayList<Rota>();

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM rota";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				Rota rota = new Rota();
				rota.setId(resultQuery.getInt(1));
				rota.setPosPartida(resultQuery.getInt(2));
				rota.setPosDestino(resultQuery.getInt(3));
				rotas.add(rota);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rotas;
	}

	public Boolean deleteRota(Integer id){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM rota WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, id);
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
