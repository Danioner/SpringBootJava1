package com.example.demo.user;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Component
public class UserDao {
    //TODO> Jpa/Hipernate > Database
    //TODO> DAO is equal to CRUD
    final Supplier<User> userSupplier = () -> new User(null,null,null);
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
        //TODO> predicate para obtener el id del usuario.
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User u = users.stream().filter(predicate).findFirst().orElse(null);
        if( u == null){
            throw new UserNotFoundException(String.format("The user with id: %s not exist", id));
        }
        return u;
    }
    public User save(User user){
        user.setId( (int) users.stream().count() + 1 );
        users.add(user);
        return user;
    }

    public void deleteUser(int id){
        User u = findOne(id);
        users.remove(u);
    }
}
