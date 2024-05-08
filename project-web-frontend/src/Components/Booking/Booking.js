import React, { useEffect, useState } from "react";
import "./test.css";
import SortOptions from "./SortOptions";
import Box from "@mui/material/Box";
import Slider from "@mui/material/Slider";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const Booking = () => {
  // const token = sessionStorage.getItem('token');
  // const headers = {
  //     Authorization: `Bearer ${token}`,
  // };
  // const province = axios.get("http://localhost:4000/api/province",{ headers: headers })
  // console.log(province);

  const [sortOption, setSortOption] = useState("default");
  const [filteredAndSortedBookings, setFilteredAndSortedBookings] = useState(
    []
  );
  const [showSlider1, setshowSlider1] = useState(false);
  const [showSlider2, setshowSlider2] = useState(false);
  const [currentImage1, setCurrentImage1] = useState("image1"); // Thêm state để theo dõi ảnh hiện tại
  const [currentImage2, setCurrentImage2] = useState("image1");
  const [showCheckbox, setShowCheckbox] = useState(false);
  const [currentImage3, setCurrentImage3] = useState("image1");

  // Thêm state để lưu trữ danh sách các nhà xe được chọn
  const [selectedNhaXe, setSelectedNhaXe] = useState([]);

  const toggleSlider1 = () => {
    setshowSlider1((prevState) => !prevState);
  };
  const toggleSlider2 = () => {
    setshowSlider2((prevState) => !prevState);
  };
  const toggleCheckbox = () => {
    setShowCheckbox((prevState) => !prevState);
  };

  const handleClick1 = () => {
    toggleSlider1();
    setCurrentImage1((prevImage) =>
      prevImage === "image1" ? "image2" : "image1"
    );
  };

  const handleClick2 = () => {
    toggleSlider2();
    setCurrentImage2((prevImage) =>
      prevImage === "image1" ? "image2" : "image1"
    );
  };

  const handleClick3 = () => {
    toggleCheckbox();
    setCurrentImage3((prevImage) =>
      prevImage === "image1" ? "image2" : "image1"
    );
  };

  const [timeVal, setValue1] = React.useState([0, 24]);
  const [priceVal, setValue2] = React.useState([0, 100]);

  const handleChange1 = (event, newValue) => {
    setValue1(newValue);
    setTimeRange(newValue);
  };
  const handleChange2 = (event, newValue) => {
    setValue2(newValue);
    setPriceRange(newValue);
  };

  useEffect(() => {
    filterBookings(timeRange, priceVal);
  }, [timeVal, priceVal, sortOption, selectedNhaXe]);

  const booking1 = axios.get(
    `http://localhost:8080/identity/api/admin/tripseat`
  );
  const [array, setArray] = useState([]); // Mảng các chuyến xe sau khi get từ backend

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await booking1;
        setArray(response.data);
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, []); // Chạy một lần sau khi component được render

  // var dateString = "2024-05-06T12:30:45.678+07:00";
  // var dateObj = new Date(dateString);

  // var hour = dateObj.getHours();
  // var minute = dateObj.getMinutes();

  // const bookings = [
  //   {
  //     id: 1,
  //     starttime: "2024-01-01T08:30:45+07:00",
  //     endtime: "2024-02-02T12:30:45+07:00",
  //     price: 50,
  //     rating: 3,
  //     nhaXe: "peo1",
  //     totalSeat: 7,
  //   },
  //   {
  //     id: 2,
  //     starttime: "2024-02-02T07:30:45+07:00",
  //     endtime: "2024-03-03T09:45:45+07:00",
  //     price: 60,
  //     rating: 1,
  //     nhaXe: "peo2",
  //     totalSeat: 9,
  //   },
  //   {
  //     id: 3,
  //     starttime: "2024-03-03T9:11:45+07:00",
  //     endtime: "2024-04-04T12:50:45+07:00",
  //     price: 70,
  //     rating: 5,
  //     nhaXe: "peo3",
  //     totalSeat: 11,
  //   },
  //   {
  //     id: 4,
  //     starttime: "2024-04-04T06:20:45+07:00",
  //     endtime: "2024-05-05T17:15:45+07:00",
  //     price: 90,
  //     rating: 4,
  //     nhaXe: "peo4",
  //     totalSeat: 13,
  //   },
  // ];

  const bookings = array;
  console.log(bookings);
  bookings.map((booking) => {
    console.log(booking.trip);
    console.log(booking.seat);
    console.log(booking.trip.coach);
  });

  // Hàm xử lý sự kiện khi có sự thay đổi trong ô đánh dấu nhà xe
  const handleNhaXeChange = (event) => {
    const nhaXe = event.target.value;
    // Kiểm tra xem nhà xe đã được chọn hay không
    if (event.target.checked) {
      // Nếu đã chọn, thêm nhà xe vào danh sách các nhà xe được chọn
      setSelectedNhaXe((prevSelected) => [...prevSelected, nhaXe]);
    } else {
      // Nếu bỏ chọn, loại bỏ nhà xe khỏi danh sách các nhà xe được chọn
      setSelectedNhaXe((prevSelected) =>
        prevSelected.filter((item) => item !== nhaXe)
      );
    }
  };

  const filterByNhaXe = (list, selectedNhaXe) => {
    // Nếu không có nhà xe nào được chọn, trả về toàn bộ danh sách chuyến đi
    if (selectedNhaXe.length === 0) {
      return list;
    } else {
      // Lọc danh sách chuyến đi sao cho nhà xe nằm trong danh sách nhà xe được chọn
      return list.filter((booking) => selectedNhaXe.includes(booking.nhaXe));
    }
  };

  const getHourAndMinute = (dateTimeString) => {
    const dateObj = new Date(dateTimeString);
    const hour = dateObj.getHours();
    const minute = dateObj.getMinutes();
    return { hour: hour, minute: minute };
  };

  const sortBookings = (option, list) => {
    switch (option) {
      case "earliest":
        return list.slice().sort((a, b) => {
          const timeA = getHourAndMinute(a.trip.starttime);
          const timeB = getHourAndMinute(b.trip.starttime);
          return (
            timeA.hour * 60 + timeA.minute - (timeB.hour * 60 + timeB.minute)
          );
        });
      case "latest":
        return list.slice().sort((a, b) => {
          const timeA = getHourAndMinute(a.trip.starttime);
          const timeB = getHourAndMinute(b.trip.starttime);
          return (
            timeB.hour * 60 + timeB.minute - (timeA.hour * 60 + timeA.minute)
          );
        });
      // case "highest":
      //   return list.slice().sort((a, b) => b.rating - a.rating);
      case "ascending":
        return list.slice().sort((a, b) => a.seat.price - b.seat.price);
      case "descending":
        return list.slice().sort((a, b) => b.seat.price - a.seat.price);
      default:
        return list;
    }
  };

  const handleSortChange = (event) => {
    setSortOption(event.target.value);
  };

  const [priceRange, setPriceRange] = useState([0, 100]); // State lưu trữ khoảng giá vé
  const [timeRange, setTimeRange] = useState([0, 24]); // State lưu trữ khoảng thời gian đi

  // Hàm lọc danh sách đặt phòng dựa trên cả hai tiêu chí: giờ đi và giá vé
  const filterBookings = (timeRange, priceRange) => {
    const filtered1 = bookings.filter((booking) => {
      //return (priceRange[0] != priceRange[1]);

      // Kiểm tra nếu giờ đi của đặt phòng nằm trong khoảng thời gian và giá vé nằm trong khoảng giá trị
      if (
        priceRange[0] !== priceRange[1] &&
        timeRange[0] !== timeRange[1] &&
        booking.seat.price >= priceRange[0] &&
        booking.seat.price <= priceRange[1]
      ) {
        if (
          getHourAndMinute(booking.trip.starttime).hour >= timeRange[0] &&
          getHourAndMinute(booking.trip.endtime).hour <= timeRange[1]
        ) {
          if (getHourAndMinute(booking.trip.endtime).hour < timeRange[1]) {
            return true;
          } else if (getHourAndMinute(booking.trip.endtime).minute === 0)
            return true;
          else return false;
        } else return false;
      } else {
        return false;
      }
    });

    const filtered2 = filterByNhaXe(filtered1, selectedNhaXe);
    const finallist = sortBookings(sortOption, filtered2);
    setFilteredAndSortedBookings(finallist);
  };

  const [showPickSeat, setShowPickSeat] = useState(null); // State quản lý div chọn chuyến
  const [currentBookingPrice, setCurrentBookingPrice] = useState(null);

  const handleBookTicket = (event, booking, id) => {
    event.preventDefault();
    console.log("Booking được chọn:", booking);
    setShowPickSeat(id);
    setCurrentBookingPrice(booking.seat.price);
  };

  const handleSeat = () => {
    console.log(currentBookingPrice);
  };

  const PickUp = [
    { id: 1, list1: ["point a", "point b", "point c"] },
    { id: 2, list1: ["point d", "point e", "point f"] },
    { id: 3, list1: ["point g", "point h", "point i"] },
    { id: 4, list1: ["point j", "point k", "point l"] },
  ];
  const Destination = [
    { id: 1, list2: ["Point A", "Point B", "Point C"] },
    { id: 2, list2: ["Point D", "Point E", "Point F"] },
    { id: 3, list2: ["Point G", "Point H", "Point I"] },
    { id: 4, list2: ["Point J", "Point K", "Point L"] },
  ];

  const [showLocation, setShowLocation] = useState(null); //State quản lý div điểm đón điểm trả
  const handlePick = (event, booking, id) => {
    event.preventDefault();
    setShowLocation(id);
  };

  return (
    <div className="background">
      <div className="box left">
        <div>
          <SortOptions
            sortOption={sortOption}
            handleSortChange={handleSortChange}
          />
        </div>
        <div className="loc">
          <h1>Lọc</h1>
          <div>
            <div>
              <p>Giờ đi</p>
              <div>
                <img
                  src={
                    currentImage1 === "image1"
                      ? "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Ic_keyboard_arrow_down_48px.svg/768px-Ic_keyboard_arrow_down_48px.svg.png"
                      : "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Ic_keyboard_arrow_up_48px.svg/768px-Ic_keyboard_arrow_up_48px.svg.png"
                  }
                  alt="Toggle Image"
                  onClick={handleClick1} // Sử dụng handleClick khi ảnh được click
                  className="arrow"
                  style={{ height: "25px", width: "25px" }}
                />
                {/* Hiển thị slider nếu showSlider1 là true */}
                {showSlider1 && (
                  <Box sx={{ width: 300 }}>
                    <Slider
                      getAriaLabel={() => "Time range"}
                      value={timeVal}
                      min={0}
                      max={24}
                      onChange={handleChange1}
                      valueLabelDisplay="auto"
                      valueLabelFormat={(timeVal) => `${timeVal}:00`}
                    />
                  </Box>
                )}
              </div>
            </div>
            <div>
              <p>Giá vé</p>
              <img
                src={
                  currentImage2 === "image1"
                    ? "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Ic_keyboard_arrow_down_48px.svg/768px-Ic_keyboard_arrow_down_48px.svg.png"
                    : "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Ic_keyboard_arrow_up_48px.svg/768px-Ic_keyboard_arrow_up_48px.svg.png"
                }
                alt="Toggle Image"
                onClick={handleClick2} // Sử dụng handleClick khi ảnh được click
                className="arrow"
                style={{ height: "25px", width: "25px" }}
              />
              {showSlider2 && (
                <Box sx={{ width: 300 }}>
                  <Slider
                    getAriaLabel={() => "Price range"}
                    value={priceVal}
                    min={0}
                    max={100}
                    step={10}
                    onChange={handleChange2}
                    valueLabelDisplay="auto"
                  />
                </Box>
              )}
            </div>
            <div>
              <p>Nhà xe</p>
              <img
                src={
                  currentImage3 === "image1"
                    ? "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Ic_keyboard_arrow_down_48px.svg/768px-Ic_keyboard_arrow_down_48px.svg.png"
                    : "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Ic_keyboard_arrow_up_48px.svg/768px-Ic_keyboard_arrow_up_48px.svg.png"
                }
                alt="Toggle Image"
                onClick={handleClick3} // Sử dụng handleClick khi ảnh được click
                className="arrow"
                style={{ height: "25px", width: "25px" }}
              />
              {/* {showCheckbox &&
                bookings.map((booking) => (
                  <div>
                    <label key={booking.trip.tripid}>
                      <input
                        type="checkbox"
                        value={booking.nhaXe}
                        onChange={handleNhaXeChange}
                        checked={selectedNhaXe.includes(booking.nhaXe)}
                      />
                      {booking.nhaXe}
                    </label>
                    <br />
                  </div>
                ))} */}
            </div>
          </div>
        </div>
      </div>
      <div className="box right">
        <h1>Danh sách chuyến đi</h1>
        <div id="booking">
          <ul>
            {filteredAndSortedBookings.map((booking) => (
              <li key={booking.trip.tripid}>
                <strong>Giờ đi:</strong> {booking.trip.starttime} <br />
                <strong>Giờ đón:</strong> {booking.trip.endtime} <br />
                <strong>Giá vé:</strong> ${booking.seat.price} <br />
                {/* <strong>Đánh giá:</strong> {booking.rating} sao <br /> */}
                {/* <strong>Nhà xe:</strong> {booking.nhaXe} <br /> */}
                <div key={booking.trip.tripid}>
                  <button
                    className="button"
                    onClick={(event) =>
                      handleBookTicket(event, booking, booking.trip.tripid)
                    }
                  >
                    Chọn chuyến
                  </button>
                  {showPickSeat === booking.trip.tripid && (
                    <div>
                      <div>
                        {[...Array(30)].map((_, index) => {
                          if (
                            index < booking.trip.coach.number &&
                            index % 2 == 0 &&
                            index > 0
                          ) {
                            // Hiển thị ảnh 1
                            return (
                              <div>
                                <img
                                  src="https://cdn-icons-png.flaticon.com/512/1683/1683758.png"
                                  className="seat"
                                  onClick={handleSeat}
                                />
                                <img
                                  src="https://cdn-icons-png.flaticon.com/512/1683/1683758.png"
                                  className="seat"
                                  onClick={handleSeat}
                                />
                              </div>
                            );
                          } else if (
                            index == booking.trip.coach.number &&
                            booking.trip.coach.number % 2 == 1
                          ) {
                            return (
                              <img
                                src="https://cdn-icons-png.flaticon.com/512/1683/1683758.png"
                                className="seat"
                                onClick={handleSeat}
                              />
                            );
                          } else if (
                            index >= booking.trip.coach.number &&
                            index <= 30
                          ) {
                            return (
                              <img
                                src="https://cdn.iconscout.com/icon/premium/png-256-thumb/car-seat-1616720-1372229.png"
                                className="seat"
                                onClick={handleSeat}
                              />
                            );
                          } else {
                            // Trường hợp còn lại, không hiển thị
                            return null;
                          }
                        })}
                      </div>
                      <div>
                        <button
                          className="button"
                          onClick={(event) =>
                            handlePick(event, booking, booking.trip.tripid)
                          }
                        >
                          Tiếp tục
                        </button>
                        {/* {showLocation === booking.trip.tripid && (
                          <div>
                            <div>
                              <p>Điểm đón</p>
                              <div
                                style={{
                                  overflowY: "scroll",
                                  height: "100px",
                                  width: "100px",
                                  border: "1px solid #ccc",
                                  padding: "10px",
                                }}
                              >
                                {PickUp.map(
                                  (pickup) =>
                                    pickup.id === booking.id &&
                                    pickup.list1.map((point, index) => (
                                      <div key={index}>
                                        <input
                                          type="radio"
                                          id={`radio-${index}`}
                                          name="pickup-point"
                                          value={point}
                                        />
                                        <label htmlFor={`radio-${index}`}>
                                          {point}
                                        </label>
                                      </div>
                                    ))
                                )}
                              </div>
                            </div>
                            <div>
                              <p>Điểm trả</p>
                              <div
                                style={{
                                  overflowY: "scroll",
                                  height: "100px",
                                  width: "100px",
                                  border: "1px solid #ccc",
                                  padding: "10px",
                                }}
                              >
                                {Destination.map(
                                  (des) =>
                                    des.id === booking.id &&
                                    des.list2.map((point, index) => (
                                      <div key={index}>
                                        <input
                                          type="radio"
                                          id={`radio-${index}`}
                                          name="des-point"
                                          value={point}
                                        />
                                        <label htmlFor={`radio-${index}`}>
                                          {point}
                                        </label>
                                      </div>
                                    ))
                                )}
                              </div>
                            </div>
                            <Link to={"/payment"}>
                              <button className="button">Tiếp tục</button>
                            </Link>
                          </div>
                        )} */}
                      </div>
                    </div>
                  )}
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Booking;
