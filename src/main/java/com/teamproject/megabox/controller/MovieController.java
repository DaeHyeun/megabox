package com.teamproject.megabox.controller;

import com.teamproject.megabox.dto.MovieDTO;
import com.teamproject.megabox.dto.PageRequestDTO;
import com.teamproject.megabox.dto.PageResultDTO;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/admin/movie/new")
    public String movieadd(Model model){
        model.addAttribute("movieDTO", new MovieDTO());
        return "/movie/movieadd";
    }
    @PostMapping("/admin/movie/new")
    public String movieadd(MovieDTO movieDTO,@RequestParam("movieImgFile") List<MultipartFile> movieImgFileList
            ,Model model) throws Exception {
        if(movieImgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage", "첫번째 이미지는 필수입니다.");
            return "/movie/movieadd";
        }
        movieService.saveMovie(movieDTO,movieImgFileList);
        return "redirect:/";
    }


    @GetMapping("/admin/movie/{movieId}")
    public String movieDetail(@PathVariable("movieId") Long movieId, Model model){
        MovieDTO movieDTO = movieService.getMovie(movieId);
        model.addAttribute("movieDTO", movieDTO);
        return "movie/moviedetail";
    }
    @PostMapping("/admin/movie")
    public String movieUpdate(MovieDTO movieDTO, @RequestParam("movieImgFile") List<MultipartFile> movieImgFileList) throws Exception {
        Long id = movieService.updateMovie(movieDTO, movieImgFileList);
        return "redirect:/admin/movielist";
    }

    //영화정보 + 이미지
    /*@GetMapping("/admin/movielist")
    public String movieList(Model model, PageRequestDTO pageRequestDTO){

    }*/
    @GetMapping("/admin/movielist")
    public String movieList(Model model, PageRequestDTO pageRequestDTO){
        PageResultDTO<MovieDTO, Movie> result = movieService.getList(pageRequestDTO);
        model.addAttribute("result",result);
        return "movie/movielist";
    }


    
}
