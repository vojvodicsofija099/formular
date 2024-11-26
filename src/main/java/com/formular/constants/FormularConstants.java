package com.formular.constants;

import java.util.Map;

public class FormularConstants {

    public static final String VALUE_NOT_NULL = "{filed} : vrednost ne sme biti null";

    public static String getMessage(String message, Map<String, String> params) {

        for(Map.Entry<String, String> entry : params.entrySet()) {
            message = message.replace("{"+ entry.getKey() + "}", entry.getValue());
        }
        return message;
    }
}
