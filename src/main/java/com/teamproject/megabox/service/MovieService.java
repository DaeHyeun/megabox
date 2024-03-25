package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MovieAndMovieImgDTO;
import com.teamproject.megabox.dto.MovieDTO;

import com.teamproject.megabox.dto.PageRequestDTO;
import com.teamproject.megabox.dto.PageResultDTO;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.entity.MovieImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    //저장
    Long saveMovie(MovieDTO dto, List<MultipartFile> movieImgFileList) throws Exception;
    //리스트 목록조회
    PageResultDTO<MovieDTO, Movie> getList(PageRequestDTO requestDTO);
    //하나조회 상세보기
    MovieDTO getMovie(Long movieId);
    //영화정보 수정하기
    Long updateMovie(MovieDTO movieDTO, List<MultipartFile> movieImgFileList) throws Exception;
    //영화 리스트 조회
    PageResultDTO<MovieAndMovieImgDTO, Object[]> getMovieandImg(PageRequestDTO requestDTO);





    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    
    //엔티티오브젝트를 메인아이템DTO로 변환
    default MovieAndMovieImgDTO entityObjToDTO (Movie movie, MovieImg movieImg){
        MovieAndMovieImgDTO listDTO = MovieAndMovieImgDTO.builder()
                //movie
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .ageRating(movie.getAgeRating())
                //movieImg
                .imgUrl(movieImg.getImgUrl())

                .build();
        return listDTO;
    }




}
