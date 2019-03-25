package com.architecture.pojo.common;

import lombok.Data;

@Data
public class WebResponse {

    private String statusCode;
    private Boolean success;
    private String errorMessage;
    private Object data;
    private Long costTime;
}
