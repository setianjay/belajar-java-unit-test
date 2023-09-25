package setianjay.test.domain.repository;

import setianjay.test.domain.entity.User;

public interface UserRepository {
    User getUserByUserName(String userName);
}
