package com.logistic.project.service;

import com.logistic.project.dto.PriceProfileDTO;
import com.logistic.project.entity.PriceProfile;
import com.logistic.project.exception.LogisticException;
import java.util.List;

public interface PriceProfileService {

    List<PriceProfileDTO> findAll() throws LogisticException;
}
