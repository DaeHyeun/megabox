package com.teamproject.megabox.controller;

import com.teamproject.megabox.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    //리스트 페이지 이동
    @GetMapping("/list")
    public void getList(){

    }
}
