package com.proyect.commander;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.proyect.commander.model.Estado;

@Repository
public class H2Dao {

	private static final String USER = "sa";
	private static final String SERVER = "jdbc:h2:~/test";
	Connection conn = null;

	public H2Dao() {
		try {
			Connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean Connect() throws SQLException {

		conn = DriverManager.getConnection(SERVER, USER, "");
		if (conn != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean add() {
		return false;
	}

	public Estado consultaEstadoId(String id) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT * FROM ESTADOS WHERE ID=?");
		pt.setString(1, id);
		ResultSet resultSet = pt.executeQuery();
		Estado s = null;
		while(resultSet.next()) {		
			s=new Estado();
			s.setId(resultSet.getInt("ID"));
			s.setLinkLeader(resultSet.getString("LINKLEADER"));
			s.setLinkMofa(resultSet.getString("LINKMOFA"));
			s.setNombre(resultSet.getString("NOMBRE"));
			s.setPib(resultSet.getInt("PIB"));
			s.setSalMin(resultSet.getInt("SALMIN"));
			s.settFrontera(resultSet.getInt("TIPOFRONTERA"));
			s.settGob(resultSet.getInt("TIPOGOB"));
			s.setUrlBanner(resultSet.getString("URLBANNER"));
		}
		s.setNombre(getTradd(s.getId(), 1, getIdioma()));
		s.setFrontera(getTradd(s.gettFrontera(),3,getIdioma()));
		s.setGobierno(getTradd(s.gettGob(), 2, getIdioma()));

		return s;
	}

	private String getTradd(int id, int tipo, String idioma) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT DESCRIPCION FROM TRADUCCION WHERE ID=? AND TIPO=? AND IDIOMA=?");
		pt.setInt(1, id);
		pt.setInt(2, tipo);
		pt.setString(3, idioma);
		ResultSet resultSet = pt.executeQuery();
		if(resultSet.next());
		return resultSet.getString(1);
	}

	private String getIdioma() {
		//TODO: MODULO DE IDIOMA
		//El idioma debe ser una variable al loguearse que permanezca activa siempre en toda la sesion. (Parte del usuario?)
		return "ENG";
	}

}
