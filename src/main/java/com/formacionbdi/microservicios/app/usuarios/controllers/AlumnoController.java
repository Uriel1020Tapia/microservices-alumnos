package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.services.IAlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;

/**
 * @author: URTAAV
 */

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {

	// Update Alumno with PUT
	@PutMapping
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumnoReq, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Alumno> o = service.findById(id);

		if (o == null) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoResp = o.get();
		alumnoResp.setNombre(alumnoReq.getNombre());
		alumnoResp.setApellido(alumnoReq.getApellido());
		alumnoResp.setEmail(alumnoReq.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoResp));
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
}
