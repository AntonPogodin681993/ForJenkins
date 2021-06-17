package com.pogodin.springMVC.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExValidator {

    private Pattern emailPattern;
    private Pattern namePattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String NAME_PATTERN ="^[a-zA-Z]*$";

    public RegExValidator() {
        emailPattern = Pattern.compile(EMAIL_PATTERN);
        namePattern = Pattern.compile(NAME_PATTERN);
    }

    public boolean emailValidate(final String email) {
        matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean nameValidate(final String name) {
        matcher = namePattern.matcher(name);
        return matcher.matches();
    }

}
