package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MovieDTO;
import com.teamproject.megabox.dto.PageRequestDTO;
import com.teamproject.megabox.dto.PageResultDTO;
import com.teamproject.megabox.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    //저장
    Long saveItem(MovieDTO dto, List<MultipartFile> itemImgFileList) throws Exception;
    //리스트
    PageResultDTO<MovieDTO, Movie> getList(PageRequestDTO requestDTO);
    //default Method
    default Movie dtoToentity(MovieDTO dto){
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .director(dto.getDirector())
                .actor(dto.getActor())
                .nation(dto.getNation())
                .genre(dto.getGenre())
                .duration(dto.getDuration())
                .releaseDate(dto.getReleaseDate())
                .ageRating(dto.getAgeRating())
                .description(dto.getDescription())
                .attendance(dto.getAttendance())
                .build();

        return movie;
    }
    default MovieDTO entityToDto(Movie movie){
        MovieDTO dto = MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .subTitle(movie.getSubTitle())
                .director(movie.getDirector())
                .actor(movie.getActor())
                .nation(movie.getNation())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .ageRating(movie.getAgeRating())
                .description(movie.getDescription())
                .attendance(movie.getAttendance())
                .build();
        return dto;
    }


}
