package ar.com.jalmeyda.magnetbot.dao;

import ar.com.jalmeyda.magnetbot.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(Long userId);
}
