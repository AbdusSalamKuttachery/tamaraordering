package com.salam.ec.tamara.dao.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.salam.ec.tamara.dao.domain.TamaraOrderItemEntity;

@Repository
@RepositoryRestResource(exported = false)
public interface TamaraOrderItemsRepo extends CrudRepository<TamaraOrderItemEntity, Long>
{

   List<TamaraOrderItemEntity> findByOrderId(Long orderId);

}
