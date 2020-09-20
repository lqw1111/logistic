package com.logistic.project.dto;

public class CountryDTO {

    private Integer id;
    private String twoLetterCode;
    private String name;
    private String chineseName;


    public CountryDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTwoLetterCode() {
        return twoLetterCode;
    }

    public void setTwoLetterCode(String twoLetterCode) {
        this.twoLetterCode = twoLetterCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
}
