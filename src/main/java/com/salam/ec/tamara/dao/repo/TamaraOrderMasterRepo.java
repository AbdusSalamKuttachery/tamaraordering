package com.salam.ec.tamara.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.salam.ec.tamara.dao.domain.TamaraOrderMasterEntity;

@Repository
@RepositoryRestResource(exported = false)
public interface TamaraOrderMasterRepo extends CrudRepository<TamaraOrderMasterEntity, Long>
{

}
