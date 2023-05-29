package com.gestion.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.entity.Empleado;
import com.gestion.empleados.exception.ResourceNotFoundException;
import com.gestion.empleados.repository.IEmpleado;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins ="http://localhost:4200")
public class EmpleadoControlador {
	
	@Autowired
	private IEmpleado emp;
	
	//Este metodo sirve para mostrar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> listarLosEmpleado(){
		return emp.findAll();
	}

	// este metodo sirve para guardar empleados
	@PostMapping("/empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return emp.save(empleado);
	}

	// Este metodo sirve para filtrar empleados por Id
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
		Empleado empleado = emp.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con id: " + id));
		return ResponseEntity.ok(empleado);
	}

	// Este metodo sirve para filtrar empleados por Id
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado detallesEmpleado , @PathVariable Long id) {
		Empleado empleado = emp.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con id: " + id));
		
		empleado.setNombre(detallesEmpleado.getNombre());
		empleado.setApellido(detallesEmpleado.getApellido());
		empleado.setEmail(detallesEmpleado.getEmail());
		
		Empleado empleadoActualizado = emp.save(empleado);

		
		return ResponseEntity.ok(empleadoActualizado);
	}
	
	  @DeleteMapping("/empleados/{id}")
	    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") Long id){
		
		  emp.deleteById(id);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	
	

}
