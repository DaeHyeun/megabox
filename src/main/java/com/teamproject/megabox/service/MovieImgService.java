package com.teamproject.megabox.service;

import com.teamproject.megabox.entity.MovieImg;
import org.springframework.web.multipart.MultipartFile;

public interface MovieImgService {
    void saveItemImg(MovieImg movieImg, MultipartFile itemImgFile) throws Exception;
}
