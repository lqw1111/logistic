package com.logistic.project.controller;

import com.logistic.project.entity.Country;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Country> findAll() throws LogisticException {
        return countryService.findAll();
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Country> findByName(@PathVariable("name") String name) throws LogisticException {
        return countryService.findByName(name);
    }

    @RequestMapping(value = "/chinesename/{chineseName}", method = RequestMethod.GET)
    public List<Country> findByChineseName(@PathVariable("chineseName") String chineseName) throws LogisticException {
        return countryService.findByChineseName(chineseName);
    }

    @RequestMapping(value = "/twolettercode/{twoLetterCode}", method = RequestMethod.GET)
    public Country findByTwoLetterCode(@PathVariable("twoLetterCode") String twoLetterCode) throws LogisticException {
        return countryService.findByTwoLetterCode(twoLetterCode);
    }

}
