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
 *
 * @author Marius Bogoevici
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

  public void updateMeal(MealPreference mealPreference) {
    Booking booking = bookingDao.getBookingById(
        mealPreference.getBookingId());
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
