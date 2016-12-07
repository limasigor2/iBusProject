package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.PontoDeParada;

public class PontoDeParadaDAO {

	public String insertPontoParada(String objPontoDeParada){

		String retornoMetodo = "";
		Gson gson = new Gson();

		try {

			PontoDeParada pontoP = gson.fromJson(objPontoDeParada, PontoDeParada.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO ponto_parada (id_posicao, nome, descricao, endereco) "
					+ "VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setInt(1, pontoP.getIdPosicao());
			preparedStm.setString(2, pontoP.getNome());
			preparedStm.setString(3, pontoP.getDescricao());
			preparedStm.setString(4, pontoP.getEndereco());
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

	public String buscarPontosDeParada(){

		String retornoMetodo = "";
		Gson gson = new Gson();
		ArrayList<PontoDeParada> pontosDeParada = new ArrayList<PontoDeParada>();

		try {

			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM ponto_parada";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				PontoDeParada pontoP = new PontoDeParada();
				pontoP.setId(resultQuery.getInt(1));
				pontoP.setIdPosicao(resultQuery.getInt(2));
				pontoP.setNome(resultQuery.getString(3));
				pontoP.setDescricao(resultQuery.getString(4));
				pontoP.setEndereco(resultQuery.getString(5));
				pontosDeParada.add(pontoP);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(null);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(pontosDeParada);
		return retornoMetodo;
	}

	public String deletePontoParada(String objPontoDeParada){

		String retornoMetodo = "";
		Gson gson = new Gson();

		try {

			PontoDeParada pontoP = gson.fromJson(objPontoDeParada, PontoDeParada.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM ponto_parada WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, pontoP.getId());
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

	public String updatePontoParada(String objPontoDeParada){

		String retornoMetodo = "";
		Gson gson = new Gson();

		try {

			PontoDeParada pontoP = gson.fromJson(objPontoDeParada, PontoDeParada.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "UPDATE ponto_parada set nome = ?, descricao = ? WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, pontoP.getNome());
			preparedStm.setString(2, pontoP.getDescricao());
			preparedStm.setInt(3, pontoP.getId());
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
