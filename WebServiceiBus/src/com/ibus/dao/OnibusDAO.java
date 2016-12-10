package com.ibus.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.Gson;

import com.ibus.date.OnibusDate;
import com.ibus.date.PosicaoDate;
import com.ibus.dominio.Onibus;
import com.ibus.webservice.ConectaMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnibusDAO {

	public String insertOnibus(String objOnibus){

		Gson gson = new Gson();
		
		try{
			
			OnibusDate onibus = gson.fromJson(objOnibus, OnibusDate.class);

			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryInsert = "INSERT INTO onibus(placa, cor) VALUES (?, ?)";
			
			PreparedStatement preparedStatement = conexao.prepareStatement(queryInsert);
			preparedStatement.setString(1, onibus.getPlaca());
			preparedStatement.setString(2, onibus.getCor());
			preparedStatement.executeUpdate();
			
			conexao.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			return gson.toJson(false);
		}

		return gson.toJson(true);
	}

	public String deleteOnibus(String objOnibus){
		
		Gson gson = new Gson();

		try{

			OnibusDate onibus = gson.fromJson(objOnibus, OnibusDate.class);

			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryDelete = "DELETE FROM onibus WHERE id = ?";

			PreparedStatement preparedStatement = conexao.prepareStatement(queryDelete);
			preparedStatement.setInt(1, onibus.getId());
			preparedStatement.executeUpdate();
			
			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
			return gson.toJson(false);
		}	
		
		return gson.toJson(true);
	}

	public String buscarOnibus(){

		Gson gson = new Gson();
		ArrayList<OnibusDate> listaOnibus = new ArrayList<>();

		try{

			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM onibus";

			PreparedStatement preparedStatement = conexao.prepareStatement(querySelect);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				OnibusDate onibus = new OnibusDate();
				onibus.setId(resultSet.getInt(1));
				onibus.setPlaca(resultSet.getString(3));
				onibus.setCor(resultSet.getString(4));
				listaOnibus.add(onibus);
			}

			conexao.close();

		}catch(Exception e){
			e.printStackTrace();
			return gson.toJson(null);
		}

		return gson.toJson(listaOnibus);
	}

	public String updateOnibus(String objOnibus){

		Gson gson = new Gson();

		try{

			OnibusDate onibus = gson.fromJson(objOnibus, OnibusDate.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryUpdate = "UPDATE onibus SET placa = ?, cor = ? WHERE id = ?";

			PreparedStatement preparedStatement = conexao.prepareStatement(queryUpdate);
			preparedStatement.setString(1, onibus.getPlaca());
			preparedStatement.setString(2, onibus.getCor());
			preparedStatement.setInt(3, onibus.getId());
			preparedStatement.executeUpdate();

			conexao.close();

		}catch(Exception e){
			e.printStackTrace();
			return gson.toJson(false);
		}

		return gson.toJson(true);
	}
	
	public String buscarPosicaoAtualOnibus(String objOnibus){
		
		Gson gson = new Gson();
		PosicaoDate posicaoOnibus = null;
		
		try{
			
			Connection conexao = ConectaMySQL.obterConexao();
			
			OnibusDate onibus = gson.fromJson(objOnibus, OnibusDate.class);
			
			String querySelect = "SELECT p.* FROM posicao p, onibus o " +
								 "WHERE o.id = ? AND p.id = o.id_posicao";
			
			PreparedStatement preparedStatement = conexao.prepareStatement(querySelect);
			preparedStatement.setInt(1, onibus.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				posicaoOnibus = new PosicaoDate();
				posicaoOnibus.setId(resultSet.getInt(1));
				posicaoOnibus.setLatitude(resultSet.getDouble(2));
				posicaoOnibus.setLongitude(resultSet.getDouble(3));
			}

		}
		catch(Exception e){
			e.printStackTrace();
			return gson.toJson(null);
		}
		
		return gson.toJson(posicaoOnibus);
	}
}