package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Member;
import com.teamproject.megabox.entity.Movie;
import com.teamproject.megabox.entity.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MovieRepository movieRepository;
    @Test
    public void save(){
        Movie movie = Movie.builder()
                .genre("Ddd")
                .build();
        movieRepository.save(movie);
        Member member = Member.builder()
                .addr("ddd")
                .build();
        memberRepository.save(member);
        Review review = Review.builder()
                .member(member)
                .movieRating(5)
                .movie(movie)
                .build();
        reviewRepository.save(review);
    }
}
