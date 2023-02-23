package tests.repositories;

import entities.User;
import enums.TestState;
import exceptions.BusinessException;
import exceptions.UserAlreadyExistException;
import interfaces.repository.IUserRepository;

import java.util.Vector;

public class UserRepositoryDummy implements IUserRepository {
    private User userToReturn;
    private TestState testState;

    public void setup(User user, TestState state)
    {
        this.userToReturn = user;
        this.testState = state;
    }
    @Override
    public User update(User user) {
        if (this.testState == TestState.ReturnException)
            return null;
        return this.userToReturn;
    }

    @Override
    public User fetch(String userID) {
        if (this.testState == TestState.ReturnException)
            return null;
        return this.userToReturn;
    }

    @Override
    public User fetch(String username, String password) {
        if (this.testState == TestState.ReturnException)
            return null;
        return this.userToReturn;
    }

    @Override
    public Vector<User> getAllUsers() {
        Vector<User> users = new Vector<>();
        users.add(this.userToReturn);
        return users;
    }

    @Override
    public User insert(User user) throws BusinessException {
        if (this.testState == TestState.ReturnObject)
            return this.userToReturn;
        throw new UserAlreadyExistException();
    }
}
