package com.ibus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;

import com.ibus.date.TrajetoDate;
import com.ibus.date.TrajetoRotaDate;
import com.ibus.dominio.Posicao;

import com.ibus.webservice.ConectaMySQL;

public class TrajetoDAO {

	public String insertTrajeto(String objTrajeto){

		String retornoMetodo = "";
		Gson gson = new Gson();
		
		try {
			
			TrajetoDate trajeto = gson.fromJson(objTrajeto, TrajetoDate.class);
			
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
		ArrayList<TrajetoDate> trajetos = new ArrayList<TrajetoDate>();

		try {
			
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM trajeto";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				TrajetoDate trajeto = new TrajetoDate();
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
	
	public String buscarTrajetoRota(){

		Gson gson = new Gson();
		ArrayList<TrajetoRotaDate> listaTrajetosERotas = new ArrayList<TrajetoRotaDate>();

		try {
			
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT t.id, t.hora_saida_prevista, t.hora_chegada_prevista, "
							   + "r.id " +
								 "FROM trajeto t, rota r WHERE t.id_rota = r.id";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				TrajetoRotaDate trajetoRota = new TrajetoRotaDate();
				trajetoRota.setHoraSaidaPrevista(resultQuery.getDate(2));
				trajetoRota.setHoraChegadaPrevista(resultQuery.getDate(3));
				
				String querySelectPosPartida = "SELECT p.* FROM posicao p, rota r " +
											   "WHERE r.id = ? and p.id = r.posicao_partida";
				preparedStm = conexao.prepareStatement(querySelectPosPartida);
				preparedStm.setInt(1, resultQuery.getInt(4));
				ResultSet resultSelectPosPartida = preparedStm.executeQuery();
				
				while(resultSelectPosPartida.next()){
					Posicao posPartida = new Posicao(resultSelectPosPartida.getDouble(2), resultSelectPosPartida.getDouble(3));
					trajetoRota.setPosicaoPartida(posPartida);
				}
				
				String querySelectPosDestino = "SELECT p.* FROM posicao p, rota r " +
						   					   "WHERE r.id = ? and p.id = r.posicao_destino";
				preparedStm = conexao.prepareStatement(querySelectPosDestino);
				preparedStm.setInt(1, resultQuery.getInt(4));
				ResultSet resultSelectPosDestino = preparedStm.executeQuery();
				
				while(resultSelectPosDestino.next()){
					Posicao posDetino = new Posicao(resultSelectPosDestino.getDouble(2), resultSelectPosDestino.getDouble(3));
					trajetoRota.setPosicaoDestino(posDetino);
				}
				
				listaTrajetosERotas.add(trajetoRota);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(null);
		}

		return gson.toJson(listaTrajetosERotas);
	}

	public String updateTrajeto(String objTrajeto){
		
		Gson gson = new Gson();
		String retornoMetodo = "";
		
		try {
			
			TrajetoDate trajeto = gson.fromJson(objTrajeto, TrajetoDate.class);
			
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
			
			TrajetoDate trajeto = gson.fromJson(objTrajeto, TrajetoDate.class);
			
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
