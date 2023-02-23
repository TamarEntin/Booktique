package entities.extension;

import entities.User;
import enums.UserStatus;

public class UserExtension {


    public static boolean isUserFitRole(User user, UserStatus userStatus)
    {
        int statusValue = userStatus.StatusValue();
        return (user.getUserStatus() == statusValue) ? true : false;
    }
}
