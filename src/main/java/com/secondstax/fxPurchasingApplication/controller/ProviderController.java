package com.secondstax.fxPurchasingApplication.controller;

import com.secondstax.fxPurchasingApplication.dto.ProviderDataRequest;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.ProviderData;
import com.secondstax.fxPurchasingApplication.service.LiquidityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provider")
@CrossOrigin(origins = "*")
public class ProviderController {
    private final LiquidityService liquidityService;

    @PostMapping("")
    public ResponseEntity<String> addData(@RequestBody ProviderDataRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(liquidityService.addData(request));

    }

    @GetMapping("")
    public ResponseEntity<List<ProviderData>> getAll() throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(liquidityService.getAllData());
    }

    @GetMapping("/{dataId}")
    public ResponseEntity<ProviderData> getOne(@PathVariable Long dataId) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(liquidityService.getOne(dataId));
    }
}
