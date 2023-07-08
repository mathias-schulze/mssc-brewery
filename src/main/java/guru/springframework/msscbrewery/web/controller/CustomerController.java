package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> getBeer(@PathVariable("customerId") UUID customerId){

        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerDto customer) {

        log.debug("Customer created: " + customer.getName() + " (" + customer.getId() + ")");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId}"})
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customer) {

        CustomerDto customerById = customerService.getCustomerById(customerId);

        log.debug("customer name changed from " + customerById.getName() + " to " + customer.getName());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {

        CustomerDto customerById = customerService.getCustomerById(customerId);

        log.debug("customer " + customerById.getName() + " deleted");
    }
}
