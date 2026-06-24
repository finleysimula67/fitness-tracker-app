package fitness_tracker.repository;

import fitness_tracker.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This is the Database Librarian for health tips and advice.
 * It handles saving and finding recommendation messages.
 */
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {

    /** * This method finds all fitness tips generated for a specific person.
     */
    List<Recommendation> findByUserId(String userId);

    /** * This method finds all fitness tips connected to a specific workout session.
     */
    List<Recommendation> findByActivityId(String activityId);
}