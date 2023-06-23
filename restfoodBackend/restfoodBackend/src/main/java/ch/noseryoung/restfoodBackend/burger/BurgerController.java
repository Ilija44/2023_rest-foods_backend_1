package ch.noseryoung.restfoodBackend.burger;



import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/burger")
@Log4j2
public class BurgerController {

    @Autowired

    BurgerService service;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Retrieves a list of all burgers from the database", description = "Retrieves a list of all burgers currently stored in the database. Returns a 200 status code with a list of burgers if the query is successful. Access to this endpoint requires the 'READ' authority.")
    public ResponseEntity<Object> getBurgers() {
        log.info("The endpoint to retrieve all burgers was called and the data returned successfully.");
        return ResponseEntity.ok().body(service.getBurger());
    }

    @GetMapping("/{burgerId}")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Retrieves an burger from the database with a given ID", description = "Retrieves an existing burger from the database with the given address ID. Returns a 200 status code with the address object if the ID is found in the database, or a 404 status code if the ID is not found. Access to this endpoint requires the 'READ' authority.")
    public ResponseEntity<Burger> getBurgerById(@PathVariable("burgerId") Integer burgerId)  {
        log.info("\n\nGetting burger with ID: " + burgerId + " from the database\n");
        return ResponseEntity.ok().body(service.getBurgerById(burgerId));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE')")
    @Operation(summary = "Adds a new burger to the database", description = "Adds a new burger to the database. Access to this endpoint requires the 'CREATE' authority.")
    public void postBurger(@Valid @RequestBody Burger burger) {
        log.info("Endpoint for adding a new burger was called. Burger: {}", burger);
        service.addBurger(burger);
    }

    @DeleteMapping("/{burgerId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @Operation(summary = "Deletes an burger from the database with a given ID", description = "Deletes an existing burger from the database with the given burger ID. Returns a 204 status code if the burger was successfully deleted, or a 404 status code if the ID is not found in the database. Access to this endpoint requires the 'ADMIN' role.")
    public ResponseEntity<Object> deleteBurger(@PathVariable("burgerId") Integer burgerId)  {
        log.info("Endpoint for deleting Address with ID {} was called.", burgerId);
        service.deleteBurgerById(burgerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{burgerId}")
    @PreAuthorize("hasAuthority('UPDATE')") @Operation(summary = "Updates an burger in the database with a given ID", description = "Updates an existing burger in the database with the given burger ID. Returns a 401 error code if the ID is not found in the database. Access to this endpoint requires the 'ADMIN' role.")
    public void updateBurger(@Valid @PathVariable("burgerId") int burgerId, @RequestBody Burger burger) {
        log.info("Endpoint for updating burger with ID {} was called.", burgerId);
        service.updateBurger(burgerId, burger);
    }

  //  @ExceptionHandler(AddressNotFoundException.class)
   // public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException e) {
    //log.info(e.getMessage());
   //     return ResponseEntity.status(404).body(e.getMessage());
   // }

}

