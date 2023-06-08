package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.EmployeeClient;
import com.example.demo.dto.Empleado;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/amaris")
public class EmployeeController {
	@Autowired
	EmployeeClient employClient;
	@Autowired
	EmployeeService employService;
	
	@GetMapping("/employee")
	public List<Empleado> obtenerPersonas() throws JsonMappingException, JsonProcessingException{
		Response response= employClient.obtenerEmpleados();
		String responseJson= response.readEntity(String.class);
		List<Empleado> empleados =new ArrayList<>();
		if(responseJson.contains("<!DOCTYPE html>")) {
			Empleado empleado =new Empleado();
			empleado.setEmployee_name("Too Many Requests");
			empleados.add(empleado);
		}else {
			empleados=employService.obtenerPersonas(responseJson);
		}
		return empleados;
	}
	
	@GetMapping("/employee/{id}")
	public Empleado obtenerPersonasId(@PathVariable("id")  Integer id) throws JsonMappingException, JsonProcessingException{
		Response response=employClient.obtenerEmpleadoId(id);
		String responseJson= response.readEntity(String.class);
		Empleado empleado =new Empleado();
		if(responseJson.contains("<!DOCTYPE html>")) {
			empleado.setEmployee_name("Too Many Requests");
		}else {
			 empleado =employService.obtenerPersonasId(responseJson);
		}
		return empleado;
	}
}
