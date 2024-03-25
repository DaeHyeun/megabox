package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MovieDTO;
import com.teamproject.megabox.dto.MovieImgDTO;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.entity.MovieImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieImgService {
    // 영화 저장하기
    void saveItemImg(MovieImg movieImg, MultipartFile itemImgFile) throws Exception;

    //MovieId에 해당하는 MovieImg 탐색
    List<MovieImg> findByMovieId(Long movieId);

    //수정하기
    void updateMovieImg(Long movieImgId, MultipartFile multipartFile) throws Exception;



    //default Method
    //Entity To DTO
    default MovieImgDTO entityToDto (MovieImg movieImg){
        MovieImgDTO dto = MovieImgDTO.builder()
                .id(movieImg.getId())
                .imgName(movieImg.getImgName())
                .oriImgName(movieImg.getOriImgName())
                .imgUrl(movieImg.getImgUrl())
                .repImgYn(movieImg.getRepImgYn())
                .build();
        return dto;
    }

}
