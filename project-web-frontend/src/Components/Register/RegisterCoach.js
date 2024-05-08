import React, { useEffect, useState } from "react";
import basestyle from "../Base.module.css";
import registerstyle from "./Register.module.css";
import axios from "axios";

import { useNavigate, NavLink } from "react-router-dom";

const RegisterCoach = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [confirmPassword, setConfirmPassword] = useState('');
  const [user, setUserDetails] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    password: "",
    address: "",
  });

  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
}

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };

  const validateForm = (values) => {
    const errors = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;

    if (!values.name) {
      errors.name = "Name is required";
    }

    if (!values.address) {
      errors.address = "Address is required";
    }

    if (!values.phoneNumber) {
      errors.phoneNumber = "phone number is required";
    }

    if (!values.email || !regex.test(values.email)) {
      errors.email = "Valid email is required";
    }

    if (!values.password || values.password.length < 6) {
      errors.password = "Password must be at least 6 characters long";
    }

    if (user.password !== confirmPassword) {
        errors.confirmPassword = "Confirmpassword and password must be the same"
    }


    return errors;
  };

  const signupHandler = (e) => {
    e.preventDefault();
    const errors = validateForm(user);
    setFormErrors(errors);

    if (Object.keys(errors).length === 0) {
      axios.post("http://localhost:4000/api/provider/register", user)
        .then((res) => {
          alert('Tạo tài khoản thành công');
          navigate("/login", { replace: true });
        })
        .catch((error) => {
          console.error("Error:", error);
          // Handle error, for example, display error message
        });
    }
  };

  return (
    <div className={registerstyle.container}>
      <div className={registerstyle.register}>
        <form>
          <h1>Create your account</h1>
          
          <input
            type="text"
            name="name"
            id="name"
            placeholder="Name"
            onChange={changeHandler}
            value={user.name}
          />
          <p className={basestyle.error}>{formErrors.name}</p>
          <input
            type="text"
            name="address"
            id="address"
            placeholder="Address"
            onChange={changeHandler}
            value={user.address}
          />
          <p className={basestyle.error}>{formErrors.address}</p>
          <input
            type="text"
            name="phoneNumber"
            id="phoneNumber"
            placeholder="Phone Number"
            onChange={changeHandler}
            value={user.phoneNumber}
          />
          <p className={basestyle.error}>{formErrors.phoneNumber}</p>
          <input
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            onChange={changeHandler}
            value={user.email}
          />
          <p className={basestyle.error}>{formErrors.email}</p>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            onChange={changeHandler}
            value={user.password}
          />
          <p className={basestyle.error}>{formErrors.password}</p>
          <input
            type="password"
            name="confirmpassword"
            id="confirmpassword"
            placeholder="Confirm Password"
            onChange={handleConfirmPasswordChange}
            value={confirmPassword}
          />
          <p className={basestyle.error}>{formErrors.confirmPassword}</p>
          <button className={basestyle.button_common} onClick={signupHandler} style={{backgroundColor: "#1890ff"}}>
            Register
          </button>
        </form>
        <NavLink to="/logincoach">Already registered? Login</NavLink>
      </div>
    </div>
  );
};

export default RegisterCoach;
