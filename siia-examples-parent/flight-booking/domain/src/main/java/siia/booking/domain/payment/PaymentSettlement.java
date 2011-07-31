package siia.booking.domain.payment;

import siia.booking.domain.FinancialAmount;

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
