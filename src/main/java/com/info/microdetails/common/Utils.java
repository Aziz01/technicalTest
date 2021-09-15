package com.info.microdetails.common;

import com.info.microdetails.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Utils {

    public static boolean isNotAdult(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
            if(Period.between(birthDate, currentDate).getYears() > 18)
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
