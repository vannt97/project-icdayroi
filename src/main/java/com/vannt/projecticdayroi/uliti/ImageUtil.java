package com.vannt.projecticdayroi.uliti;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtil {
    public String getImgData(byte[] bytes){
        return Base64.getMimeEncoder().encodeToString(bytes);
    }
}
