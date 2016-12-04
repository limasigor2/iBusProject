package team.com.ibus.DAO;

import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import team.com.ibus.Dominio.Onibus;

public class JavaJson {
	
	public static void main(String [] args){
//		Onibus onibus = new Onibus();
//		onibus.setCor("azul");
//		onibus.setPlaca("123489");
//		onibus.setId(0);
		
		Gson gson = new Gson();
		
		//String aux = gson.toJson(onibus);
		
		//JsonElement objJson = new JsonParser().parse(aux);
		
		OnibusDAO dao = new OnibusDAO();
		//JsonElement response = dao.inserirOnibus(objJson);
		JsonElement response = dao.buscarTodosOsOnibus();
		Type listType = new TypeToken<ArrayList<Onibus>>() {}.getType();
		ArrayList<Onibus> resposta = gson.fromJson(response, listType);
		for(Onibus o : resposta){
			System.out.println(o.getCor() + o.getPlaca() + o.getId());
		}
	}
}
