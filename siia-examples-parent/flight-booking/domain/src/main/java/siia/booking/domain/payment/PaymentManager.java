package siia.booking.domain.payment;

/**
 * @author Marius Bogoevici
 */
public class PaymentManager {

  public void processPayment(Invoice invoice) {
    // process payment for Invoice
  }

  public void processPayment(CreditCardPayment creditCardPayment) {
    // process payment for CreditCardPayment
  }

  public void processPayment(PaypalPayment payment) {
    // process payment for PaypalPayment
  }
}
