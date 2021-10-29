package pl.grupakpkpur.awslab.repository;

import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import pl.grupakpkpur.awslab.model.administration.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
  Optional<User> findByUsername(String username);
}
