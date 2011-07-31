package siia.booking.domain.car;

import siia.booking.domain.FinancialAmount;

/**
 * @author Jonas Partner
 */
public class CarRentalQuote {

    private final CarRental carRental;

    private final FinancialAmount quote;

    public CarRentalQuote(CarRental carRental, FinancialAmount quote) {
        this.carRental = carRental;
        this.quote = quote;
    }

    public CarRental getCarRental() {
        return carRental;
    }

    public FinancialAmount getQuote() {
        return quote;
    }
}
