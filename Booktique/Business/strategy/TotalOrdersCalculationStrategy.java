package strategy;

import constants.ConfigurationsKeys;
import entities.Configuration;
import entities.Order;
import exceptions.BusinessException;
import exceptions.ConfigurationMissingException;
import interfaces.ITotalOrdersCalculationStrategy;
import interfaces.repository.IConfigurationRepository;
import interfaces.repository.IOrderRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class TotalOrdersCalculationStrategy implements ITotalOrdersCalculationStrategy {

    private IConfigurationRepository _configurationRepository;
    private IOrderRepository _orderRepository;

    public TotalOrdersCalculationStrategy(IConfigurationRepository configurationRepository,
                                          IOrderRepository orderRepository)
    {
        _configurationRepository = configurationRepository;
        _orderRepository = orderRepository;
    }

    @Override
    public float calculate() throws BusinessException
    {
        Configuration budgetConfiguration = _configurationRepository.fetchConfigurationByName(ConfigurationsKeys.OrdersBudget);

        if (budgetConfiguration == null)
            throw new ConfigurationMissingException(ConfigurationsKeys.OrdersBudget);

        try {
            float budget = Float.parseFloat(budgetConfiguration.getConfigValue());

            if (budget == 0)
                return 0;

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endOfMonth = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date startOfMonth = cal.getTime();

            Vector<Order> ordersInRange = _orderRepository.searchOrders(startOfMonth, endOfMonth);
            if (ordersInRange == null || ordersInRange.isEmpty())
                return budget;

            double orderTotals = ordersInRange.stream().filter(
                                ordr -> ordr.getOrderCheckedDate() != null && ordr.isCanceled() == false)
                                                        .mapToDouble(order -> order.getPrice()).sum();

            float ordersBalance = budget - (float)orderTotals;

            return ordersBalance;
        }
        catch(NumberFormatException exception)
        {
            System.out.println(exception);
            return  0;
        }
    }
}
