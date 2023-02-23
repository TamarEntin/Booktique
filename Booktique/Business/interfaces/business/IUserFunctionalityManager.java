package interfaces.business;

import entities.User;
import exceptions.BusinessException;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;

import java.util.Vector;

public interface IUserFunctionalityManager {

    User login(String username, String password) throws BusinessException;

    boolean logout(String userID) throws GeneralErrorException;

    User register(User user) throws BusinessException;

    User update(User user);

    Vector<User> updateUsers(Vector<User> usersToUpdate);

    Vector<User> getAllUsers();
}
