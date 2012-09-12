package siia.booking.domain.hotel;

import siia.booking.domain.FinancialAmount;

import java.util.Date;

/**
 * @author Jonas Partner
 */
public class HotelBooking {

    private final Hotel hotel;

    private final Date checkInDate;

    private final Date checkOutDate;

    private final FinancialAmount financialAmount;

    private boolean paid = false;

    private String bookingConfirmation;

    public HotelBooking(Hotel hotel, Date checkInDate, Date checkOutDate,
                        FinancialAmount financialAmount,  String bookingConfirmation) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.financialAmount = financialAmount;
        this.bookingConfirmation = bookingConfirmation;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public FinancialAmount getFinancialAmount() {
        return financialAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getBookingConfirmation() {
        return bookingConfirmation;
    }

    public void setBookingConfirmation(String bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }
}
