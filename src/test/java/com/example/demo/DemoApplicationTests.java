package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.Empleado;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	
	private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void testObtenerPersonas() throws JsonMappingException, JsonProcessingException {
        String empleadosJson = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}";

        List<Empleado> empleados = employeeService.obtenerPersonas(empleadosJson);

        Assertions.assertEquals(2, empleados.size());
    }
    
    //este debe fallar
   /* @Test
    public void testObtenerPersonasFail() throws JsonMappingException, JsonProcessingException {
        String empleadosJson = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}";

        List<Empleado> empleados = employeeService.obtenerPersonas(empleadosJson);

        Assertions.assertEquals(3, empleados.size());
    }*/

    @Test
    public void testObtenerPersonasId() throws JsonMappingException, JsonProcessingException {
        String empleadoJson = "{\"status\":\"success\",\"data\":{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},\"message\":\"Successfully! Record has been fetched.\"}";

        Empleado empleado = employeeService.obtenerPersonasId(empleadoJson);

        Assertions.assertEquals(1, empleado.getId());
    }
}
