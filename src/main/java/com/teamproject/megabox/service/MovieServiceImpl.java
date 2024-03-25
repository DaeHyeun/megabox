package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.*;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.entity.MovieImg;
import com.teamproject.megabox.repository.MovieRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieImgService movieImgService;

    @Override
    public Long saveMovie(MovieDTO dto, List<MultipartFile> movieFileList) throws Exception {
        //Item 영속저장

        Movie movie = dtoToentity(dto);
        movieRepository.save(movie);
        //이미지 파일 저장
        for (int i = 0; i < movieFileList.size(); i++) {
            MovieImg movieImg = new MovieImg();
            movieImg.setMovie(movie);
            if(i==0){
                movieImg.setRepImgYn("Y");
            }else {
                movieImg.setRepImgYn("N");
            }
            movieImgService.saveItemImg(movieImg, movieFileList.get(i));
        }
        return movie.getId();
    }

    //리스트 출력(페이징)
    @Override
    public PageResultDTO<MovieDTO, Movie> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Movie> result = movieRepository.findAll(pageable);
        Function<Movie, MovieDTO> fn = (entity->entityToDto(entity));
        return new PageResultDTO<>(result,fn);
    }

    //영화 하나 상세보기 페이지
    @Override
    public MovieDTO getMovie(Long movieId) {
        Optional<Movie> result = movieRepository.findById(movieId);
        MovieDTO dto = entityToDto(result.get());
        List<MovieImg> movieImgList = movieImgService.findByMovieId(movieId);
        List<MovieImgDTO> movieImgDTOList = movieImgList.stream().map(movieImg->
                        movieImgService.entityToDto(movieImg)).collect(Collectors.toList());
        dto.setMovieImgDTOList(movieImgDTOList);
        return dto;
    }

    //수정하기
    @Override
    public Long updateMovie(MovieDTO movieDTO, List<MultipartFile> itemImgFileList) throws Exception {
        //기존에 등록되어있던 영화 조회
        Movie movie = movieRepository.findById(movieDTO.getId()).get();
        //아이템 필드값 변경
        movie.updateMovie(movieDTO);
        //업데이트
        movieRepository.save(movie);
        List<Long> movieImgIds = movieDTO.getMovieImgIds();
        for (int i = 0; i < movieImgIds.size(); i++) {
            if (movieImgIds.get(i)==null){
                System.out.println("널입니다.");
                MovieImg movieImg = new MovieImg();
                movieImg.setMovie(movie);
                if(i==0){
                    movieImg.setRepImgYn("Y");
                }else {
                    movieImg.setRepImgYn("N");
                }
                movieImgService.saveItemImg(movieImg,itemImgFileList.get(i));

            }else {
                movieImgService.updateMovieImg(movieImgIds.get(i), itemImgFileList.get(i));
            }
        }
        return movie.getId();
    }

    //이미지랑 같이 영화객체 뽑기
    @Override
    public PageResultDTO<MovieAndMovieImgDTO, Object[]> getMovieandImg(PageRequestDTO requestDTO) {
        Page<Object[]> result =  movieRepository.getListPage(requestDTO.getPageable(Sort.by("id").descending()));
        //지금은 아이디로 하고 나중에 개봉일 좋아요수 관객수로 변경
        Function<Object[],MovieAndMovieImgDTO> fn  = (arr->{
            System.out.println("arr" + Arrays.toString(arr));
            return  entityObjToDTO((Movie) arr[0],(MovieImg) arr[1]);
        });
        return new PageResultDTO<>(result,fn);
    }



}
