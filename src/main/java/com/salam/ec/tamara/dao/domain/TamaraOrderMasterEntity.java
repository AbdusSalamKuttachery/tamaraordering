package com.salam.ec.tamara.dao.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TM_ORDER_MASTER")
public class TamaraOrderMasterEntity
{
   @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "to_order_id")
   private Long id;

   @Column(name = "to_total_amt")
   private BigDecimal tototalamt;

   @Column(name = "to_tax_amt")
   private BigDecimal totaxamt;

   @Column(name = "to_discount_amt")
   private BigDecimal todiscountamt;

   @Column(name = "to_user_id")
   private String touserid;

   @Column(name = "to_order_status")
   private String toorderstatus;
   //
   // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   // List<TamaraOrderItemEntity> orderItems = new ArrayList<>();

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public BigDecimal getTototalamt()
   {
      return tototalamt;
   }

   public void setTototalamt(BigDecimal tototalamt)
   {
      this.tototalamt = tototalamt;
   }

   public BigDecimal getTotaxamt()
   {
      return totaxamt;
   }

   public void setTotaxamt(BigDecimal totaxamt)
   {
      this.totaxamt = totaxamt;
   }

   public BigDecimal getTodiscountamt()
   {
      return todiscountamt;
   }

   public void setTodiscountamt(BigDecimal todiscountamt)
   {
      this.todiscountamt = todiscountamt;
   }

   public String getTouserid()
   {
      return touserid;
   }

   public void setTouserid(String tousername)
   {
      this.touserid = tousername;
   }
   //
   // public List<TamaraOrderItemEntity> getOrderItems()
   // {
   // return orderItems;
   // }
   //
   // public void setOrderItems(List<TamaraOrderItemEntity> orderItems)
   // {
   // this.orderItems = orderItems;
   // }

   public String getToorderstatus()
   {
      return toorderstatus;
   }

   public void setToorderstatus(String toorderstatus)
   {
      this.toorderstatus = toorderstatus;
   }

}
