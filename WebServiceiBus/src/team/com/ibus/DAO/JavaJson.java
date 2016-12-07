package team.com.ibus.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import team.com.ibus.Dominio.Usuario;

public class JavaJson {
	
	public static void main(String [] args){
		Usuario user = new Usuario();
		user.setId(0);
		user.setLogin("erika");
		user.setSenha("erika123");
		user.setNome("Erika");
		Integer ident = 13;
		
		Gson gson = new Gson();
		
		String aux = gson.toJson(user);
		
		//JsonElement objJson = new JsonParser().parse(aux);
		
		UsuarioDAO dao = new UsuarioDAO();
		String request = dao.insertUsuario(aux);
		String response = gson.fromJson(request, String.class);
		System.out.println(request);
	}
}
