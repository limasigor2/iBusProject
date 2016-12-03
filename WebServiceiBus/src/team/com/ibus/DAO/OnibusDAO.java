package team.com.ibus.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Onibus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnibusDAO {

	public Boolean inserirOnibus(String objOnibus){

		try{

			Gson gson = new Gson();
			Onibus onibus = gson.fromJson(objOnibus, Onibus.class);

			Connection conn = ConectaMySQL.obterConexao();

			String insertQuery = "INSERT INTO onibus(placa, cor) VALUES (?, ?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
			preparedStatement.setString(1, onibus.getPlaca());
			preparedStatement.setString(2, onibus.getCor());

			preparedStatement.executeUpdate();

			conn.close();


		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	//	public Boolean excluirOnibus(JsonObject objJsonOnibus){
	//		
	//		try{
	//
	//			Gson gson = new Gson();
	//			Onibus onibus = gson.fromJson(objJsonOnibus, Onibus.class);
	//
	//			Connection conn = ConectaMySQL.obterConexao();
	//
	//			String insertQuery = "DELETE FROM onibus WHERE id = ?";
	//
	//			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	//			preparedStatement.setInt(1, onibus.getId());
	//
	//			preparedStatement.executeUpdate();
	//
	//			conn.close();
	//
	//
	//		}catch(Exception ex){
	//			ex.printStackTrace();
	//			return false;
	//		}
	//
	//		return true;
	//	}
	//
	//	public String buscarTodosOsOnibus(){
	//		
	//		String listaOnibusJson = "";
	//		
	//		try{
	//			
	//			ArrayList<Onibus> listaOnibus = new ArrayList();
	//			Gson gson = new Gson();
	//			
	//			Connection conn = ConectaMySQL.obterConexao();
	//			
	//			String selectQuery = "SELECT * FROM onibus";
	//			
	//			PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
	//		
	//			ResultSet resultSet = preparedStatement.executeQuery();
	//
	//			while(resultSet.next()){
	//				
	//				Onibus onibus = new Onibus();
	//				onibus.setId(resultSet.getInt(1));
	//				onibus.setPlaca(resultSet.getString(3));
	//				onibus.setCor(resultSet.getString(4));
	//				
	//				listaOnibus.add(onibus);
	//			}
	//			
	//			
	//			listaOnibusJson = gson.toJson(listaOnibus, Onibus.class);
	//			
	//			conn.close();
	//			
	//		}catch(Exception ex){
	//			
	//			ex.printStackTrace();
	//			return null;
	//		}
	//		
	//		return listaOnibusJson;
	//	}
	//
	//	public Boolean atualizarOnibus(JsonObject objJsonOnibus){
	//		
	//
	//		try{
	//
	//			Gson gson = new Gson();
	//			Onibus onibus = gson.fromJson(objJsonOnibus, Onibus.class);
	//
	//			Connection conn = ConectaMySQL.obterConexao();
	//
	//			String insertQuery = "UPDATE onibus SET placa = ?, cor = ? WHERE id = ?";
	//
	//			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	//			preparedStatement.setString(1, onibus.getPlaca());
	//			preparedStatement.setString(2, onibus.getCor());
	//			preparedStatement.setInt(3, onibus.getId());
	//
	//			preparedStatement.executeUpdate();
	//
	//			conn.close();
	//
	//		}catch(Exception ex){
	//			ex.printStackTrace();
	//			return false;
	//		}
	//
	//		return true;
	//	}

}
