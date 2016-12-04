package team.com.ibus.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Onibus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnibusDAO {

	public JsonElement insertOnibus(JsonElement objOnibus){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try{

			Onibus onibus = gson.fromJson(objOnibus, Onibus.class);

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
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}

	public JsonElement deleteOnibus(JsonElement objOnibus){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try{

			Onibus onibus = gson.fromJson(objOnibus, Onibus.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM onibus WHERE id = ?";

			PreparedStatement preparedStatement = conexao.prepareStatement(queryDelete);
			preparedStatement.setInt(1, onibus.getId());
			preparedStatement.executeUpdate();

			conexao.close();

		}catch(Exception ex){
			ex.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}

	public JsonElement buscarTodosOsOnibus(){

		Gson gson = new Gson();
		JsonElement objJsonRetorno = null;
		String listaOnibusRetorno = "";
		ArrayList<Onibus> listaOnibus = new ArrayList();

		try{

			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM onibus";

			PreparedStatement preparedStatement = conexao.prepareStatement(querySelect);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				Onibus onibus = new Onibus();
				onibus.setId(resultSet.getInt(1));
				onibus.setPlaca(resultSet.getString(3));
				onibus.setCor(resultSet.getString(4));
				listaOnibus.add(onibus);
			}

			conexao.close();

		}catch(Exception ex){
			ex.printStackTrace();
			listaOnibusRetorno = gson.toJson(null);
			objJsonRetorno = new JsonParser().parse(listaOnibusRetorno);
			return objJsonRetorno;
		}

		listaOnibusRetorno = gson.toJson(listaOnibus);
		objJsonRetorno = new JsonParser().parse(listaOnibusRetorno);
		return objJsonRetorno;
	}


	public JsonElement updateOnibus(JsonElement objOnibus){
		
		Gson gson = new Gson();
		JsonElement objJsonRetorno = null;
		String retornoMetodo = "";
		
		try{
			
			Onibus onibus = gson.fromJson(objOnibus, Onibus.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryUpdate = "UPDATE onibus SET placa = ?, cor = ? WHERE id = ?";

			PreparedStatement preparedStatement = conexao.prepareStatement(queryUpdate);
			preparedStatement.setString(1, onibus.getPlaca());
			preparedStatement.setString(2, onibus.getCor());
			preparedStatement.setInt(3, onibus.getId());
			preparedStatement.executeUpdate();

			conexao.close();

		}catch(Exception ex){
			ex.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}

}
