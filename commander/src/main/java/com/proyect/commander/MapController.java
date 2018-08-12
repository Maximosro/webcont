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
	H2Dao dao;

	@RequestMapping(value = "/getEstadoId", method = { RequestMethod.GET })
	public Estado mod(Model model, @RequestParam Map<String, String> requestParams) {
		Estado es = null;
		try {
			dao.Connect();
			es = dao.consultaEstadoId(requestParams.get("ID"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return es;

	}

}
