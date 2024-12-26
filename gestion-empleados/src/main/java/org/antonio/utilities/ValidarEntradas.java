package org.antonio.utilities;

import org.hibernate.PropertyValueException;

public class ValidarEntradas extends Exception {

    public ValidarEntradas() {
    }

    public ValidarEntradas(String message) {
        super(message);
    }
}
