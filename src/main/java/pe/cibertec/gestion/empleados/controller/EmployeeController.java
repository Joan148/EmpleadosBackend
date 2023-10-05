package pe.cibertec.gestion.empleados.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.cibertec.gestion.empleados.exception.ResourceNotFoundException;
import pe.cibertec.gestion.empleados.model.Employee;
import pe.cibertec.gestion.empleados.repository.EmpleoyeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmpleoyeeRepository empleoyeeRepository;

	//Método para Listar todos los empleados
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees() {
		return empleoyeeRepository.findAll();
	}
	
	//Método para guardar un nuevo empleado
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return empleoyeeRepository.save(employee);
	}
	
	//Método para buscar un empleado por id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = empleoyeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = empleoyeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		Employee updatedEmployee = empleoyeeRepository.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
		Employee employee = empleoyeeRepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		empleoyeeRepository.delete(employee);
		Map<String, Boolean> result = new HashMap<>();
		result.put("delete",Boolean.TRUE);
		return ResponseEntity.ok(result);
	
	}
	

}
