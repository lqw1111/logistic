package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.CountryRepository;
import com.logistic.project.entity.Country;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country findByTwoLetterCode(String twoLetterCode) throws LogisticException {
        return countryRepository.findByTwoLetterCode(twoLetterCode);
    }

    @Override
    public List<Country> findAll() throws LogisticException {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> findByChineseName(String chineseName) throws LogisticException {
        return countryRepository.findByChineseName(chineseName);
    }

    @Override
    public List<Country> findByName(String name) throws LogisticException {
        return countryRepository.findByName(name);
    }
}
