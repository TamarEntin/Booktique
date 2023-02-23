package tests;

//import org.testing.annotations.AfterTest;

import entities.User;
import enums.TestState;
import exceptions.UserNotFoundException;
import managers.UserFunctionalityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.repositories.UserRepositoryDummy;

import java.util.Date;
import java.util.Vector;

public class UserFunctionalityTest  {

    UserRepositoryDummy dummy;
    User newUser;
    UserFunctionalityManager userFunctionalityManager;
    @Before
    public void setup()
    {
        newUser = new User("1", "username", "Nikita", "Chuiko", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        dummy = new UserRepositoryDummy();
        userFunctionalityManager = new UserFunctionalityManager(dummy);

    }
    @Test
    public void loginTest_HappyPath() {
        dummy.setup(newUser, TestState.ReturnObject);

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
    public void loginTest_ShouldThrowUserNotFound()
    {
        dummy.setup(newUser, TestState.ReturnException);

        String username = "username";
        String password = "password";
        try {
            User existedUser = userFunctionalityManager.login(username, password);
            if (existedUser != null)
                Assert.fail("Should throw User Not Found Exception");
        }
        catch(UserNotFoundException userNotFound){
            Assert.assertTrue(userNotFound != null);
        }
        catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void logoutTest_HappyPath() {
        dummy.setup(newUser, TestState.ReturnObject);

        try {
            boolean logout = userFunctionalityManager.logout("1");
            Assert.assertTrue(logout);
        }
        catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void registerTest_HappyPath() {
        dummy.setup(newUser, TestState.ReturnObject);

        try {
            User registeredUser = userFunctionalityManager.register(newUser);
            Assert.assertTrue(registeredUser != null);
        }
        catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @org.junit.Test
    public void updateTest_HappyFlow() {
        dummy.setup(newUser, TestState.ReturnObject);

        try {
            User users = userFunctionalityManager.update(newUser);
            Assert.assertTrue(users != null);
        }
        catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getAllUsersTest_HappyFlow() {
        dummy.setup(newUser, TestState.ReturnObject);

        try {
            Vector<User> users = userFunctionalityManager.getAllUsers();
            Assert.assertTrue(users != null);
        }
        catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }
}