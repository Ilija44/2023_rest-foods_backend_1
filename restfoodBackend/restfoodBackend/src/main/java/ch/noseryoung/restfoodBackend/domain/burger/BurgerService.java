package ch.noseryoung.restfoodBackend.domain.burger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {

    @Autowired
    private BurgerRepository repository;


    public List<Burger> getAllProducts(){return repository.findAll();}
    public Burger addBurger(Burger burger) {
        return repository.save(burger);
    }

    public Burger getBurgerById(int index) {
        return repository.findById(index).orElse(null);
    }


    public List<Burger> getBurger() {
        return repository.findAll();
    }

    public void updateBurger(Burger burger) {
        repository.save(burger);
    }

    public void deleteBurgerById(int index) {
        repository.deleteById(index);
    }


}
