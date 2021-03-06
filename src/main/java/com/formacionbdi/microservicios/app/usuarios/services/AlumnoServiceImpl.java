package com.formacionbdi.microservicios.app.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.usuarios.models.repository.IAlumnoRepository;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService{

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String query) {
		
		return repository.findByNombreOrApellido(query);
	}

}
