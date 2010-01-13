package com.manning.siia.integration.routing;

import com.manning.siia.domain.payment.CreditCardPayment;
import com.manning.siia.domain.payment.Invoice;
import com.manning.siia.domain.payment.PaymentSettlement;
import com.manning.siia.domain.payment.PaypalPayment;

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
