package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Uom;
import com.track_and_trace.restful_application.model.request.CreateUomRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomRequest;
import com.track_and_trace.restful_application.model.response.UomResponse;
import com.track_and_trace.restful_application.repository.UomRepository;
import com.track_and_trace.restful_application.service.UomService;
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
public class UomServiceImpl implements UomService {

    @Autowired
    private UomRepository uomRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public UomResponse create(CreateUomRequest request) {
        validationService.validate(request);

        Uom uom = new Uom();
        uom.setUomName(request.getUomName());
        uomRepository.save(uom);

        return responseBuilder(uom);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UomResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<Uom> uoms = uomRepository.findAll(pageable);
        List<UomResponse> uomResponses = uoms.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(uomResponses, pageable, uoms.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public UomResponse findById(int id) {
        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom is not found"));

        return responseBuilder(uom);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom is not found"));

        uomRepository.delete(uom);
    }

    @Transactional
    @Override
    public UomResponse update(UpdateUomRequest request) {
        validationService.validate(request);

        Uom uom = uomRepository.findById(request.getIdUom())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom is not found"));

        uom.setUomName(request.getUomName());
        uomRepository.save(uom);

        return responseBuilder(uom);
    }

    @Override
    public UomResponse responseBuilder(Uom entity) {
        return UomResponse
                .builder()
                .idUom(entity.getIdUom())
                .uomName(entity.getUomName())
                .build();
    }
}
