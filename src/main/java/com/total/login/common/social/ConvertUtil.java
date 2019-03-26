package com.total.login.common.social;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by com on 2016-04-25.
 */
public class ConvertUtil {

    public Map<String, String> getConvertData(String data) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> result = new HashMap<String, String>();
        try {
            result = mapper.readValue(data, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
