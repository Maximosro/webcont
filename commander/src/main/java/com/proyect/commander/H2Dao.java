package com.proyect.commander;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.proyect.commander.model.Estado;
import com.proyect.commander.model.Region;

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
		while (resultSet.next()) {
			s = new Estado();
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
		s.setFrontera(getFrontera(s.gettFrontera(), getIdioma()));
		s.setGobierno(getGobierno(s.gettGob(), getIdioma()));
		s.setListaRegiones(getListaRegiones(s.getId()));
		return s;
	}

	private String getIdioma() {
		// TODO: MODULO DE IDIOMA
		// El idioma debe ser una variable al loguearse que permanezca activa siempre en
		// toda la sesion. (Parte del usuario?)
		return "ENG";
	}

	private Map<Integer, Region> getListaRegiones(int idEstado) throws SQLException {
		Map<Integer, Region> listaRegiones = new HashMap<Integer, Region>();
		PreparedStatement pt = conn.prepareStatement("SELECT * FROM REGIONES WHERE IDESTADO=?");
		pt.setInt(1, idEstado);

		ResultSet resultSet = pt.executeQuery();
		while (resultSet.next()) {
			Region r = new Region();
			r.setId(resultSet.getInt("ID"));
			r.setIdForeingState(resultSet.getInt("IDESTADO"));
			r.setIdResource(resultSet.getInt("IDRESCURSO"));
			r.setNombre(resultSet.getString("NOMBRE"));
			r.setNombreResource(getRecurso(r.getIdResource(), getIdioma()));
			r.setUrlbanner(resultSet.getString("URLBANNER"));
			listaRegiones.put(r.getId(), r);
		}

		return listaRegiones;
	}

	/**
	 * <CREATE TALBE RECURSOS(ID INT, IDIOMA VARCHAR(3), DESCRIPCION VARCHAR(100),
	 * PRIMARY KEY (ID,IDIOMA))>
	 * 
	 * @param idResource
	 * @param idioma
	 * @return
	 * @throws SQLException
	 */

	private String getRecurso(int idResource, String idioma) throws SQLException {

		PreparedStatement pt = conn.prepareStatement("SELECT DESCRIPCION FROM RECURSOS WHERE ID=? AND IDIOMA=?");
		pt.setInt(1, idResource);
		pt.setString(2, idioma);
		ResultSet resultSet = pt.executeQuery();
		resultSet.next();
		return resultSet.getString("DESCRIPCION");
	}

	/**
	 * <CREATE TALBE GOBIERNOS(ID INT, IDIOMA VARCHAR(3), DESCRIPCION
	 * VARCHAR(100),PRIMARY KEY (ID,IDIOMA))>
	 * 
	 * @param idGob
	 * @param idioma
	 * @return
	 * @throws SQLException
	 */
	private String getGobierno(int idGob, String idioma) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT DESCRIPCION FROM GOBIERNOS WHERE ID=? AND IDIOMA=?");
		pt.setInt(1, idGob);
		pt.setString(2, idioma);
		ResultSet resultSet = pt.executeQuery();
		resultSet.next();
		return resultSet.getString("DESCRIPCION");
	}

	/**
	 * <CREATE TALBE FRONTERAS (ID INT, IDIOMA VARCHAR(3), DESCRIPCION
	 * VARCHAR(100),PRIMARY KEY (ID,IDIOMA))>
	 * @param idFront
	 * @param idioma
	 * @return
	 * @throws SQLException
	 */
	private String getFrontera(int idFront, String idioma) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT DESCRIPCION FROM FRONTERAS WHERE ID=? AND IDIOMA=?");
		pt.setInt(1, idFront);
		pt.setString(2, idioma);
		ResultSet resultSet = pt.executeQuery();
		resultSet.next();
		return resultSet.getString("DESCRIPCION");
	}

}
