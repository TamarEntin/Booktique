package managers;

import builder.BookStockBuilder;
import entities.*;
import entities.extension.UserExtension;
import enums.UserStatus;
import exceptions.*;
import interfaces.IBookStockBuilder;
import interfaces.business.IOrderManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.repository.IOrderRepository;
import interfaces.repository.IUserRepository;

import java.util.Date;
import java.util.Vector;

public class OrderManager implements IOrderManager {

    private IOrderRepository orderRepository;
    private IUserRepository userRepository;
    private IBookStockRepository bookStockRepository;
    private IBooksInOrdersRepository booksInOrdersRepository;
    private IBookStockBuilder bookStockBuilder;

    public OrderManager(IOrderRepository orderRepository, IUserRepository userRepository,
                        IBookStockRepository bookStockRepository,
                        IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookStockRepository = bookStockRepository;
        this.booksInOrdersRepository = booksInOrdersRepository;
        bookStockBuilder = new BookStockBuilder(booksInOrdersRepository);
    }

    @Override
    public Order insertOrder(String userId, Order order, BooksInOrders bookInOrder) throws BusinessException {
        if (bookInOrder == null)
            throw new InvalidBookException();

        User userTemp = userRepository.fetch(userId);

        if (userTemp == null)
            throw new UserNotFoundException();

        if (UserExtension.isUserFitRole(userTemp, UserStatus.Reader))
            throw new UserNotAuthorizeException();

        Order newOrder = orderRepository.insert(order);
        if (newOrder != null)
        {
            bookInOrder.setOrderID(newOrder.getOrderID());
            BooksInOrders bookTemp = booksInOrdersRepository.insert(bookInOrder);
            if (bookTemp != null) {
                approveOrderAndInsertNewBooks(userTemp, order);
                return newOrder;
            }
            else{
                orderRepository.delete(newOrder);
                throw new GeneralErrorException();
            }
        }
        return  null;
    }

    private Order approveOrderAndInsertNewBooks(User user, Order order)throws BusinessException {

        BookStock book = bookStockRepository.fetch(order.getBookName(), order.getAuthorName());
        if (book == null) {
            //Insert New Book
            BookStock newBook = bookStockBuilder.build(order);

            if (newBook == null)
                throw new GeneralErrorException();

            bookStockRepository.insert(newBook);
        } else {
            //Update Quantity
            book.setQuantity(book.getQuantity() + order.getQuantity());
            bookStockRepository.update(book);
        }

        booksInOrdersRepository.delete(order.getOrderID());

        order.setOrderCheckedDate(new Date(System.currentTimeMillis()));
        order.setCanceled(false);
        orderRepository.update(order);

        return order;
    }


    public Vector<EmployeeOrder> getAllOrders()
    {
        Vector<EmployeeOrder> result = new Vector<>();

        Vector<Order> orders = this.orderRepository.getAllOrders();

        if (orders == null || orders .size() == 0) return new Vector<>();

        for (Order order: orders)
        {
            User employee = this.userRepository.fetch(order.getLibrarianID());

            EmployeeOrder employeeOrder = new EmployeeOrder(order.getBookName(), order.getAuthorName(), employee.getUserName(),
                    order.getOrderCreateDate(), order.getQuantity(), order.getPrice());

            result.add(employeeOrder);
        }

        return result;
    }
}
