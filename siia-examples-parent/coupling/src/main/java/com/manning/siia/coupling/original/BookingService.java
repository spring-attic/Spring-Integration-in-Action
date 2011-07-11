package com.manning.siia.coupling.original;

import com.manning.siia.coupling.Booking;
import com.manning.siia.coupling.BookingDao;
import com.manning.siia.coupling.MealPreference;
import com.manning.siia.coupling.MockBookingDao;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Strongly coupled version of the {@link com.manning.siia.coupling.BookingService}.
 * <ul>
 *     <li>Dependencies are created by the class itself, rather than being injected;</li>
 *     <li>The meal preference update is strongly tied to a web service invocation</li>
 * </ul>
 *
 *
 * @author Marius Bogoevici
 */
public class BookingService {
    private final BookingDao bookingDao;
    private final WebServiceOperations mealPreferenceWebServiceInvoker;

    public BookingService() {
        this.bookingDao = new MockBookingDao();
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

    public Source buildMealPreferenceUpdateRequest(Booking booking, MealPreference mealPreference) {

        return new StringSource("<updateMealPreference><flightRef>" + booking.getFlightRef() +
                "</flightRef><mealPreference>" + mealPreference +
                "</mealPreference></updateMealPreference>");
    }
}
