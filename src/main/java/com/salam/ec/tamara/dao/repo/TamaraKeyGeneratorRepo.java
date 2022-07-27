package com.salam.ec.tamara.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.salam.ec.tamara.dao.domain.TamaraKeyGenerator;

@Repository
@RepositoryRestResource(exported = false)
public interface TamaraKeyGeneratorRepo extends CrudRepository<TamaraKeyGenerator, String>
{

}
