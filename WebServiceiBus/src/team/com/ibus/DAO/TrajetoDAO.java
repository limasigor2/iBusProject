package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Trajeto;

public class TrajetoDAO {

	public String insertTrajeto(String objTrajeto){

		String retornoMetodo = "";
		Gson gson = new Gson();
		
		try {
			
			Trajeto trajeto = gson.fromJson(objTrajeto, Trajeto.class);
			
			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO trajeto (id_onibus, id_rota, hora_saida_prevista, hora_chegada_prevista) "
					+ "VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setInt(1, trajeto.getIdOnibus());
			preparedStm.setInt(2, trajeto.getIdRota());
			preparedStm.setDate(3, (Date) trajeto.getHoraSaidaPrevista());
			preparedStm.setDate(4, (Date) trajeto.getHoraChegadaPrevista());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String buscarTrajetos(){

		Gson gson = new Gson();
		String listaTrajetosRetorno = "";
		ArrayList<Trajeto> trajetos = new ArrayList<Trajeto>();

		try {
			
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM trajeto";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				Trajeto trajeto = new Trajeto();
				trajeto.setId(resultQuery.getInt(1));
				trajeto.setIdOnibus(resultQuery.getInt(2));
				trajeto.setIdRota(resultQuery.getInt(3));
				trajeto.setHoraSaidaPrevista(resultQuery.getDate(4));
				trajeto.setHoraChegadaPrevista(resultQuery.getDate(5));
				trajetos.add(trajeto);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			listaTrajetosRetorno = gson.toJson(null);
			return listaTrajetosRetorno;
		}

		listaTrajetosRetorno = gson.toJson(trajetos);
		return listaTrajetosRetorno;
	}

	public String updateTrajeto(String objTrajeto){
		
		Gson gson = new Gson();
		String retornoMetodo = "";
		
		try {
			
			Trajeto trajeto = gson.fromJson(objTrajeto, Trajeto.class);
			
			Connection conexao = ConectaMySQL.obterConexao();

			String queryUpdate = "UPDATE trajeto SET id_onibus = ?, id_rota = ?,"
					+ "hora_saida_prevista = ?, hora_chegada_prevista = ? WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryUpdate);
			preparedStm.setInt(1, trajeto.getIdOnibus());
			preparedStm.setInt(2, trajeto.getIdRota());
			preparedStm.setDate(3, (Date) trajeto.getHoraSaidaPrevista());
			preparedStm.setDate(4, (Date) trajeto.getHoraChegadaPrevista());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String deleteTrajeto(String objTrajeto){

		String retornoMetodo = "";
		Gson gson = new Gson();
		
		try {
			
			Trajeto trajeto = gson.fromJson(objTrajeto, Trajeto.class);
			
			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM trajeto WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, trajeto.getId());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;

	}
}
