package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.MovieImg;
import com.teamproject.megabox.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
