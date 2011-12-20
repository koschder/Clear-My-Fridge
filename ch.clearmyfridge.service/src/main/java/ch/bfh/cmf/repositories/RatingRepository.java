package ch.bfh.cmf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
