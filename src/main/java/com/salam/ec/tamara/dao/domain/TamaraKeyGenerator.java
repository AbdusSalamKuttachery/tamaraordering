package com.salam.ec.tamara.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TM_KEY_GENERATOR")
public class TamaraKeyGenerator
{
   public TamaraKeyGenerator(String entityName, Long keyValue)
   {
      super();
      this.entityName = entityName;
      this.keyValue = keyValue;
   }

   public TamaraKeyGenerator()
   {

   }

   @Id
   @Column(name = "KEY_ENTITY")
   private String entityName;

   @Column(name = "KEY_VALUE")
   private Long keyValue;

   public String getEntityName()
   {
      return entityName;
   }

   public void setEntityName(String entityName)
   {
      this.entityName = entityName;
   }

   public Long getKeyValue()
   {
      return keyValue;
   }

   public void setKeyValue(Long keyValue)
   {
      this.keyValue = keyValue;
   }
}
