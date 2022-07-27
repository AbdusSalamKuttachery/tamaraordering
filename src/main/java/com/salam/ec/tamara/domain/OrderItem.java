package com.salam.ec.tamara.domain;

public class OrderItem
{

   String itemId;
   String itemName;
   int itemCount;

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

}
