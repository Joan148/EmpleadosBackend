package pe.cibertec.gestion.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cibertec.gestion.empleados.model.Employee;

@Repository
public interface EmpleoyeeRepository extends JpaRepository<Employee, Long> {

}
