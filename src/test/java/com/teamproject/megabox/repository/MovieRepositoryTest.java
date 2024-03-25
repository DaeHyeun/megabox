package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    //더미데이터는 엑셀로 Import 사진은 수정페이지에서 추가
    //regdate, ModDate 는 고민중

    @Test
    public void findAll(){
        List<Movie> result = movieRepository.findAll();
        for (Movie i :result){
            System.out.println(i);
        }
    }

}
