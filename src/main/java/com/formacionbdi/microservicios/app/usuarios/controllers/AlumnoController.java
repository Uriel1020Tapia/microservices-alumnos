package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.services.IAlumnoService;

/**
* @author: URTAAV
*/

@RestController
@RequestMapping("api/v1/")
public class AlumnoController {

	@Autowired
	private IAlumnoService alumnoService;
	
	 // List All Alumnos
    @RequestMapping(value = "alumnos", method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		return  ResponseEntity.ok().body(alumnoService.findAll());
	}
    // List One Alumno
    @RequestMapping(value = "alumnos/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> ver(@PathVariable Long id){
		
		Optional<Alumno> o = alumnoService.findById(id);
		
		if(o == null) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(o.get());
	}
	
 // Create New Alumno
    @RequestMapping(value = "alumnos", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoResp = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoResp);
	}
	
    // Update Alumno with PUT
    @RequestMapping(value = "alumnos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editar(@RequestBody Alumno alumnoReq,@PathVariable Long id){
	Optional<Alumno> o = alumnoService.findById(id);
		
		if(o == null) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoResp = o.get();
		alumnoResp.setNombre(alumnoReq.getNombre());
		alumnoResp.setApellido(alumnoReq.getApellido());
		alumnoResp.setEmail(alumnoReq.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoResp));
		
	}
	
 // Delete Alumno
    @RequestMapping(value = "alumnos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
