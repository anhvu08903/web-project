import React, { useEffect, useState } from "react";
import axios from "axios";
import "./index.css";
import Rating from "@mui/material/Rating";
import Stack from "@mui/material/Stack";
import { useLocation } from "react-router-dom";
import moment from "moment";

const Ratings = () => {
  // const location = useLocation();
  // const { currentBooking } = location.state;
  const currentBooking = JSON.parse(sessionStorage.getItem("booking"));
  console.log(currentBooking);

  const [inputValue, setInputValue] = useState("");

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const [star, setStar] = useState(0);
  const handleRatingChange = (event, newValue) => {
    setStar(newValue);
  };

  const token = sessionStorage.getItem("token");
  const [user, setUser] = useState({});

  async function getUserInfo() {
    try {
      const response = await axios.get(
        `http://localhost:8080/identity/users/tk/${token}`
      );
      const data = response.data;
      setUser(data);
      console.log(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      await getUserInfo();
    };
    fetchData();
  }, []);

  const now = moment().format("YYYY-MM-DDTHH:mm:ss.SSSZ");

  const handleSubmit = async () => {
    try {
      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const response = await axios.post(
        `http://localhost:8080/identity/users/binhluan`,
        {
          content: inputValue,
          user: user,
          admin: currentBooking.admin,
          createdAt: now,
          star: star,
        },
        { headers: headers }
      );
      console.log("Data submitted successfully:", response.data);
    } catch (error) {
      console.error("There was an error submitting the data!", error);
    }
  };

  const ticket = JSON.parse(sessionStorage.getItem("ticket"));

  const ticketID = parseInt(ticket[ticket.length - 1].ticketid);
  console.log(ticketID);

  const handleSetStatus = async () => {
    try {
      const response = await axios.post(
        `http://localhost:8080/identity/api/admin/ticket/grant/${ticketID}`,
        {
      
        }
      );
      console.log("Data submitted successfully:", response.data);
      console.log(ticketID);
    } catch (error) {
      console.error("There was an error submitting the data!", error);
    }
  };

  return (
    <div className="background">
      <div className="container1">
        <input
          type="text"
          value={inputValue}
          onChange={handleInputChange}
          placeholder="Enter your text here"
        />
        <Stack spacing={1}>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={0.5}
            onChange={handleRatingChange}
          />
        </Stack>
        <button
          onClick={() => {
            handleSubmit();
            handleSetStatus();
          }}
        >
          Submit
        </button>
      </div>
    </div>
  );
};

export default Ratings;
