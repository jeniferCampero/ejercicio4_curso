package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin("*")
@RestController
public class CursoController {

	@Autowired
	CursoService cursoService;
	
	@Operation(summary="Busqueda de todos los cursos", 
			description="Devuelve todos los cursos de la base de datos")
	@GetMapping(value="cursos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos() {
		return cursoService.cursos();
	}
	
	@Operation(summary="Busqueda de un curso por código de curso", 
			description="Devuelve el curso que coincide con el código de curso facilitado en la url")
	@GetMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@Parameter(description="Variable que contiene el código de curso por el que buscamos")
							@PathVariable("codCurso") String codCurso) {
		return cursoService.buscarCurso(codCurso);
	}
	
	@Operation(summary="Busqueda de un curso por precio", 
			description="Devuelve el curso que coincide con el precio facilitado en la url")
	@GetMapping(value="curso/precio/{precio}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarPorPrecio(@Parameter(description="Variable que contiene el precio del curso por el que buscamos")
								@PathVariable("precio") int precio) {
		return cursoService.buscarPrecio(precio);
	}
	
	@Operation(summary="Creamos un curso", 
			description="Creamos un curso con los datos que nos envian en la llamada como json")
	@PostMapping(value="curso", consumes=MediaType.APPLICATION_JSON_VALUE,  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> crearCurso(@Parameter(description="Objeto que contiene todos los datos enviados como json")
								@RequestBody Curso curso){
		return cursoService.crearCurso(curso);		
	}
	
	@Operation(summary="Actualizamos la duración de un curso", 
			description="Actualizamos la duración de un curso con los datos facilitados en la url")
	@PutMapping(value="curso/{codCurso}/{duracion}")
	public void actualizarCurso(@Parameter(description="Variable que contiene el codigo del curso que vamos actualizar")
								@PathVariable("codCurso") String codCurso,
								@Parameter(description="Variable que contiene la nueva duración del curso")
								@PathVariable("duracion") int duracion) {
		cursoService.actualizarDuracion(codCurso, duracion);
	}
	
	@Operation(summary="Eliminamos un curso", 
			description="Eliminamos un curso con los datos facilitados en la url")
	@DeleteMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminarCurso(@Parameter(description="Variable que contiene el codigo del curso que vamos a eliminar")
									@PathVariable("codCurso") String codCurso){
		return cursoService.eliminarCurso(codCurso);
	}
}
