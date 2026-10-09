import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    abstract String getEmployeeType();

    String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    String getEmployeeType() {
        return "Full-Time";
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    String getEmployeeType() {
        return "Part-Time";
    }
}

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

class LeaveRequest {
    private final Employee employee;
    private final int days;
    private LeaveStatus status = LeaveStatus.PENDING;

    LeaveRequest(Employee employee, int days) {
        this.employee = employee;
        this.days = days;
    }

    LeaveStatus getStatus() {
        return status;
    }

    void review(boolean approve) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Request already reviewed.");
            return;
        }

        status = approve ? LeaveStatus.APPROVED : LeaveStatus.REJECTED;
        System.out.println(employee.getName() + " (" 
                + employee.getEmployeeType() + "), " + days
                + " days: " + status);
    }
}

class LeaveManagementSystem {
    private final List<LeaveRequest> requests = new ArrayList<>();

    LeaveRequest submitRequest(Employee employee, int days) {
        if (days <= 0) {
            System.out.println("Leave days must be positive.");
            return null;
        }

        LeaveRequest request = new LeaveRequest(employee, days);
        requests.add(request);

        System.out.println("Leave request submitted for "
                + employee.getName() + ": PENDING");

        return request;
    }
}

public class EmployeeLeaveWorkflow {
    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();

        Employee e1 = new FullTimeEmployee("Arun");
        Employee e2 = new PartTimeEmployee("Priya");

        LeaveRequest r1 = system.submitRequest(e1, 3);
        LeaveRequest r2 = system.submitRequest(e2, 2);

        if (r1 != null) r1.review(true);
        if (r2 != null) r2.review(false);

        // A reviewed request cannot change its final status.
        if (r1 != null) r1.review(false);
    }
}
