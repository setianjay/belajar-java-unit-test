package setianjay.test.domain.service;

import setianjay.test.domain.entity.User;
import setianjay.test.domain.repository.UserRepository;

public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getProfile(String userName){
        return userRepository.getUserByUserName(userName);
    }
}
