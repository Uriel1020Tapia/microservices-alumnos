package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.services.IAlumnoService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;

/**
* @author: URTAAV
*/

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService>{

	
    // Update Alumno with PUT
    @PutMapping
	public ResponseEntity<?> editar(@RequestBody Alumno alumnoReq,@PathVariable Long id){
	Optional<Alumno> o = service.findById(id);
		
		if(o == null) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoResp = o.get();
		alumnoResp.setNombre(alumnoReq.getNombre());
		alumnoResp.setApellido(alumnoReq.getApellido());
		alumnoResp.setEmail(alumnoReq.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoResp));
	}
}
