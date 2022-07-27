package com.salam.ec.tamara.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TM_ORDER_ITEMS")
public class TamaraOrderItemEntity
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "toi_item_idfr")
   private Long itemIdfr;

   @Column(name = "toi_order_id")
   private Long orderId;

   @Column(name = "toi_item_id")
   String itemId;

   @Column(name = "toi_item_name")
   String itemName;

   @Column(name = "toi_item_count")
   int itemCount;
   //
   // @ManyToOne
   // private TamaraOrderMasterEntity orderNmber;

   public Long getItemIdfr()
   {
      return itemIdfr;
   }

   public void setItemIdfr(Long itemIdfr)
   {
      this.itemIdfr = itemIdfr;
   }

   public Long getOrderId()
   {
      return orderId;
   }

   public void setOrderId(Long orderId)
   {
      this.orderId = orderId;
   }

   public String getItemId()
   {
      return itemId;
   }

   public void setItemId(String itemId)
   {
      this.itemId = itemId;
   }

   public String getItemName()
   {
      return itemName;
   }

   public void setItemName(String itemName)
   {
      this.itemName = itemName;
   }

   public int getItemCount()
   {
      return itemCount;
   }

   public void setItemCount(int itemCount)
   {
      this.itemCount = itemCount;
   }

   @Override
   public String toString()
   {
      return "TamaraOrderItemEntity [itemIdfr=" + itemIdfr + ", orderId=" + orderId + ", itemId="
         + itemId + ", itemName=" + itemName + ", itemCount=" + itemCount + "]";
   }

}
