package com.track_and_trace.restful_application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse {

//    private Integer limit;
//
//    private Integer offset;

    private Integer size;
}
