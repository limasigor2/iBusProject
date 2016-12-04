package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Usuario;

public class UsuarioDAO {

	public JsonElement insertUsuario(JsonElement objUsuario){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try {

			Usuario usuario = gson.fromJson(objUsuario, Usuario.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO usuario (nome, login, senha) "
					+ "VALUES (?, ?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, usuario.getNome());
			preparedStm.setString(2, usuario.getLogin());
			preparedStm.setString(3, usuario.getSenha());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}

	// falta JSON
	public ArrayList<Usuario> listarUsuarios(){

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM usuario";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			ResultSet resultQuery = preparedStm.executeQuery();

			while(resultQuery.next()){
				Usuario user = new Usuario();
				user.setId(resultQuery.getInt(1));
				user.setNome(resultQuery.getString(2));
				user.setLogin(resultQuery.getString(3));
				user.setSenha(resultQuery.getString(4));
				usuarios.add(user);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public JsonElement getUsuarioId(JsonElement objId){

		Usuario user = null;
		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try {

			Integer id = gson.fromJson(objId, Integer.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String querySelect = "SELECT * FROM usuario WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(querySelect);
			preparedStm.setInt(1, id);

			ResultSet resultQuery = preparedStm.executeQuery();
			if(resultQuery.next()){
				user = new Usuario();
				user.setId(resultQuery.getInt(1));
				user.setNome(resultQuery.getString(2));
				user.setLogin(resultQuery.getString(3));
				user.setSenha(resultQuery.getString(4));
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(null);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(user);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno; 

	}

	public JsonElement updateUsuario(JsonElement objUsuario){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try {
			Usuario usuario = gson.fromJson(objUsuario, Usuario.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, usuario.getNome());
			preparedStm.setString(2, usuario.getLogin());
			preparedStm.setString(3, usuario.getSenha());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}

	public JsonElement deleteUsuarioPorId(JsonElement objId){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try {
			Integer id = gson.fromJson(objId, Integer.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM usuario WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, id);
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;

	}

	public JsonElement deleteUsuario(JsonElement objUsuario){

		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();

		try {
			Usuario usuario = gson.fromJson(objUsuario, Usuario.class);

			Connection conexao = ConectaMySQL.obterConexao();

			String queryDelete = "DELETE FROM usuario WHERE id = ?";

			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, usuario.getId());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			retornoMetodo = gson.toJson(false);
			objJsonRetorno = new JsonParser().parse(retornoMetodo);
			return objJsonRetorno;
		}

		retornoMetodo = gson.toJson(true);
		objJsonRetorno = new JsonParser().parse(retornoMetodo);
		return objJsonRetorno;
	}
}
