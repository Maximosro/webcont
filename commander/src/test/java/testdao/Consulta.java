package testdao;

import com.proyect.commander.H2Dao;

public class Consulta {

	private static H2Dao dao = new H2Dao();

	public static void main(String[] args) {
		try {
			dao.Connect();
			dao.consultaEstadoId("1");
			dao.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
