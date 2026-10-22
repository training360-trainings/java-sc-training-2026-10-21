package employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    @Query("select new employees.EmployeeModel(e.id, e.name) from Employee e, AclEntry a WHERE e.id = a.employeeId and a.username = ?#{authentication.name}")
    List<EmployeeModel> findAllResources();
}
