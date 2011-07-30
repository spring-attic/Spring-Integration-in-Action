package siia.fundamentals.original;

import siia.fundamentals.Booking;
import siia.fundamentals.BookingDao;
import siia.fundamentals.MealPreference;
import siia.fundamentals.SimpleBookingDao;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Strongly coupled version of the {@link siia.fundamentals.BookingService}.
 * <ul>
 *     <li>Dependencies are created by the class itself, rather than being injected;</li>
 *     <li>The meal preference update is strongly tied to a web service invocation</li>
 * </ul>
 *
 *
 */
public class BookingService {
    private final BookingDao bookingDao;
    private final WebServiceOperations mealPreferenceWebServiceInvoker;

    public BookingService() {
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
