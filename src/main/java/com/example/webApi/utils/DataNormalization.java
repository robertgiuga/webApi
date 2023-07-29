package com.example.webApi.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataNormalization {

    public String domainNormalization(String domain){
        Pattern pattern= Pattern.compile("(?:https?://)?(?:www\\.)?([^/\\r\\n\\s]+)");
        Matcher m = pattern.matcher(domain);
        if(m.matches())
            return m.group(1);
        return  domain;
    }
}
