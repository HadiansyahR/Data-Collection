package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.UomPackage;
import com.track_and_trace.restful_application.model.request.CreateUomPackageRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomPackageRequest;
import com.track_and_trace.restful_application.model.response.UomPackageResponse;
import com.track_and_trace.restful_application.repository.UomPackageRepository;
import com.track_and_trace.restful_application.service.UomPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UomPackageServiceImpl implements UomPackageService {

    @Autowired
    private UomPackageRepository uomPackageRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public UomPackageResponse create(CreateUomPackageRequest request) {
        validationService.validate(request);

        UomPackage uomPackage = new UomPackage();
        uomPackage.setUomPackageName(request.getUomPackageName());
        uomPackageRepository.save(uomPackage);

        return responseBuilder(uomPackage);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UomPackageResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<UomPackage> uomPackages = uomPackageRepository.findAll(pageable);
        List<UomPackageResponse> uomPackageResponses = uomPackages.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(uomPackageResponses, pageable, uomPackages.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public UomPackageResponse findById(int id) {
        UomPackage uomPackage = uomPackageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom package is not found"));

        return responseBuilder(uomPackage);
    }

    @Transactional
    @Override
    public void delete(int id) {
        UomPackage uomPackage = uomPackageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom package is not found"));

        uomPackageRepository.delete(uomPackage);
    }

    @Transactional
    @Override
    public UomPackageResponse update(UpdateUomPackageRequest request) {
        validationService.validate(request);

        UomPackage uomPackage = uomPackageRepository.findById(request.getIdUomPackage())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom package is not found"));

        uomPackage.setUomPackageName(request.getUomPackageName());
        uomPackageRepository.save(uomPackage);

        return responseBuilder(uomPackage);
    }

    @Override
    public UomPackageResponse responseBuilder(UomPackage entity) {
        return UomPackageResponse
                .builder()
                .idUomPackage(entity.getIdUomPackage())
                .uomPackageName(entity.getUomPackageName())
                .build();
    }
}
