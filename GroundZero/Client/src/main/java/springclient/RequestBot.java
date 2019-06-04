package springclient;

import model.Bilet;
import model.Cursa;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class RequestBot {

    public static final String URL = "http://localhost:8080/zero/";
    @Autowired
    private RestTemplate restTemplate;
    
    RequestBot(){
        restTemplate = new RestTemplate();
    }
    
    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            throw new ClientException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            throw new ClientException(e);
        }
    }
    //USER

    public User[] getAllUser() {
        return execute(() -> restTemplate.getForObject(URL + "users", User[].class));
    }

    public User saveUser(User user) {
        return execute(() -> restTemplate.postForObject(URL + "users", user, User.class));
    }

    public User getByUsername(String username) {
        User u =  execute(() -> restTemplate.getForObject(String.format("%s/%s", URL+"/users", username), User.class));
        return u;
    }
    //Cursa

    public Cursa[] getAllCursa() {
        return execute(() -> restTemplate.getForObject(URL + "cursa/", Cursa[].class));
    }

    public Cursa saveCursa(Cursa cursa) {
        return execute(() -> restTemplate.postForObject(URL + "cursa/", cursa, Cursa.class));
    }

    public Cursa getById(String id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL + "cursa/get", id), Cursa.class));
    }

    public void updateCursa(Cursa cursa) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL + "cursa", cursa.getId()), cursa);
            return null;
        });
    }

    public void removeCursa(Cursa cursa) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL + "cursa/", cursa));
            return null;
        });
    }

    //BILET

    public Bilet getByIdBilet(String id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL + "/bilet" + id), Bilet.class));
    }


    public Bilet addBilet(Bilet bilet) {
        return execute(() -> restTemplate.postForObject(URL + "bilet/", bilet, Bilet.class));
    }

    public void updateBilet(Bilet bilet) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL + "bilet/", bilet.getId()), bilet);
            return null;
        });
    }

    public void deleteBilet(String id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s/%s", URL, "bilet/", id));
            return null;
        });
    }
    
    
    public Bilet[] getAllBilet(){
       return execute(()->restTemplate.getForObject(URL + "bilet", Bilet[].class));
    }

}
