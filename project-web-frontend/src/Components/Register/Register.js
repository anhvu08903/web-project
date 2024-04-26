import React, { useEffect, useState } from "react";
import basestyle from "../Base.module.css";
import registerstyle from "./Register.module.css";
import axios from "axios";

import { useNavigate, NavLink } from "react-router-dom";
const Register = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    password: "",
    cpassword: "",
    account: "",
  });

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

    if (!values.email) {
      errors.email = "Email is required";
    } else if (!regex.test(values.email)) {
      errors.email = "This is not a valid email format!";
    }
    if (!values.password) {
      errors.password = "Password is required";
    } else if (values.password.length < 4) {
      errors.password = "Password must be more than 4 characters";
    } else if (values.password.length > 10) {
      errors.password = "Password cannot exceed more than 10 characters";
    }
    if (!values.cpassword) {
      errors.cpassword = "Confirm Password is required";
    } else if (values.cpassword !== values.password) {
      errors.cpassword = "Confirm password and password should be same";
    }
    return errors;
  };

  const signupHandler = (e) => {
    e.preventDefault();
    setFormErrors(validateForm(user));
    setIsSubmit(true);
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      axios
        .post("http://localhost:8080/identity/users/dangky", user)
        .then((res) => {
          alert(res.data.message);
          navigate("/login", { replace: true });
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  }, [formErrors]);

  return (
    <>
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
              name="account"
              id="account"
              placeholder="Account"
              onChange={changeHandler}
              value={user.account}
            />
            <p className={basestyle.error}>{formErrors.account}</p>

            <input
              type="text"
              name="phoneNumber"
              id="phone"
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
              name="cpassword"
              id="cpassword"
              placeholder="Confirm Password"
              onChange={changeHandler}
              value={user.cpassword}
            />
            <p className={basestyle.error}>{formErrors.cpassword}</p>
            <button className={basestyle.button_common} onClick={signupHandler}>
              Register
            </button>
          </form>
          <NavLink to="/login">Already registered? Login</NavLink>
        </div>
      </div>
    </>
  );
};
export default Register;
