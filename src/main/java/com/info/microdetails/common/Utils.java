package com.info.microdetails.common;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static boolean isNotAdult(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if (Period.between(birthDate, currentDate).getYears() > 18)
            return true;
        else {
            return false;
        }


    }

    public static LocalDate stringToDate(String date) throws
            DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
