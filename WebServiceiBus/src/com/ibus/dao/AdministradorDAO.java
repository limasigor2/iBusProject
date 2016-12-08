package com.ibus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gson.Gson;
import com.ibus.date.AdministradorDate;
import com.ibus.webservice.ConectaMySQL;

public class AdministradorDAO {
	
	public String insertAdmin(String objAdmin){
		
		String retornoMetodo = "";
		Gson gson = new Gson();
		
		try {
			AdministradorDate admin = gson.fromJson(objAdmin, AdministradorDate.class);
			
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
			return retornoMetodo;
		}
		retornoMetodo = gson.toJson(true);
		return retornoMetodo;
		
	}
}
