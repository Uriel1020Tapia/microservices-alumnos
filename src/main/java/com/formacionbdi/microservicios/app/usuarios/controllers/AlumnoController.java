package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.services.IAlumnoService;

/**
* @author: URTAAV
*/

@RestController
public class AlumnoController {

	@Autowired
	private IAlumnoService alumnoService;
	
	@GetMapping("/")
	public ResponseEntity<?> listar(){
		return  ResponseEntity.ok().body(alumnoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		
		Optional<Alumno> o = alumnoService.findById(id);
		
		if(o == null) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(o.get());
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoResp = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoResp);
	}
	
	@PutMapping("/{id}")
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
	
	
	@DeleteMapping("/{id{")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
