package com.track_and_trace.restful_application.service;

import java.util.List;

public interface CrudService<T, Response> {
    Response responseBuilder(T entity);
}
