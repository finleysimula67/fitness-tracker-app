package fitness_tracker.repository;

import fitness_tracker.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation , String> {
    List<Recommendation> findByUserId(String userId);
}
