package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Trajeto;

public class TrajetoDAO {

	public Boolean insertTrajeto(Trajeto traj){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO trajeto (hora_saida_prevista, hora_chegada_prevista) "
					+ "VALUES (?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setDate(1, (Date) traj.getHoraSaidaPrevista());
			preparedStm.setDate(2, (Date) traj.getHoraChegadaPrevista());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<Trajeto> listarTrajetos(){

		ArrayList<Trajeto> trajetos = new ArrayList<Trajeto>();

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM trajeto";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				Trajeto traj = new Trajeto();
				traj.setId(resultQuery.getInt(1));
				traj.setHoraSaidaPrevista(resultQuery.getDate(2));
				traj.setHoraChegadaPrevista(resultQuery.getDate(3));
				trajetos.add(traj);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return trajetos;
	}

	public Boolean updateTrajeto(Trajeto traj){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryUpdate = "UPDATE trajeto set hora_saida_prevista = ?, hora_chegada_prevista = ? WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryUpdate);
			preparedStm.setDate(1, (Date) traj.getHoraSaidaPrevista());
			preparedStm.setDate(2, (Date) traj.getHoraChegadaPrevista());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Boolean deleteTrajeto(Integer id){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM trajeto WHERE id = ?";

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
