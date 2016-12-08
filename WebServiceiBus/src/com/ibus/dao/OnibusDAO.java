package com.ibus.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.ibus.date.OnibusDate;
import com.ibus.webservice.ConectaMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnibusDAO {

	public String insertOnibus(String objOnibus){

		String retornoMetodo = "";
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
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String deleteOnibus(String objOnibus){
		
		String retornoMetodo = "";
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
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}	
		
		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String buscarOnibus(){

		Gson gson = new Gson();
		String listaOnibusRetorno = "";
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
			listaOnibusRetorno = gson.toJson(null);
			return listaOnibusRetorno;
		}

		listaOnibusRetorno = gson.toJson(listaOnibus);
		return listaOnibusRetorno;
	}

	public String updateOnibus(String objOnibus){

		Gson gson = new Gson();
		String retornoMetodo = "";

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
			retornoMetodo = gson.toJson(false);
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}
}