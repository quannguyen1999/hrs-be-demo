package com.hrs.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import static com.hrs.demo.constants.MessageErrors.DATE_INVALID;

public final class FunctionUtils {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @description Convert String to Date by format yyyy-MM-dd
     * @param string - string String
     * @return date
     * */
    public static Function<String, Date> parseStringToDate() {
        return (input) -> {
            try {
                return df.parse(input);
            } catch (ParseException e) {
                CommonValidator.badRequest().accept(DATE_INVALID);
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * @description Convert Date to String by format yyyy-MM-dd
     * @param date - date Date
     * @return string
     * */
    public static Function<Date, String> parseDateToString() {
        return df::format;
    }

}
