import { useState } from "react";
const Booking = () => {
    const[bookingState,setBookingState] = useState([])
    // Một mảng ví dụ cho các chuyến đi
    const bookings = [
        { id: 1, departureTime: "9:00", arrivalTime: "11:30", price: 50 },
        { id: 2, departureTime: "12:00", arrivalTime: "14:30", price: 60 },
        { id: 3, departureTime: "15:00", arrivalTime: "17:30", price: 70 }
    ];

    return (
        <div>
            <div>
                
            </div>
            <div>
            <h2>Danh sách chuyến đi</h2>
            <ul>
                {/* Sử dụng phương thức map để lặp qua mỗi chuyến đi và render */}
                {bookings.map(booking => (
                    <li key={booking.id}>
                        <strong>Giờ đi:</strong> {booking.departureTime}<br />
                        <strong>Giờ đón:</strong> {booking.arrivalTime}<br />
                        <strong>Giá vé:</strong> ${booking.price}<br />
                    </li>
                ))}
            </ul>
        </div>
        </div>
    );
}

export default Booking;