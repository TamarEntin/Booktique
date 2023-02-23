package strategy;

import enums.BorrowStatus;
import interfaces.IDebtCalculationStrategy;
import interfaces.repository.IBorrowedBookRepository;

public class DebtCalculationStrategy implements IDebtCalculationStrategy {
    private IBorrowedBookRepository _borrowedBookRepository;

    public DebtCalculationStrategy(IBorrowedBookRepository borrowedBookRepository)
    {
        _borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public float calculate(String userID) {

        _borrowedBookRepository.searchBorrowedBooksByUserID(userID, BorrowStatus.Borrowed.StatusValue());

        return 0;
    }
}
