package initializer;

import interfaces.repository.*;
import repository.*;

import java.util.Hashtable;
import java.util.stream.Collectors;


public class RepositoriesInitializer {

    private Hashtable<String,IRepository> repositories;

    public RepositoriesInitializer()
    {
        repositories = new Hashtable<>();
        IBooksInOrdersRepository booksInOrdersRepository = new BooksInOrdersRepository();
        repositories.put("IBooksInOrdersRepository" ,booksInOrdersRepository);

        IUserRepository userRepository = new UserRepository();
        repositories.put("IUserRepository", userRepository);

        IOrderRepository orderRepository = new OrderRepository();
        repositories.put("IOrderRepository", orderRepository);

        IBookStockRepository bookStockRepository = new BookStockRepository();
        repositories.put("IBookStockRepository", bookStockRepository);

        IBorrowedBookRepository borrowedBookRepository = new BorrowedBookRepository();
        repositories.put("IBorrowedBookRepository", borrowedBookRepository);

        IConfigurationRepository configurationRepository = new ConfigurationRepository();
        repositories.put("IConfigurationRepository", configurationRepository);

        IRecommendationRepository recommendationRepository = new RecommendationRepository();
        repositories.put("IRecommendationRepository", recommendationRepository);
    }

    public IRepository getRepository(String repositoryName)
    {
        if (repositories == null) return null;

        try {
            return repositories.get(repositoryName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
