package com.co.taller2.servicemovie.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private Integer code;
    private Object data;
}
