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
public class H2DaoMap {

	private static final String USER = "sa";
	private static final String SERVER = "jdbc:h2:~/test";
	Connection conn = null;

	/**
	 * CONSTRUCTOR
	 */
	public H2DaoMap() {
		try {
			Connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * CONEXIONES
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean isConnect() throws SQLException {
		return !conn.isClosed();
	}

	public void close() throws SQLException {
		conn.close();
	}

	public void Connect() throws SQLException {
		conn = DriverManager.getConnection(SERVER, USER, "");
	}

	public boolean add() {
		return false;
	}

	/**
	 * Consulta el estado a partir del ID y devuelve toda la informacion de este
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * Devuelve el idioma del usuario
	 * 
	 * @return
	 */
	private String getIdioma() {
		// TODO: MODULO DE IDIOMA
		// El idioma debe ser una variable al loguearse que permanezca activa siempre en
		// toda la sesion. (Parte del usuario?)
		return "ENG";
	}

	/**
	 * Consulta la lista de regiones asociadas al IDEstad (al estado) y devuelve
	 * toda la info sobre ellas.
	 * 
	 * @param idEstado
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Region> getListaRegiones(int idEstado) throws SQLException {
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

	public Estado crearNuevoEstado(String nombre, String urlbanner, int idRegion) throws Exception {
		Region r = getRegionId(idRegion, 0); // 0 son regiones libres, sin estado.
		int id=0;
		String insertState = "INSERT INTO ESTADOS (ID,NOMBRE,URLBANNER,TIPOFRONTERA,TIPOGOB) VALUES(?,?,?,?,?)";
		if (r != null) {
			//Creamos el nuevo estado
			PreparedStatement pt = conn.prepareStatement(insertState);
			id=genIdState("ESTADOS");
			pt.setInt(1, id);
			pt.setString(2, nombre != null ? nombre : r.getNombre()); //Pone el de la region por defecto
			pt.setString(3, urlbanner!=null?urlbanner:r.getUrlbanner());//pone el de la region por defecto
			pt.setInt(4, 1);
			pt.setInt(5, 1);
			pt.execute();
			
			//Le asociamos la region
			setStateRegion(idRegion	,id);
			
		} else {
			throw new Exception("The region its not free to make a new state");
		}
		
		return consultaEstadoId(id+"");
	}

	// Genera el id secuencial
	private int genIdState(String tabla) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT MAX(*) FROM ?");
		pt.setString(1, tabla);
		ResultSet r = pt.executeQuery();
		if (r.next()) {
			return r.getInt(1) + 1;
		} else {
			return 0;
		}

	}

	/**
	 * Devuelve toda la info de una region
	 * 
	 * @param idRegion
	 * @param idEstado
	 * @return
	 * @throws SQLException
	 */
	public Region getRegionId(int idRegion, int idEstado) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("SELECT * FROM REGIONES WHERE IDESTADO=? AND ID=? ");
		pt.setInt(1, idEstado);
		pt.setInt(2, idRegion);
		ResultSet resultSet = pt.executeQuery();
		Region r = null;
		while (resultSet.next()) {
			r = new Region();
			r.setId(resultSet.getInt("ID"));
			r.setIdForeingState(resultSet.getInt("IDESTADO"));
			r.setIdResource(resultSet.getInt("IDRESCURSO"));
			r.setNombre(resultSet.getString("NOMBRE"));
			r.setNombreResource(getRecurso(r.getIdResource(), getIdioma()));
			r.setUrlbanner(resultSet.getString("URLBANNER"));
		}

		return r;
	}

	/**
	 * Hace la region libre PONIENDO EL ESTADO A 0 QUE ES "NINGUNO"
	 * 
	 * @param idRegion
	 * @param idEstado
	 * @return
	 * @throws SQLException
	 */
	public boolean setFreeRegion(int idRegion, int idEstado) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("UPDATE REGIONES SET IDESTADO=0,WHERE ID=? AND IDESTADO=?");
		pt.setInt(1, idRegion);
		pt.setInt(2, idEstado);
		int resultSet = pt.executeUpdate();
		if (resultSet > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setStateRegion(int idRegion, int idEstado) throws SQLException {
		PreparedStatement pt = conn.prepareStatement("UPDATE REGIONES SET IDESTADO=?,WHERE ID=? AND IDESTADO=0");
		pt.setInt(1, idEstado);
		pt.setInt(2, idRegion);
		int resultSet = pt.executeUpdate();
		if (resultSet > 0) {
			return true;
		} else {
			return false;
		}
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
	 * 
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
