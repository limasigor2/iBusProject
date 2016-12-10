package com.ibus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.ibus.date.RotaDate;
import com.ibus.webservice.ConectaMySQL;

public class RotaDAO {

	public String insertRota(String objRota){
		
		String retornoMetodo = "";
		Gson gson = new Gson();

		try {
			RotaDate rota = gson.fromJson(objRota, RotaDate.class);

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
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String buscarRotas(){

		Gson gson = new Gson();
		String listaRotaRetorno = "";
		ArrayList<RotaDate> listaRota = new ArrayList<RotaDate>();

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM rota";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				RotaDate rota = new RotaDate();
				rota.setId(resultQuery.getInt(1));
				rota.setPosPartida(resultQuery.getInt(2));
				rota.setPosDestino(resultQuery.getInt(3));
				listaRota.add(rota);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			listaRotaRetorno = gson.toJson(null);
			return listaRotaRetorno;
		}

		listaRotaRetorno = gson.toJson(listaRota);
		return listaRotaRetorno;

	}

	public String deleteRota(String objRota){

		String retornoMetodo = "";
		Gson gson = new Gson();

		try {
			RotaDate rota = gson.fromJson(objRota, RotaDate.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM rota WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, rota.getId());
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
