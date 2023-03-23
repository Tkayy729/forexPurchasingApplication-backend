package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.model.ProviderData;
import com.secondstax.fxPurchasingApplication.dto.ProviderDataRequest;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;

import java.util.List;

public interface LiquidityService {
    List<ProviderData> getAllData() throws ResourceNotFoundException;
    ProviderData getOne(Long id) throws ResourceNotFoundException;
     String addData(ProviderDataRequest providerDataRequest);
}
