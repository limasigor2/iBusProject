package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Usuario;

public class UsuarioDAO {
	
	public Boolean insertUsuario(Usuario usuario){
		
		try {
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
			return false;
		}
		
		return true;
	}
	
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

	public Usuario getUsuarioId(Integer id){
		Usuario user = null;
		
		try {
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
			else{
				return user;
			}
			
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user; 
	}
	
	public Boolean updateUsuario(Usuario usuario){
		
		try {
			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryInsert = "UPDATE usuario set `nome` = ?, `login` = ?, `senha` = ? WHERE id = ?";
			
			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setString(1, usuario.getNome());
			preparedStm.setString(2, usuario.getLogin());
			preparedStm.setString(3, usuario.getSenha());
			preparedStm.executeUpdate();
			
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Boolean deleteUsuario(Integer id){
		
		try {
			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryDelete = "DELETE FROM usuario WHERE id = ?";
			
			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, id);
			preparedStm.executeUpdate();
			
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	
	}
	
	public Boolean deleteUsuario(Usuario usuario){
		
		try {
			Connection conexao = ConectaMySQL.obterConexao();
			
			String queryDelete = "DELETE FROM usuario WHERE id = ?";
			
			PreparedStatement preparedStm = conexao.prepareStatement(queryDelete);
			preparedStm.setInt(1, usuario.getId());
			preparedStm.executeUpdate();
			
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
