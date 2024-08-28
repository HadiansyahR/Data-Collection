package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Menu;
import com.track_and_trace.restful_application.model.request.CreateMenuRequest;
import com.track_and_trace.restful_application.model.response.MenuResponse;
import com.track_and_trace.restful_application.model.request.UpdateMenuRequest;
import com.track_and_trace.restful_application.repository.MenuRepository;
import com.track_and_trace.restful_application.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public MenuResponse responseBuilder(Menu entity) {
        return MenuResponse
                .builder()
                .idMenu(entity.getIdMenu())
                .menuName(entity.getMenuName())
                .labelMenu(entity.getLabelMenu())
                .build();
    }

    @Transactional
    @Override
    public MenuResponse create(CreateMenuRequest request) {
        validationService.validate(request);

        Menu menu = new Menu();
        menu.setMenuName(request.getMenuName());
        menu.setLabelMenu(request.getLabelMenu());
        menuRepository.save(menu);

        return responseBuilder(menu);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MenuResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<Menu> menus = menuRepository.findAll(pageable);
        List<MenuResponse> menuResponses = menus.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(menuResponses, pageable, menus.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public MenuResponse findById(int id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        return responseBuilder(menu);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        menuRepository.delete(menu);
    }

    @Transactional
    @Override
    public MenuResponse update(UpdateMenuRequest updateMenuRequest) {
        validationService.validate(updateMenuRequest);

        Menu menu = menuRepository.findById(updateMenuRequest.getIdMenu())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found"));

        menu.setMenuName(updateMenuRequest.getMenuName());
        menu.setLabelMenu(updateMenuRequest.getLabelMenu());
        menuRepository.save(menu);

        return responseBuilder(menu);
    }

}
