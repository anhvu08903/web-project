import React, { useEffect, useState } from "react";
import axios from "axios";
import "./index.css";
import Rating from "@mui/material/Rating";
import Stack from "@mui/material/Stack";
const Ratings = () => {
  const [inputValue, setInputValue] = useState("");

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };
  //   console.log(inputValue);

  const [star, setStar] = useState(0);
  const handleRatingChange = (event, newValue) => {
    // console.log(newValue);
    setStar(newValue);
  };
  //   console.log(star);

  const handleSubmit = async () => {
    try {
      const response = await axios.post(
        "https://your-api-endpoint.com/submit",
        { data: inputValue }
      );
      console.log("Data submitted successfully:", response.data);
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
        <button onClick={handleSubmit}>Submit</button>
      </div>
    </div>
  );
};
export default Ratings;
