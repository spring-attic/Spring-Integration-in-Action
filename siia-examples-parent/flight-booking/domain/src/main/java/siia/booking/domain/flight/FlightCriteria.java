package siia.booking.domain.flight;


public class FlightCriteria {

    private FlightSeatClass requiredSeatClass = FlightSeatClass.Economy;

    private boolean returnRequired = true;

    public void setRequiredSeatClass(FlightSeatClass requiredSeatClass) {
        this.requiredSeatClass = requiredSeatClass;
    }

    public FlightSeatClass getRequiredSeatClass() {
        return requiredSeatClass;
    }

    public boolean isReturnRequired() {
        return returnRequired;
    }

    public void setReturnRequired(boolean returnRequired) {
        this.returnRequired = returnRequired;
    }
}
