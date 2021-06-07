package com.pogodin.springMVC.dao;

import com.pogodin.springMVC.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Olga"));
        people.add(new Person(++PEOPLE_COUNT,"Marry"));
        people.add(new Person(++PEOPLE_COUNT,"Nikita"));
        people.add(new Person(++PEOPLE_COUNT,"Anton"));
    }

    public   List<Person> index(){
        return people;
    }

    public Person show(int id){
        return  people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
