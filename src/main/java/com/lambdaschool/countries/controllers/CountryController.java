package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController
{

    @Autowired
    CountriesRepository countriesRepository;

    // http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = "application/json")
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countriesRepository.findAll().iterator().forEachRemaining(myList::add);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/{letter} **Case Sensitive**

    private List<Country> filterCountries(List<Country> empList, CheckCountry tester)
    {
        List<Country> rtnList = new ArrayList<>();
        for(Country c : empList){
            if(tester.test(c)){
                rtnList.add(c);
            }
        }
        return rtnList;
    }

    @GetMapping(value = "/names/start/{letter}", produces = "application/json")
    public ResponseEntity<?> findByLetter(@PathVariable char letter)
    {

        List<Country> myList = new ArrayList<>();
        countriesRepository.findAll().iterator().forEachRemaining(myList::add);
        List<Country> filteredList = filterCountries(myList, (c) -> c.getName().charAt(0)== letter);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }


    // http://localhost:2019/population/total
    @GetMapping(value = "/population/total", produces = "application/json")
    public ResponseEntity<?> findTotal()
    {
        List<Country> myList = new ArrayList<>();
        countriesRepository.findAll().iterator().forEachRemaining(myList::add);
        long total = 0;
        for(Country c : myList){
            total += c.getPopulation();
        }
        System.out.println("The Total Population is: " + total);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }


    // http://localhost:2019/population/min
    @GetMapping(value = "/population/min", produces = "application/json")
    public ResponseEntity<?> findMin()
    {
        Country min;
        List<Country> myList = new ArrayList<>();
        countriesRepository.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> ((int)(c1.getPopulation() - c2.getPopulation())));
        min = myList.get(0);
        System.out.println("The Min Population is: " + min.getPopulation());
        return new ResponseEntity<>(min, HttpStatus.OK);

    }


    // http://localhost:2019/population/max
    @GetMapping(value = "/population/max", produces = "application/json")
    public ResponseEntity<?> findMax()
    {

        List<Country> myList = new ArrayList<>();
        countriesRepository.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> ((int)(c2.getPopulation() - c1.getPopulation())));
        Country max = myList.get(0);
        System.out.println("The Max Population is: " + max.getPopulation());
        return new ResponseEntity<>(max, HttpStatus.OK);

    }


}
