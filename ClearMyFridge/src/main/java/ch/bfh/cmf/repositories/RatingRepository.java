package ch.bfh.cmf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bfh.cmf.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
