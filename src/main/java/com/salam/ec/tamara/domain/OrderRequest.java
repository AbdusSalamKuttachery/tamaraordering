package com.salam.ec.tamara.domain;

import java.math.BigDecimal;

import com.salam.ec.tamara.dao.domain.TamaraOrderMasterEntity;

public class OrderRequest
{

   String orderUserId;
   BigDecimal orderTotalAmt;
   BigDecimal orderTaxAmt;
   BigDecimal orderDiscountAmt;

   public TamaraOrderMasterEntity extractOrder()
   {

      TamaraOrderMasterEntity newOrder = new TamaraOrderMasterEntity();
      newOrder.setTouserid(this.getOrderUserId());
      newOrder.setTotaxamt(this.getOrderTaxAmt());
      newOrder.setTototalamt(this.getOrderTotalAmt());
      newOrder.setTodiscountamt(this.getOrderDiscountAmt());
      return newOrder;
   }
   // List<OrderItem> item;
   public String getOrderUserId()
   {
      return orderUserId;
   }

   public void setOrderUserId(String orderUserId)
   {
      this.orderUserId = orderUserId;
   }

   public BigDecimal getOrderTotalAmt()
   {
      return orderTotalAmt;
   }

   public void setOrderTotalAmt(BigDecimal orderTotalAmt)
   {
      this.orderTotalAmt = orderTotalAmt;
   }

   public BigDecimal getOrderTaxAmt()
   {
      return orderTaxAmt;
   }

   public void setOrderTaxAmt(BigDecimal orderTaxAmt)
   {
      this.orderTaxAmt = orderTaxAmt;
   }

   public BigDecimal getOrderDiscountAmt()
   {
      return orderDiscountAmt;
   }

   public void setOrderDiscountAmt(BigDecimal orderDiscountAmt)
   {
      this.orderDiscountAmt = orderDiscountAmt;
   }

}
