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
