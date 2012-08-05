package siia.fundamentals;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

public class BookingServiceWithInjection {

  private final BookingDao bookingDao;

  private final WebServiceOperations mealPreferenceWebServiceInvoker;

  public BookingServiceWithInjection(BookingDao bookingDao,
             WebServiceOperations mealPreferenceWebServiceInvoker) {
    this.bookingDao = bookingDao;
    this.mealPreferenceWebServiceInvoker =
        mealPreferenceWebServiceInvoker;
  }

  public void updateMeal(MealPreference mealPreference) {
    Booking booking = bookingDao.getBookingById(
        mealPreference.getBookingReference());
    Source mealUpdateSource = populatePreference(
        booking, mealPreference);
    StringResult result = new StringResult();
    mealPreferenceWebServiceInvoker.sendSourceAndReceiveToResult(
        mealUpdateSource, result);
  }

  public Source populatePreference(Booking booking,
                                   MealPreference
                                       mealPreference) {

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