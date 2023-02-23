package interfaces.business;

import entities.UserLending;
import exceptions.BusinessException;

import java.util.Vector;

public interface IUserBooksManager {
    Vector<UserLending> getAllUserActiveBorrowing(String userId) throws BusinessException;

    Vector<UserLending> getAllAwaitingForApprovalBorrowing()throws BusinessException;

}
