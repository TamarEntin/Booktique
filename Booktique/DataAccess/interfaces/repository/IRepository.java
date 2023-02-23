package interfaces.repository;

import exceptions.BusinessException;

public interface IRepository<Entity> {
    Entity insert(Entity entity) throws BusinessException;
}
