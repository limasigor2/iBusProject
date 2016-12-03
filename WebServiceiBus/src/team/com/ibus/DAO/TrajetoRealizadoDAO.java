package team.com.ibus.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ibus.com.webservice.ConectaMySQL;
import team.com.ibus.Dominio.Trajeto;
import team.com.ibus.Dominio.TrajetoRealizado;;

public class TrajetoRealizadoDAO {

	public Boolean insertTrajetoRealizado(TrajetoRealizado trajR){

		try {
			Connection conexao = ConectaMySQL.obterConexao();

			String queryInsert = "INSERT INTO trajeto_realizado (hora_saida_realizada, hora_chegada_realizada) "
								+ "VALUES (?, ?)";

			PreparedStatement preparedStm = conexao.prepareStatement(queryInsert);
			preparedStm.setDate(1, (Date) trajR.getHoraSaidaRealizada());
			preparedStm.setDate(2, (Date) trajR.getHoraChegadaRealizada());
			preparedStm.executeUpdate();

			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
