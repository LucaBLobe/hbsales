package br.com.hbsis.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class EmployeeRest {

    @RestController
    public class ConsumeWebService {
        @Autowired
        RestTemplate restTemplate;

        @RequestMapping(value = "/template/employee", method = RequestMethod.POST)
        public String createEmploye(@RequestBody EmployeeDTO employeeDTO) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("Content-type", "application/json");
            headers.set("Authorization", "f59ff1b4-1b67-11ea-978f-2e728ce88125");
            HttpEntity<EmployeeDTO> entity = new HttpEntity<EmployeeDTO>(employeeDTO, headers);
            ResponseEntity<EmployeeSavingDTO> resultadoEmployee = restTemplate.exchange(
                    "http://10.2.54.25:9999/api/employees",  HttpMethod.POST, entity, EmployeeSavingDTO.class);
            return resultadoEmployee.getBody().getEmployeeUuid();
        }

     /* @RequestMapping(value = "/template/employee")
        public String getEmployeeList() {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);

            return restTemplate.exchange(
                    "http://10.2.54.25:9999/api/employees", HttpMethod.GET, entity, String.class).getBody();
        }*/
    }
}


