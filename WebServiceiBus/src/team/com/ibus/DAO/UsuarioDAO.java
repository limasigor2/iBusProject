package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Onibus;
import team.com.ibus.Dominio.Usuario;

public class UsuarioDAO {

	public String insertUsuario(String objUsuario){

		Usuario usuario;
		String retornoMetodo = "";
		Gson gson = new Gson();

		try {

			usuario = gson.fromJson(objUsuario, Usuario.class);

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
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}

	public String buscarUsuarios(){

		Gson gson = new Gson();
		String listaOnibusRetorno = "";
		ArrayList<Usuario> listaUsuarios = new ArrayList();

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
				listaUsuarios.add(user);
			}

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			listaOnibusRetorno = gson.toJson(null);
			return listaOnibusRetorno;
		}

		listaOnibusRetorno = gson.toJson(listaUsuarios);
		return listaOnibusRetorno;
	}

	public String updateUsuario(String objUsuario){

		String retornoMetodo = "";
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
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}


	public String deleteUsuario(String objUsuario){

		String retornoMetodo = "";
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
			return retornoMetodo;
		}

		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
	}
}
