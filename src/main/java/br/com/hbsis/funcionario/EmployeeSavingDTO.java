package br.com.hbsis.funcionario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeSavingDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSavingDTO.class);
    private String employeeName;
    private String employeeUuid;


    public EmployeeSavingDTO() {
    }
    public EmployeeSavingDTO(String employeeName, String employeeUuid) {
        this.employeeName = employeeName;
        this.employeeUuid = employeeUuid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeUuid() {
        return employeeUuid;
    }

    public void setEmployeeUuid(String employeeUuid) {
        this.employeeUuid = employeeUuid;
    }


}
