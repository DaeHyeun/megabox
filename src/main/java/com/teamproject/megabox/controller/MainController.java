package com.teamproject.megabox.controller;

import com.teamproject.megabox.dto.MovieAndMovieImgDTO;
import com.teamproject.megabox.dto.PageRequestDTO;
import com.teamproject.megabox.dto.PageResultDTO;
import com.teamproject.megabox.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MovieService movieService;
    @GetMapping("/")
    public String mainP(Model model, PageRequestDTO requestDTO){
        PageResultDTO<MovieAndMovieImgDTO,Object[]> result = movieService.getMovieandImg(requestDTO);
        model.addAttribute("result",result);
        List<MovieAndMovieImgDTO> re = result.getDtoList();
        System.out.println(re.toString());
        for (int i = 0; i < re.size(); i++) {
            System.out.println(re.get(i).toString());
            System.out.println("리스트길이 : " + re.size());
        }
        return "main";
    }
    @GetMapping("/support")
    public String supportPage(){
        return "support/supportmain";
    }

    @GetMapping("/ticketing")
    public String ticketingPage(){
        return "ticketing/screenList";
    }
}
