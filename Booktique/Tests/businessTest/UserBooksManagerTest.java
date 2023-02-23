package businessTest;

import entities.User;
import enums.TestState;
import managers.UserFunctionalityManager;
import org.junit.Assert;
import org.junit.Test;
import tests.repositories.UserRepositoryDummy;

import java.util.Date;

public class UserBooksManagerTest {

    @Test
    public void getAllUserActiveBorrowing_HappyPath() {
        User newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);

        UserRepositoryDummy dummy = new UserRepositoryDummy();
        dummy.setup(newUser, TestState.ReturnObject);

        UserFunctionalityManager userFunctionalityManager = new UserFunctionalityManager(dummy);

        String username = "username";
        String password = "password";
        try {
            User existedUser = userFunctionalityManager.login(username, password);
            Assert.assertEquals(existedUser.getUserName(), username);
            Assert.assertEquals(existedUser.getUserName(), newUser.getUserName());
            Assert.assertEquals(existedUser.getPassword(), password);
            Assert.assertEquals(existedUser.getPassword(), newUser.getPassword());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getAllAwaitingForApprovalBorrowing_HappyPath() {


    }
}
