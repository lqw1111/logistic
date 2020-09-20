package com.logistic.project.service;

import com.logistic.project.entity.PriceProfile;
import com.logistic.project.exception.LogisticException;
import java.util.List;

public interface PriceProfileService {

    List<PriceProfile> findAll() throws LogisticException;
}
