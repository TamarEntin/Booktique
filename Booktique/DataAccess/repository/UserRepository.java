package repository;

import exceptions.BusinessException;
import exceptions.UserAlreadyExistException;
import interfaces.repository.IUserRepository;
import entities.User;
import java.util.Vector;

public class UserRepository extends RepositoryBase<User> implements IUserRepository {

    private Vector<User> users;

    public UserRepository()
    {
        users = this.loadData();
    }

    public User insert(User user) throws BusinessException {
        if (users == null)
            this.users = new Vector<>();

        User userResult = users.stream().filter(usr -> usr.getId().equals(user.getId())
                || usr.getUserName().equalsIgnoreCase(user.getUserName()))
                .findFirst().orElse(null);

        if (userResult != null)
            throw new UserAlreadyExistException();

        boolean result = users.add(user);
        if (result)
        {
            this.saveData(users);
            return user;
        }
        else{
            return null;
        }
    }

    public User update (User user) {
        if (users == null || users.isEmpty())
            return null;

        User userResult = users.stream().filter(usr ->
                usr.getId().equals(user.getId())).findFirst().orElse(null);

        if (userResult == null)
            return null;

        if(user.getPassword() == null || user.getPassword().equals(""))
        {
            user.setPassword(userResult.getPassword());
        }
        users.remove(userResult);

        boolean result = users.add(user);

        if (result) {
            this.saveData(users);
            return user;
        } else {
            return null;
        }
    }

    public User fetch(String userID)
    {
        if (users == null || users.isEmpty())
            return null;

        return users.stream().filter(usr->
                usr.getId().equals(userID)).findFirst().orElse(null);
    }

    public User fetch(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty())
            return  null;

        return users.stream().filter(usr->
                usr.getUserName().equals(username) && usr.getPassword().equals(password))
                .findFirst().orElse(null);

    }

    public Vector<User> getAllUsers()
    {
        return users;
    }

}
