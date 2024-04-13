import React, { useState } from 'react';
import './test.css';
import ToggleableRangeSlider from './testSlider';


const Booking = () => {
    const [sortOption, setSortOption] = useState('default'); // State để lưu trữ tùy chọn sắp xếp
    const [filteredBookings, setFilteredBookings] = useState([]);

    // Một mảng ví dụ cho các chuyến đi
    const bookings = [
        { id: 1, departureTime: "9:00", arrivalTime: "11:30", price: 50, rating: 3 },
        { id: 2, departureTime: "12:00", arrivalTime: "14:30", price: 60, rating: 1 },
        { id: 3, departureTime: "15:00", arrivalTime: "17:30", price: 70, rating: 5 }
    ];

    // Hàm sắp xếp danh sách chuyến đi dựa trên tùy chọn
    const sortBookings = (option) => {
        switch (option) {
            case 'earliest':
            return bookings.slice().sort((a, b) => {
                const timeA = parseInt(a.departureTime.replace(':', ''));
                const timeB = parseInt(b.departureTime.replace(':', ''));
                return timeA - timeB;
            });
        case 'latest':
            return bookings.slice().sort((a, b) => {
                const timeA = parseInt(a.departureTime.replace(':', ''));
                const timeB = parseInt(b.departureTime.replace(':', ''));
                return timeB - timeA;
            });
            case 'highest':
                return bookings.slice().sort((a, b) => b.rating - a.rating);
            case 'ascending':
                return bookings.slice().sort((a, b) => a.price - b.price);
            case 'descending':
                return bookings.slice().sort((a, b) => b.price - a.price);
            default:
                return bookings;
        }
    };

    // Hàm xử lý sự kiện khi người dùng chọn tùy chọn sắp xếp
    const handleSortChange = (event) => {
        setSortOption(event.target.value);
    };

    // Sắp xếp danh sách chuyến đi dựa trên tùy chọn đã chọn
    const sortedBookings = sortBookings(sortOption);

    const handleSliderChange = (newValue) => {
        const [start, end] = newValue;
        const filtered = bookings.filter(
          (booking) =>
            parseInt(booking.departureTime.split(":")[0]) >= start &&
            parseInt(booking.arrivalTime.split(":")[0]) <= end
        );
    
        setFilteredBookings(filtered);
      };

    return (
        <div>
            <div>
                <h1>Sắp xếp</h1>
                <div>
                    <div>
                    <input
                        type="radio"
                        id="default"
                        value="default"
                        checked={sortOption === 'default'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="default">Mặc định</label>
                    </div>

                    <div>
                    <input
                        type="radio"
                        id="earliest"
                        value="earliest"
                        checked={sortOption === 'earliest'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="earliest">Giờ đi sớm nhất</label>
                    </div>

                    <div>
                    <input
                        type="radio"
                        id="latest"
                        value="latest"
                        checked={sortOption === 'latest'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="latest">Giờ đi muộn nhất</label>
                    </div>

                    <div>
                    <input
                        type="radio"
                        id="highest"
                        value="highest"
                        checked={sortOption === 'highest'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="highest">Đánh giá cao nhất</label>
                    </div>

                    <div>
                    <input
                        type="radio"
                        id="ascending"
                        value="ascending"
                        checked={sortOption === 'ascending'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="ascending">Giá tăng dần</label>
                    </div>

                    <div>
                    <input
                        type="radio"
                        id="descending"
                        value="descending"
                        checked={sortOption === 'descending'}
                        onChange={handleSortChange}
                    />
                    <label htmlFor="descending">Giá giảm dần</label>
                    </div>
                </div>
            </div>
            <div>
                <h1>Lọc</h1>
                <div>
                    <div>
                        <p>Giờ đi</p>
                        { <ToggleableRangeSlider min={0} max={24} step={1} formatLabel={(value) => `${value}:00` } handleChange={handleSliderChange} />}
                    </div>
                </div>
            </div>
            <div>
                <h1>Danh sách chuyến đi</h1>
                <ul>
                    {/* Sử dụng danh sách đã được sắp xếp để render */}
                    {sortedBookings.map(booking => (
                        <li key={booking.id}>
                            <strong>Giờ đi:</strong> {booking.departureTime} <br />
                            <strong>Giờ đón:</strong> {booking.arrivalTime} <br />
                            <strong>Giá vé:</strong> ${booking.price} <br />
                            <strong>Đánh giá:</strong> {booking.rating} sao <br />

                        </li>
                    ))}
                </ul>
            </div>
            <div>
        <h1>Filtered Bookings</h1>
        <ul>
          {filteredBookings.map((booking) => (
            <li key={booking.id}>
              <strong>Giờ đi:</strong> {booking.departureTime} <br />
              <strong>Giờ đón:</strong> {booking.arrivalTime} <br />
              <strong>Giá vé:</strong> ${booking.price} <br />
              <strong>Đánh giá:</strong> {booking.rating} sao <br />
            </li>
          ))}
        </ul>
      </div>
        </div>
    );
}

export default Booking;
