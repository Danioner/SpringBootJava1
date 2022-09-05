package com.example.demo.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {
    //TODO> Jpa/Hipernate > Database
    //TODO> DAO is equal to CRUD

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Stuward", LocalDate.now().minusYears(30)));
        users.add(new User(3, "Richard", LocalDate.now().minusYears(25)));
        users.add(new User(4, "Lorte", LocalDate.now().minusYears(10)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }
}
