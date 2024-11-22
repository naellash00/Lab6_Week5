package com.example.lab6employeemanagementsystem.Controller;

import com.example.lab6employeemanagementsystem.ApiResponse.ApiResponse;
import com.example.lab6employeemanagementsystem.Model.Employee;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee/system")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEmployees() {
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added Successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.set(index, employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                employees.remove(employee);
                return ResponseEntity.status(200).body(new ApiResponse("Employee Deleted Successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Employee Not Deleted Successfully"));
    }

    @GetMapping("/position/{position}")
    public ResponseEntity getEmployeeByPosition(@PathVariable String position) {
        ArrayList<Employee> samePositionEmployees = new ArrayList<>();
        if (!(position.equalsIgnoreCase("supervisor") || position.equalsIgnoreCase("coordinator")))
            return ResponseEntity.status(400).body(new ApiResponse("Enter A Valid Position. Either 'Supervisor' or 'Coordinator'"));

        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position))
                samePositionEmployees.add(employee);
        }
        return ResponseEntity.status(200).body(samePositionEmployees);
    }

    @GetMapping("/same/age/{minAge}/{maxAge}")
    public ResponseEntity sameAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
        ArrayList<Employee> sameAgeEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                sameAgeEmployees.add(employee);
            }
        }
        if (sameAgeEmployees.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Enter Valid Age"));

        return ResponseEntity.status(200).body(sameAgeEmployees);
    }

    @GetMapping("/annual/leave/{id}")
    public ResponseEntity applyForAnnualLeave(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                if (!employee.isOnLeave() && employee.getAnnualLeave() >= 1) {
                    employee.setOnLeave(true);
                    employee.setAnnualLeave(employee.getAnnualLeave() - 1);
                    return ResponseEntity.status(200).body(new ApiResponse("Applied For Annual Leave Successfully"));
                }
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Cannot Get Annual Leave"));
    }

    @GetMapping("/annual/leave/list")
    public ResponseEntity noAnnualLeaveEmployees() {
        ArrayList<Employee> noAnnualLeaveEmployeesList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAnnualLeave() == 0)
                noAnnualLeaveEmployeesList.add(employee);
        }
        return ResponseEntity.status(200).body(noAnnualLeaveEmployeesList);
    }

    @PutMapping("/promote/{supervisorID}/{employeeID}")
    public ResponseEntity promoteEmployee(@PathVariable String supervisorID, @PathVariable String employeeID) {
        boolean isSupervisor = false;
        for (Employee employee : employees) {
            if (employee.getId().equals(supervisorID)) {
                if (employee.getPosition().equalsIgnoreCase("supervisor"))
                    isSupervisor = true;
            }
        }
        if (isSupervisor) {
            for (Employee employee : employees) {
                if (employee.getId().equals(employeeID) && employee.getAge() >= 30 && !employee.isOnLeave()) {
                    employee.setPosition("Supervisor");
                    return ResponseEntity.status(200).body(new ApiResponse("Employee Promoted Successfully"));
                } //else return ResponseEntity.status(200).body(new ApiResponse("Employee Cant Be Promoted"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only Supervisors Can Promote"));
    }

}
