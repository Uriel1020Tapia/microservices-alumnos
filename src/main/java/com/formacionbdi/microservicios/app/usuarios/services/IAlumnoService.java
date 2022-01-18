package com.formacionbdi.microservicios.app.usuarios.services;

import java.util.List;

import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno>{

	public List<Alumno> findByNombreOrApellido(String query);
}
