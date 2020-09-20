package com.logistic.project.service;

import com.logistic.project.entity.Country;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface CountryService {

    Country findByTwoLetterCode(String twoLetterCode) throws LogisticException;

    List<Country> findAll() throws LogisticException;

    List<Country> findByChineseName(String chineseName) throws LogisticException;

    List<Country> findByName(String name) throws LogisticException;
}
