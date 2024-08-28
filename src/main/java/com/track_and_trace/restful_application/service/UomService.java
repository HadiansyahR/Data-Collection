package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.Uom;
import com.track_and_trace.restful_application.model.request.CreateUomRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomRequest;
import com.track_and_trace.restful_application.model.response.UomResponse;
import org.springframework.data.domain.Page;

public interface UomService extends CrudService<Uom, UomResponse>{
    UomResponse create(CreateUomRequest request);
    Page<UomResponse> findAll(int limit, int offset);
    UomResponse findById(int id);
    void delete(int id);
    UomResponse update(UpdateUomRequest request);
}
