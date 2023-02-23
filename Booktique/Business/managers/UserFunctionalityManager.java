package managers;

import entities.User;
import exceptions.BusinessException;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;

import java.util.Vector;

public class UserFunctionalityManager implements IUserFunctionalityManager {

    private IUserRepository userRepository;

    public UserFunctionalityManager(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws BusinessException {
        User user = userRepository.fetch(username, password);
        if (user == null)
            throw new UserNotFoundException();
        if (!user.isActive())
            throw new BusinessException("User is locked");
        return user;
    }

    public boolean logout(String userID) throws GeneralErrorException {
        if (userRepository.fetch(userID) != null)
            return true;
        throw new GeneralErrorException();
    }

    public User register(User user) throws BusinessException
    {
        return userRepository.insert(user);
    }

    public User update(User user)
    {
        User oldUser = userRepository.fetch(user.getId());
        if (oldUser == null)
             return null;
        return userRepository.update(user);
    }

    public Vector<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }

    public Vector<User> updateUsers(Vector<User> usersToUpdate)
    {
        if (usersToUpdate == null)
            return null;

        Vector<User> updatedUsers = new Vector<>();

        for (User user: usersToUpdate) {
            User updatedUser = this.userRepository.update(user);
            if (updatedUser != null)
                updatedUsers.add(updatedUser);
        }

        return updatedUsers;
    }
}
