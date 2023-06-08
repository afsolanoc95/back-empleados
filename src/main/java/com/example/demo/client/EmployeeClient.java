package com.example.demo.client;

import java.util.ArrayList;
import java.util.Optional;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmployeeClient {

	@Value("${api.empleado}")
	private String employeeApiEndpoint;
	
	public Response obtenerEmpleados(){
		WebTarget wt= ClientBuilder.newClient().target(employeeApiEndpoint);
		return  wt.path("/employees").request().get();
	}
	
	public Response obtenerEmpleadoId(Integer id){
		WebTarget wt= ClientBuilder.newClient().target(employeeApiEndpoint);
		return  wt.path("/employee/"+id).request().get();
	}

}
