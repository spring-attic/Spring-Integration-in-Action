package siia.fundamentals;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Strongly coupled version of the
 * {@link BookingServiceWithInjection}.
 * <ul>
 * <li>Dependencies are created by the class itself, rather than
 * being injected;</li>
 * <li>The meal preference update is strongly tied to a web service
 * invocation</li>
 * </ul>
 */
public class BookingServiceWithStrongCoupling {
  private final BookingDao bookingDao;

  private final WebServiceOperations mealPreferenceWebServiceInvoker;

  public BookingServiceWithStrongCoupling() {
    this.bookingDao = new SimpleBookingDao();
    WebServiceTemplate template = new WebServiceTemplate();
    template.setDefaultUri(System.getProperty(
        "meal.preference.service.uri"));
    this.mealPreferenceWebServiceInvoker = template;
  }

  public void getBooking(MealPreference mealPreference) {
    Booking booking = bookingDao.getBookingById(
        mealPreference.getBookingReference());
    Source mealUpdateSource = buildMealPreferenceUpdateRequest(
        booking, mealPreference);

    StringResult result = new StringResult();
    mealPreferenceWebServiceInvoker.sendSourceAndReceiveToResult(
        mealUpdateSource, result);
  }

  public Source buildMealPreferenceUpdateRequest(
      Booking booking, MealPreference mealPreference) {

    return new StringSource(
        "<updateMealPreference>" +
            "<flightRef>" +
            booking.getFlightRef() +
            "</flightRef>" +
            "<mealPreference>" +
            mealPreference +
            "</mealPreference>" +
            "</updateMealPreference>");
  }
}
