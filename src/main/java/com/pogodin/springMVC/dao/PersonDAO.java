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

        people.add(new Person(++PEOPLE_COUNT,"Vera"));
        people.add(new Person(++PEOPLE_COUNT,"Nicola"));
        people.add(new Person(++PEOPLE_COUNT,"Stas"));
        people.add(new Person(++PEOPLE_COUNT,"Marina"));
    }

    public   List<Person> index(){
        return people;
    }

    public Person show(int id){
        return  people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
