package com.total.login.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Common.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class Common {
    
    String status;
    String message;
    String errorCode;

    Map<String, Object> data;

    public Map<String, Object> getData() {
        if (data == null) data = new HashMap<String, Object>();
        return data;
    }
}
