package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MovieDTO;
import com.teamproject.megabox.dto.PageRequestDTO;
import com.teamproject.megabox.dto.PageResultDTO;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.entity.MovieImg;
import com.teamproject.megabox.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieImgService movieImgService;

    @Override
    public Long saveItem(MovieDTO dto, List<MultipartFile> movieFileList) throws Exception {
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


}
