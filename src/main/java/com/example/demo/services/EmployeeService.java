package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.Empleado;

	@Service
	public class EmployeeService {
		
		
		
	  public List<Empleado> obtenerPersonas(String  empleadosJson) throws JsonMappingException, JsonProcessingException{
		  ObjectMapper objectMapper = new ObjectMapper();
		  Map<String, Object> jsonMap = objectMapper.readValue(empleadosJson, new TypeReference<Map<String, Object>>() {});
		  List<Map<String, Object>> data = (List<Map<String, Object>>) jsonMap.get("data");

		  List<Empleado> empleados = new ArrayList<>();

		  for (Map<String, Object> empleadoMap : data) {
		      Empleado empleado = objectMapper.convertValue(empleadoMap, Empleado.class);
		      empleado.setEmployee_anual_salary(empleado.getEmployee_salary()*12);
		      empleados.add(empleado);
		  }
		return empleados;
	 } 
	  
	  public Empleado obtenerPersonasId(String  empleadosJson) throws JsonMappingException, JsonProcessingException{
		  ObjectMapper objectMapper = new ObjectMapper();
		  JsonNode jsonNode = objectMapper.readTree(empleadosJson);
		  JsonNode dataNode = jsonNode.get("data");
		  Empleado empleado = objectMapper.convertValue(dataNode, Empleado.class);
	      empleado.setEmployee_anual_salary(empleado.getEmployee_salary()*12);

		  return empleado;
	 } 
}
