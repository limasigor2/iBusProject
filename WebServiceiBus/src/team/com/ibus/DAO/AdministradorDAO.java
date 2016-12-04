package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Administrador;

public class AdministradorDAO {
	
	public JsonElement insertAdmin(JsonElement objAdmin){
		
		String retornoMetodo = "";
		JsonElement objJsonRetorno = null;
		Gson gson = new Gson();
		
		try {
			Administrador admin = gson.fromJson(objAdmin, Administrador.class);
			
			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryInsert = "INSERT INTO administrador (nome, login, senha, cpf, codigo_acesso) "
								 + "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, admin.getNome());
			preparedStm.setString(2, admin.getLogin());
			preparedStm.setString(3, admin.getSenha());
			preparedStm.setInt(4, admin.getCpf());
			preparedStm.setInt(5, admin.getCodigoDeAcesso());
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
