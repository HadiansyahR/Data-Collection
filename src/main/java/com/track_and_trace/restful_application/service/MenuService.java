package com.track_and_trace.restful_application.service;

import com.track_and_trace.restful_application.entity.Menu;
import com.track_and_trace.restful_application.model.request.CreateMenuRequest;
import com.track_and_trace.restful_application.model.response.MenuResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuRequest;
import org.springframework.data.domain.Page;

public interface MenuService extends CrudService<Menu, MenuResponse>{
    MenuResponse create(CreateMenuRequest request);
    Page<MenuResponse> findAll(int limit, int offset);
    MenuResponse findById(int id);
    void delete(int id);
    MenuResponse update(UpdateMenuRequest updateMenuRequest);
}
