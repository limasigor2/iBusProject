package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Administrador;

public class AdministradorDAO {
	
	public Boolean insertAdmin(Administrador admin){
		
		try {
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
			return false;
		}
		
		return true;
	}
}
