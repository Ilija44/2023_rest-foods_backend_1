package ch.noseryoung.restfoodBackend.burger;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/burger")
@Log4j2
@CrossOrigin(origins = "http://localhost:8080")
public class BurgerController {
    @Autowired

    BurgerService service;

    /**

     Diese Methode sortiert eine Liste von Burgern basierend auf dem angegebenen Attribut und der Sortierreihenfolge.
     @param toSort Die zu sortierende Liste von Burgern.
     @param attribute Das Attribut, nach dem die Burger sortiert werden sollen. Gültige Werte sind "relevance", "price" und "name".
     @param order Die Sortierreihenfolge. Gültige Werte sind "asc" für aufsteigend und "desc" für absteigend.
     @return Die sortierte Liste von Burgern.
     */

    public List<Burger> sort(List<Burger> toSort, String attribute, String order) {
        try {
            Collections.sort(toSort, new Comparator<Burger>() {
                @Override
                public int compare(Burger m1, Burger m2) {
                    if (attribute.equals("relevance")) {
                        if (order.equals("asc")) {
                            return Integer.compare(m1.getRelevance(), m2.getRelevance());
                        } else {
                            return Integer.compare(m2.getRelevance(), m1.getRelevance());
                        }
                    } else if (attribute.equals("price")) {
                        if (order.equals("asc")) {
                            return Integer.compare(m1.getPreis(), m2.getPreis());
                        } else {
                            return Integer.compare(m2.getPreis(), m1.getPreis());
                        }
                    } else if (attribute.equals("name")) {
                        if (order.equals("desc")) {
                            return m2.getName().compareTo(m1.getName());
                        } else {
                            return m1.getName().compareTo(m2.getName());
                        }
                    }
                    return 0;
                }
            });

        } catch (Exception e) {
            log.error("Invalid Arguments");
        }
        return toSort;

    }

    /** Diese Methode filtert eine Liste von Burgern basierend auf dem angegebenen Filterkriterium.
     *  @param toFilter Die zu filternde Liste von Burgern.
     *  @param filterStr Das Filterkriterium, das angewendet werden soll. Das Filterkriterium sollte im Format "Name;Preis;Vegetarisch" sein,
     *  @return Die gefilterte Liste von Burgern, die den angegebenen Filterkriterien entsprechen. */
    public List<Burger> filter(List<Burger> toFilter, String filterStr) {
        try {
            filterStr = filterStr.toLowerCase();

            String arr[] = null;
            arr = filterStr.split(";");

            List<Burger> filtertList = new ArrayList<>();

            for (int i = 0; i < toFilter.size(); i++) {
                if (arr[0].equals("null") || toFilter.get(i).getName().toLowerCase().contains(arr[0])) {
                    if (arr[1].equals("null") || String.valueOf(toFilter.get(i).getPreis()).toLowerCase().equals(arr[1])) {
                        if (arr[2].equals("null") || String.valueOf(toFilter.get(i).isVegetarian()).toLowerCase().equals(arr[2])) {
                            filtertList.add(toFilter.get(i));
                        }
                    }
                }
            }


            return filtertList;
        } catch (Exception e) {
            log.error("Invalid Arguments");
            return toFilter;
        }
    }


    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Retrieves a list of all burgers from the database", description = "Retrieves a list of all burgers currently stored in the database. Returns a 200 status code with a list of burgers if the query is successful. Access to this endpoint requires the 'READ' authority.")
    public ResponseEntity<List<Burger>> getBurgers() {
        log.info("Fetching all burgers from DB");
        return ResponseEntity.ok().body(service.getAllProducts());
    }

    @Operation(summary = "Fetch all Burgers", description = "With this method, you can fetch all the burgers from the database.")
    @GetMapping("/advanced")
    public ResponseEntity<List<Burger>> getBurgersSorted(@Valid @RequestParam("attribute") String attribute,
                                                         @Valid @RequestParam("order") String order,
                                                         @Valid @RequestParam("filter") String filter) {
        log.info("Fetching sorted burgers from DB attributes: " + attribute + ", " + order + ", " + filter);

        List<Burger> listFiltered = filter(service.getAllProducts(), filter);
        List<Burger> listSorted = sort(listFiltered, attribute, order);

        return ResponseEntity.ok().body(listSorted);
    }


    @GetMapping("/{burgerId}")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Retrieves an burger from the database with a given ID", description = "Retrieves an existing burger from the database with the given address ID. Returns a 200 status code with the address object if the ID is found in the database, or a 404 status code if the ID is not found. Access to this endpoint requires the 'READ' authority.")
    public ResponseEntity<Burger> getBurgerById(@PathVariable("burgerId") Integer burgerId) {
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
    public ResponseEntity<Object> deleteBurger(@PathVariable("burgerId") Integer burgerId) {
        log.info("Endpoint for deleting Address with ID {} was called.", burgerId);
        service.deleteBurgerById(burgerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{burgerId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @Operation(summary = "Updates an burger in the database with a given ID", description = "Updates an existing burger in the database with the given burger ID. Returns a 401 error code if the ID is not found in the database. Access to this endpoint requires the 'ADMIN' role.")
    public void updateBurger(@Valid @PathVariable("burgerId") int burgerId, @RequestBody Burger burger) {
        log.info("Endpoint for updating burger with ID {} was called.", burgerId);
        service.updateBurger(burger);
    }

    //  @ExceptionHandler(AddressNotFoundException.class)
    // public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException e) {
    //log.info(e.getMessage());
    //     return ResponseEntity.status(404).body(e.getMessage());
    // }

}

