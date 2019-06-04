package spring.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.model.Bilet;
import spring.model.Cursa;
import spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import spring.repos.BiletRepository;
import spring.repos.CursaRepository;
import spring.repos.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@org.springframework.stereotype.Controller
//@RestController
@RequestMapping(path = "/zero")
public class Controller {

    private String template = "Hello, %s!";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CursaRepository cursaRepository;
    @Autowired
    private BiletRepository biletRepository;

    
    

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<User[]> getAllUsers() {
        Collection<User> col = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            col.add(u);
        }
        User[] ret = new User[col.size()];
        ret =  col.toArray(ret);
        return new ResponseEntity<User[]>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User save(@RequestBody User u) {
        userRepository.save(u);
        return u;
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        for (User u : userRepository.findAll()) {
            if (u.getUsername().equals(username)) {
                return new ResponseEntity<User>(u, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cursa", method = RequestMethod.GET)
    public ResponseEntity<Cursa[]> getAllCursa() {
        Collection<Cursa> collection = new ArrayList<>();
        for (Cursa c : cursaRepository.findAll()) {
            collection.add(c);
            System.out.println(c.getId());
        }
        Cursa[] toReturn = new Cursa[collection.size()];
        toReturn = collection.toArray(toReturn);
        return new ResponseEntity<Cursa[]>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/cursa", method = RequestMethod.POST)
    public Cursa saveCursa(@RequestBody Cursa cursa) {
        cursaRepository.save(cursa);
        return cursa;
    }

    @RequestMapping(value = "/cursa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByIdCursa(@PathVariable String id) {
        if (cursaRepository.existsById(Integer.parseInt(id)))
            return new ResponseEntity<String>("Cursa not found...", HttpStatus.NOT_FOUND);

        Cursa c = cursaRepository.findById(Integer.parseInt(id)).get();
        return new ResponseEntity<Cursa>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "/cursa/{id}", method = RequestMethod.PUT)
    public Cursa updateCursa(@PathVariable Integer id, @RequestBody Cursa cursa) {
        cursaRepository.deleteById(id);
        cursaRepository.save(cursa);
        return cursa;
    }

    @RequestMapping(value = "cursa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCursa(@PathVariable String id) {
        if (!cursaRepository.existsById(Integer.parseInt(id))) {
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
        cursaRepository.deleteById(Integer.parseInt(id));
        return new ResponseEntity<Cursa>(HttpStatus.OK);

    }

    @RequestMapping(value = "/bilet", method = RequestMethod.GET)
    public ResponseEntity<Bilet[]> getAllBilet() {
        Collection<Bilet> list = new ArrayList<>();
        Iterable<Bilet> iter = biletRepository.findAll();
        for (Bilet b : iter) {
            list.add(b);
        }
        Bilet[] ret = new Bilet[list.size()];
        ret = list.toArray(ret);
        return new ResponseEntity<Bilet[]>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/bilet", method = RequestMethod.POST)
    public Bilet saveBilet(@RequestBody Bilet b) {
        biletRepository.save(b);
        return b;
    }

    @GetMapping(value = "/bilet/{id}")
    public ResponseEntity<?> getByIdBilet(@PathVariable String id) {
        if (biletRepository.existsById(Integer.parseInt(id))) {
            return new ResponseEntity<Bilet>(biletRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Missing Bilet", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/bilet/{id}", method = RequestMethod.PUT)
    public Bilet updateBilet(@PathVariable String id, @RequestBody Bilet bilet) {
        biletRepository.deleteById(Integer.parseInt(id));
        biletRepository.save(bilet);
        return bilet;
    }

    @RequestMapping(value = "/bilet/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBilet(@PathVariable String id) {
        Integer ID = Integer.parseInt(id);
        if (biletRepository.existsById(ID)) {
            biletRepository.deleteById(ID);
            return new ResponseEntity<Bilet>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
        }
    }


}
