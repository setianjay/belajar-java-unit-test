package setianjay.test.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import setianjay.test.domain.entity.User;
import setianjay.test.domain.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = MockitoExtension.class)
class ProfileServiceTest {
    /*
    * @Mock is an annotation used to indicate that the object will be mocked, where the mock object can be customized in
    * behavior.
    * */
    @Mock
    private UserRepository userRepository;

    ProfileService profileService;

    @BeforeEach
    void setup() {
        profileService = new ProfileService(userRepository);
    }

    @AfterEach
    void shutdown() {
        profileService = null;
    }

    @Test
    void testGetProfileSuccess() {
        User user = new User("setianjay", "12345");
        Mockito.when(userRepository.getUserByUserName(user.userName()))
                .thenReturn(user);

        User userProfile = profileService.getProfile("setianjay");

        Mockito.verify(userRepository, Mockito.atMostOnce()).getUserByUserName("setianjay");

        assertNotNull(userProfile);
        assertEquals(user.userName(), userProfile.userName());
        assertEquals(user.password(), userProfile.password());
    }

    @Test
    void testGetProfileNull(){
        User userProfile = profileService.getProfile("setianjay");

        Mockito.verify(userRepository, Mockito.atMostOnce()).getUserByUserName("setianjay");

        assertNull(userProfile);
    }
}
