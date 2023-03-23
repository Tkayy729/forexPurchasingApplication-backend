package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import com.secondstax.fxPurchasingApplication.model.ProviderData;
import com.secondstax.fxPurchasingApplication.dto.ProviderDataRequest;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.repository.ProviderRepository;
import com.secondstax.fxPurchasingApplication.service.LiquidityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiquidityProviderServiceImpl implements LiquidityService {
    private final ProviderRepository providerRepository;

    public List<ProviderData> getAllData() throws ResourceNotFoundException {
       var data= providerRepository.findAll();
       if(data.isEmpty()){
           throw new ResourceNotFoundException("No data");
       }
       return data;
    }

    @Override
    public ProviderData getOne(Long id) throws ResourceNotFoundException {
        var data = providerRepository.findById( id);
        if(data.isEmpty()){
            throw new ResourceNotFoundException("No data");
        }
        return data.get();
    }

    public String addData(ProviderDataRequest providerDataRequest){
        ProviderData data = ProviderData.builder().provider(providerDataRequest.provider() )
                .amount(providerDataRequest.amount())
                .exchange(providerDataRequest.currency()).build();

        providerRepository.save(data);
        return "new data added";
    }
}
