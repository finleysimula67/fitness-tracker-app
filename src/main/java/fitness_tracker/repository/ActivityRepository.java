package fitness_tracker.repository;

import fitness_tracker.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This is the Database Librarian for workouts.
 * It handles saving and finding workout logs in the database.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    /** * This method looks through the database and finds a list of
     * all workouts that belong to one specific user ID.
     */
    List<Activity> findByUserId(String userId);
}