package com.logistic.project.dao.repository;

import com.logistic.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByTwoLetterCode(String twoLetterCode);

    @Query("select c from Country c where c.name like %:name%")
    List<Country> findByName(@Param("name") String name);

    @Query("select c from Country c where c.chineseName like %:chineseName%")
    List<Country> findByChineseName(@Param("chineseName") String chineseName);
}
