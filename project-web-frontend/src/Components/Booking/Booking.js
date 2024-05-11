import React, { useEffect, useState } from "react";
import "./test.css";
import SortOptions from "./SortOptions";
import Box from "@mui/material/Box";
import Slider from "@mui/material/Slider";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { number } from "prop-types";

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

  const bookings = array;

  const [ratings, setRatings] = useState({});

  async function fetchDataAndRender(adminId) {
    try {
      const response = await axios.get(
          `http://localhost:8080/identity/api/admin/comment/rate/${adminId}`
      );
      const data = response.data;
      setRatings((prevRatings) => ({ ...prevRatings, [adminId]: data }));
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }

  useEffect(() => {
    // Fetch and render ratings for all admins
    const fetchData = () => {
      filteredAndSortedBookings.forEach(async (booking) => {
        await fetchDataAndRender(booking.admin.adminid);
      });
    };

    fetchData();
  }, [filteredAndSortedBookings]);

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
      return list.filter((booking) =>
          selectedNhaXe.includes(booking.admin.adminname)
      );
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
      case "highest":
        return list
            .slice()
            .sort((a, b) => ratings[b.admin.adminid] - ratings[a.admin.adminid]);
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
    // console.log("Booking được chọn:", booking);
    setShowPickSeat(id);
    setCurrentBookingPrice(booking.seat.price);
  };

  // const handleSeat = () => {
  //   console.log(currentBookingPrice);
  // };

  const [showLocation, setShowLocation] = useState(null); //State quản lý div điểm đón điểm trả
  const handlePick = (event, booking, id) => {
    event.preventDefault();
    setShowLocation(id);
  };

  const renderSeatNumbers = (booking) => {
    return Array.from(
        { length: booking.trip.coach.number },
        (_, index) => index + 1
    );
  };

  const [picticket, setPickTicKet] = useState("");

  const handlePickTicketChange = (event) => {
    setPickTicKet(event.target.value);
  };
  const [pickup, setPickUp] = useState([]);

  const handlePickupPointChange = (event) => {
    setPickUp(event.target.value);
  };

  // async function getTrip() {
  //   try {
  //     const response = await axios.get(
  //       `http://localhost:8080/identity/users/danhsachtatcacacghetrenchuyenxe`
  //     );
  //     const data = response.data;
  //     console.log(data);
  //   } catch (error) {
  //     console.error("Error fetching data:", error);
  //   }
  // }


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
                {showCheckbox &&
                    bookings.map((booking) => (
                        <div>
                          <label key={booking.trip.tripid}>
                            <input
                                type="checkbox"
                                value={booking.admin.adminname}
                                onChange={handleNhaXeChange}
                                checked={selectedNhaXe.includes(
                                    booking.admin.adminname
                                )}
                            />
                            {booking.admin.adminname}
                          </label>
                          <br />
                        </div>
                    ))}
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
                    <div>
                      <div className="container">
                        <img
                            src="https://static.vexere.com/production/images/1690435601693.jpeg?w=250&h=250"
                            className="booking_img"
                        ></img>
                        <strong>Nhà xe:</strong> {booking.admin.adminname} <br />
                        <strong>Giờ đi:</strong> {booking.trip.starttime} <br />
                        <strong>Giờ đón:</strong> {booking.trip.endtime} <br />
                        <strong>Giá vé:</strong> ${booking.seat.price} <br />
                        <strong>Đánh giá:</strong> {ratings[booking.admin.adminid]}{" "}
                        sao
                        <br />
                        <button
                            className="button"
                            onClick={(event) =>
                                handleBookTicket(event, booking, booking.trip.tripid)
                            }
                        >
                          Chọn chuyến
                        </button>
                      </div>
                      {showPickSeat === booking.trip.tripid && (
                          <div>
                            <div className="show1">
                              <p>Còn{booking.trip.coach.number} chỗ</p>
                              <p>Chọn ghế:</p>
                              {renderSeatNumbers(booking).map((number) => (
                                  <div key={number}>
                                    <input
                                        type="checkbox"
                                        id={`seat-${number}`}
                                        value={number}
                                    />
                                    <label htmlFor={`seat-${number}`}>
                                      Ghế {number}
                                    </label>
                                  </div>
                              ))}

                              <button
                                  className="button"
                                  onClick={(event) =>
                                      handlePick(event, booking, booking.trip.tripid)
                                  }
                              >
                                Tiếp tục
                              </button>
                            </div>
                            {showLocation === booking.trip.tripid && (
                                <div className="show2">
                                  <div>
                                    <p>Điểm đón</p>
                                    <div>
                                      <Box sx={{ minWidth: 120 }}>
                                        <FormControl style={{ width: "200px" }}>
                                          <InputLabel id="pick-up">Điểm đón</InputLabel>
                                          <Select
                                              labelId="pick-up"
                                              id="pick-up"
                                              label="Điểm đón"
                                              onChange={(event) => {
                                                handlePickupPointChange(event);
                                                // console.log(
                                                //   `Giá trị được chọn: ${event.target.value}`
                                                // );
                                              }}
                                          >
                                            {booking.pickAddress.map((pick) => (
                                                <MenuItem value={pick.pickname}>
                                                  {pick.pickname}
                                                </MenuItem>
                                            ))}
                                          </Select>
                                        </FormControl>
                                      </Box>
                                    </div>
                                  </div>
                                  <div>
                                    <p>Điểm trả</p>
                                    <div>
                                      <Box sx={{ minWidth: 120 }}>
                                        <FormControl style={{ width: "200px" }}>
                                          <InputLabel id="drop-off">
                                            Điểm trả
                                          </InputLabel>
                                          <Select
                                              labelId="drop-off"
                                              id="drop-off"
                                              label="Điểm trả"
                                              onChange={(event) => {
                                                handlePickupPointChange(event);
                                                // console.log(
                                                //   `Giá trị được chọn: ${event.target.value}`
                                                // );
                                              }}
                                          >
                                            {booking.returnAddress.map((dropoff) => (
                                                <MenuItem value={dropoff.returnaddress}>
                                                  {dropoff.returnaddress}
                                                </MenuItem>
                                            ))}
                                          </Select>
                                        </FormControl>
                                      </Box>
                                    </div>
                                  </div>
                                  <Link to={"/payment"}>
                                    <button className="button">Tiếp tục</button>
                                  </Link>
                                </div>
                            )}
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
