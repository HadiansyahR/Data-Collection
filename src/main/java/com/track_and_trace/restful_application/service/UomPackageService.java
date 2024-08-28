package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.UomPackage;
import com.track_and_trace.restful_application.model.request.CreateUomPackageRequest;
import com.track_and_trace.restful_application.model.request.UpdateUomPackageRequest;
import com.track_and_trace.restful_application.model.response.UomPackageResponse;
import org.springframework.data.domain.Page;

public interface UomPackageService extends CrudService<UomPackage, UomPackageResponse> {
    UomPackageResponse create(CreateUomPackageRequest request);
    Page<UomPackageResponse> findAll(int limit, int offset);
    UomPackageResponse findById(int id);
    void delete(int id);
    UomPackageResponse update(UpdateUomPackageRequest request);
}
