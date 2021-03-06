package com.academy.techcenture;

import com.github.javafaker.Faker;


public class CommonMethods {

    static Faker faker = new Faker();


    public static String randomFirstName(){

        return faker.name().firstName();
    }

    public static String randomLastName(){
        return faker.name().lastName();
    }

    public static String randomZipcode(){
        return faker.address().zipCode().substring(0,5);
    }





    public static void main(String[] args) {
        System.out.println(randomFirstName());
        System.out.println(randomLastName());
        System.out.println(randomZipcode());


    }//end main
}//end CommonMethods

