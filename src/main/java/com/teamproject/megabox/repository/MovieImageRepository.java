package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.MovieImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieImageRepository extends JpaRepository<MovieImg, Long> {
    List<MovieImg> findByMovieId(Long movieId);
}
