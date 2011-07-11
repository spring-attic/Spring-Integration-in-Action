package com.manning.siia.coupling;

import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * @author Marius Bogoevici
 */
public class MealPreferenceUpdateRequestTrasformer {

    public Source buildMealPreferenceUpdateRequest(MealPreference mealPreference) {
        return new StringSource(
             "<updateMealPreference>" +
                "<flightRef>" + mealPreference.getFlightReference() + "</flightRef>" +
                "<mealPreference>" + mealPreference + "</mealPreference>" +
             "</updateMealPreference>");
    }
}
