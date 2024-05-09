import React, { useState, useEffect } from "react";
import basestyle from "../Base.module.css";
import loginstyle from "./Login.module.css";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
const Login = () => {
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    account: "",
    password: "",
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };
  const validateForm = (values) => {
    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.account) {
      error.account = "Account is required";
    }

    if (!values.password) {
      error.password = "Password is required";
    }
    return error;
  };

  const loginHandler = (e) => {
    e.preventDefault();
    setFormErrors(validateForm(user));
    setIsSubmit(true);
    // if (!formErrors) {
    console.log(formErrors);
    // }
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(user);
      axios
        .post("http://localhost:8080/identity/users/dangnhap", user)
        .then((res) => {
          alert("Dang nhap thanh cong!");
          if (typeof window !== "undefined")
            localStorage.setItem("token", res.data);
          navigate("/Homepage", { replace: true });
        });
      // if (typeof window !== "undefined")
      //   localStorage.setItem("token", 'da dang nhap');
      // navigate("/Homepage", { replace: true });
    }
  }, [formErrors]);

  return (
    <div className={loginstyle.container}>
      <div className={loginstyle.login}>
        <form>
          <h1>Login</h1>
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
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            onChange={changeHandler}
            value={user.password}
          />
          <p className={basestyle.error}>{formErrors.password}</p>
          <button className={basestyle.button_common} onClick={loginHandler}>
            Login
          </button>
        </form>
        <NavLink to="/signup">Not yet registered ? Register Now</NavLink>
      </div>
    </div>
  );
};
export default Login;
