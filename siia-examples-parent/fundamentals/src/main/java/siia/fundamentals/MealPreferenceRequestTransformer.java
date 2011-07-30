package siia.fundamentals;

import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

public class MealPreferenceRequestTransformer {

  public Source buildMealPreferenceUpdateRequest(MealPreference
                                                     mealPreference) {
    return new StringSource(
        "<updateMealPreference>" +
            "<flightRef>" +
               mealPreference.getFlightReference() +
            "</flightRef>" +
            "<mealPreference>" +
               mealPreference + "" +
            "</mealPreference>" +
        "</updateMealPreference>");
  }
}
