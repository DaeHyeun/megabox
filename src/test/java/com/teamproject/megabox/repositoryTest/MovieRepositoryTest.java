package com.teamproject.megabox.repositoryTest;

import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void insert(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Movie movie = Movie.builder()
                    .title(i+"title.....")
                    .director("director"+i)
                    .genre("genre"+i)
                    .duration("120m")
                    .releaseYear("2024-3-10")
                    .rating(15)
                    .posterURI("image"+i)
                    .description("설명 ")
                    .build();
            movieRepository.save(movie);
        });

    }
}
