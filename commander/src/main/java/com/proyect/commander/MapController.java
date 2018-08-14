package com.proyect.commander;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.commander.model.Estado;

@RestController
public class MapController {
	@Autowired
	H2DaoMap dao;

	/**
	 * Devuelve toda la informacion del estado segun un id de entrada
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getStateById", method = { RequestMethod.GET })
	public Estado mod(Model model, @RequestParam Map<String, String> requestParams) throws SQLException {
		if (!dao.isConnect()) {
			dao.Connect();
		}

		return dao.consultaEstadoId(requestParams.get("ID"));

	}

	/**
	 * Crea un nuevo estado a partir de una region libre
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CreateNewState", method = { RequestMethod.GET })
	public Estado createState(Model model, @RequestParam Map<String, String> requestParams) throws Exception {

		if (!dao.isConnect()) {
			dao.Connect();
		}
		String nameState = requestParams.get("NAME_STATE") != null ? requestParams.get("NAME_STATE") : "";
		String urlBannerState = requestParams.get("URL_BANNER_STATE") != null ? requestParams.get("URL_BANNER_STATE")
				: "";
		String idRegionState = requestParams.get("ID_REGION");

		return dao.crearNuevoEstado(nameState, urlBannerState, Integer.parseInt(idRegionState));

	}

	/**
	 * Libera una region de un estado
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/LiberateRegion", method = { RequestMethod.GET })
	public boolean setFreeRegion(Model model, @RequestParam Map<String, String> requestParams) throws Exception {

		if (!dao.isConnect()) {
			dao.Connect();
		}
		return dao.setFreeRegion(Integer.parseInt(requestParams.get("ID_REGION")), Integer.parseInt(requestParams.get("ID_STATE")));
	}
	
	/**
	 * Pasa una region LIBRE a ser propiedad de un estado
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ColoniceRegion", method = { RequestMethod.GET })
	public boolean setStateRegion(Model model, @RequestParam Map<String, String> requestParams) throws Exception {

		if (!dao.isConnect()) {
			dao.Connect();
		}
		return dao.setStateRegion(Integer.parseInt(requestParams.get("ID_REGION")), Integer.parseInt(requestParams.get("ID_STATE")));
	}

}
