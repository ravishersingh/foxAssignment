package com.fox.assignment.core;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringHelper {

    /**
     * @return A unique number of size 15, up to millisecond precision.
     */
    public static String getUniqueNumber(){
        return new SimpleDateFormat("yyMMdd.HHmmss.SSS").format(new Date());
    }

    /**
     * @return Random Alphanumeric In Upper Case
     */
    public static String getRandomAlphanumericInUpperCase(int size){
        return RandomStringUtils.randomAlphanumeric(size).toUpperCase();
    }

    /**
     * @return Random Alphabets In Upper Case
     */
    public static String getRandomAlphaInUpperCase(int size){
        return RandomStringUtils.randomAlphabetic(size);
    }

    /**
     * Get Random Email.
     * @return
     */
    public static String getRandomEmail(){
        String email = getRandomAlphanumericInUpperCase(10); // Alphanumeric 10 character email
        return email.concat("@"+getRandomAlphaInUpperCase(5)+".com").toLowerCase(); // Only Alphabetic 5 character domainName
    }

}
