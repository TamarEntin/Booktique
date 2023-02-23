package interfaces;

import exceptions.BusinessException;

public interface ITotalOrdersCalculationStrategy {

    float calculate() throws BusinessException;
}
