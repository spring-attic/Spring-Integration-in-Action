/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.integration.routing;

import siia.booking.domain.payment.CreditCardPayment;
import siia.booking.domain.payment.Invoice;
import siia.booking.domain.payment.PaymentSettlement;
import siia.booking.domain.payment.PaypalPayment;

/**
 * @author Marius Bogoevici
 */
public class PaymentSettlementRouter {

   public String routePaymentSettlement(PaymentSettlement paymentSettlement) {
      String destinationChannel = null;
      if (paymentSettlement instanceof CreditCardPayment)
         destinationChannel = "credit-card-payments";
      if (paymentSettlement instanceof Invoice)
         destinationChannel = "invoices";
      if (paymentSettlement instanceof PaypalPayment)
         destinationChannel = "paypal-payments";
      return destinationChannel;
   }
}
