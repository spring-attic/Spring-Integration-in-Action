/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.domain.payment;

import java.util.Date;

/**
 * @author Marius Bogoevici
 */
public class CreditCardPayment extends PaymentSettlement
{
   private CreditCardType creditCardType;

   private String accountNumber;

   private Date expiryDate;

   private String cardholderName;

   public String getAccountNumber()
   {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber)
   {
      this.accountNumber = accountNumber;
   }

   public String getCardholderName()
   {
      return cardholderName;
   }

   public void setCardholderName(String cardholderName)
   {
      this.cardholderName = cardholderName;
   }

   public CreditCardType getCreditCardType()
   {
      return creditCardType;
   }

   public void setCreditCardType(CreditCardType creditCardType)
   {
      this.creditCardType = creditCardType;
   }

   public Date getExpiryDate()
   {
      return expiryDate;
   }

   public void setExpiryDate(Date expiryDate)
   {
      this.expiryDate = expiryDate;
   }
}
