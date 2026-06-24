package fitness_tracker.repository;

import fitness_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * This is the Database Librarian for user profiles.
 * It handles creating new accounts and looking up user details.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /** * This method searches the database to find a user by their email address.
     * This is highly useful during login to check if the account exists.
     */
    User findByEmail(String email);
}