package interfaces.business;

import java.util.Date;
import java.util.Map;

public interface IUserReportManager {

    Map<String,String> getRegisteredUserByTimeRange(Date startRange, Date endRange);

    Map<String,String> getStatistics(int age, int anotherParam, String anotherParam2);

    Map<String,String> getNumberOfBooksByUser (int userId);

    Map<String, String> getFavoriteUserCategory(int userID);
}
