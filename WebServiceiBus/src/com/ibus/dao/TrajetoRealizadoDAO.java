package com.ibus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.ibus.date.TrajetoRealizadoDate;
import com.ibus.webservice.ConectaMySQL;

public class TrajetoRealizadoDAO {

	public String insertTrajetoRealizado(String objTrajetoRealizado){
		
		String retornoMetodo = "";
		Gson gson = new Gson();
		
		try {
			
			TrajetoRealizadoDate trajetoR = gson.fromJson(objTrajetoRealizado, TrajetoRealizadoDate.class);
			
			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO trajeto_realizado (traj_previsto, hora_saida_realizada, hora_chegada_realizada) "
								+ "VALUES (?, ?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setInt(1, trajetoR.getTrajPrevisto());
			preparedStm.setDate(2, (Date) trajetoR.getHoraSaidaRealizada());
			preparedStm.setDate(3, (Date) trajetoR.getHoraChegadaRealizada());
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
