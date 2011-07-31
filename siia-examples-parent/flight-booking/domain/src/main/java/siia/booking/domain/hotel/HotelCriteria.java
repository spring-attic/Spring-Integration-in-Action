package siia.booking.domain.hotel;


public class HotelCriteria {

    private RoomType roomType = RoomType.Twin;

    private boolean smokingRoom = false;

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isSmokingRoom() {
        return smokingRoom;
    }

    public void setSmokingRoom(boolean smokingRoom) {
        this.smokingRoom = smokingRoom;
    }
}
