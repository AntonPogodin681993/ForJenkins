package com.pogodin.springMVC.util;

import com.pogodin.springMVC.MyEcxeption.MyEmailValidException;
import com.pogodin.springMVC.dao.PersonDAO;
import com.pogodin.springMVC.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    RegExValidator regExValidator;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        regExValidator = new RegExValidator();

        if (person.getName() == ""){
            errors.rejectValue("name", "", "Should be not empty");
        }
        if (person.getName().length() > 20){
            errors.rejectValue("name", "", "It's too long name");
        }

        if (person.getAge() < 0 || person.getAge() > 120){
            errors.rejectValue("age" , "", "Incorrect age");
        }

        if (!regExValidator.nameValidate(person.getName())){
            errors.rejectValue("name", "", "Enter the correct name");
        }

        if (!regExValidator.emailValidate(person.getEmail())){
            try {
                throw new MyEmailValidException("Incorrect Email");
            } catch (MyEmailValidException e) {
                e.printStackTrace();
                e.getMessage();
            }
            errors.rejectValue("email", "", "Incorrect email");
        }



    }
}
