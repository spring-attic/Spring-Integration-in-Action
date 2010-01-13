package com.manning.siia.domain.payment;

import com.manning.siia.domain.FinancialAmount;

/**
 * @author Marius Bogoevici
 */
public abstract class PaymentSettlement
{
   private FinancialAmount amount;

   public FinancialAmount getAmount()
   {
      return amount;
   }

   public void setAmount(FinancialAmount amount)
   {
      this.amount = amount;
   }
   
}
