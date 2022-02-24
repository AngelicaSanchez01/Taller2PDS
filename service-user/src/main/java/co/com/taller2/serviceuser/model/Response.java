package co.com.taller2.serviceuser.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private Integer code;
    private Object data;

}
