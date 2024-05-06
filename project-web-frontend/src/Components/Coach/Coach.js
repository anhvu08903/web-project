import React, { useEffect, useState } from "react";
import styles from "./Coach.module.css";
import { Link, useNavigate } from "react-router-dom";
import Trip from "./Trip";
import TripForm from "./TripForm";
import axios from "axios";

// function convertToStandardDateFormat(datetimeLocal) {
//   // Tạo một đối tượng Date từ chuỗi datetime-local
//   var date = new Date(datetimeLocal);

//   // Lấy các thành phần ngày, tháng, năm, giờ, phút, giây từ đối tượng Date
//   var year = date.getFullYear();
//   var month = ("0" + (date.getMonth() + 1)).slice(-2); // Tháng bắt đầu từ 0 nên cần cộng thêm 1
//   var day = ("0" + date.getDate()).slice(-2);
//   var hour = ("0" + date.getHours()).slice(-2);
//   var minute = ("0" + date.getMinutes()).slice(-2);
//   var second = ("0" + date.getSeconds()).slice(-2);

//   // Trả về định dạng chuẩn của Date
//   return (
//     year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second
//   );
// }

const Coach = () => {
  const coachName = "{Placeholder}";

  const [buttonPopup, setButtonPopup] = useState(false);

  const [tripInfo, setTripInfo] = useState({
    starttime: "",
    endtime: "",
    startprovince: {
      pid: "",
      pname: "",
    },
    endprovince: {
      pid: "",
      pname: "",
    },
    licenseplate: "",
  });

  const handleChange = (e) => {
    setTripInfo({ ...tripInfo, [e.target.name]: e.target.value });
    if (e.target.name == "startprovince-pid")
      setTripInfo({
        ...tripInfo,
        startprovince: {
          pid: e.target.value,
          pname: tripInfo.startprovince.pname,
        },
      });
    if (e.target.name == "startprovince-pname")
      setTripInfo({
        ...tripInfo,
        startprovince: {
          pname: e.target.value,
          pid: tripInfo.startprovince.pid,
        },
      });
    if (e.target.name == "endprovince-pid")
      setTripInfo({
        ...tripInfo,
        endprovince: {
          pid: e.target.value,
          pname: tripInfo.endprovince.pname,
        },
      });
    if (e.target.name == "endprovince-pname")
      setTripInfo({
        ...tripInfo,
        endprovince: {
          pname: e.target.value,
          pid: tripInfo.endprovince.pid,
        },
      });
    console.log(tripInfo);
  };

  const addTrip = async () => {
    // var startdatetimeLocal = document.getElementsByName("starttime").value;
    // var startstandardDate = convertToStandardDateFormat(startdatetimeLocal);
    // var enddatetimeLocal = document.getElementsByName("endtime").value;
    // var endstandardDate = convertToStandardDateFormat(enddatetimeLocal);
    axios
      .post(
        "http://localhost:8080/identity/api/admin/add/trip",
        tripInfo

        // {
        //   startprovince: {
        //     pid: tripInfo.startprovince.pid,
        //     pname: tripInfo.startprovince.pname,
        //   },
        //   endprovince: {
        //     pid: tripInfo.endprovince.pid,
        //     pname: tripInfo.endprovince.pname,
        //   },
        //   starttime: startstandardDate ? startstandardDate : "",
        //   endtime: endstandardDate ? endstandardDate : "",
        //   licenseplate: tripInfo.licenseplate,
        // }
      )
      .then((res) => {
        alert("thanh cong ");
      });
    setButtonPopup(false);
    setTripInfo({
      starttime: "",
      endtime: "",

      startprovince: {
        pid: "",
        pname: "",
      },
      endprovince: {
        pid: "",
        pname: "",
      },
      licenseplate: "",
    });
  };

  // const AddTrip = (e) => {
  //     e.preventDefault();
  //
  // };
  //
  // useEffect(() => {
  //     {
  //
  //         axios.post("http://localhost:8080/api/admin/add/trip", tripInfo).then((res) => {
  //             alert(res.data.message);
  //
  //
  //         });
  //     }
  // }, []);

  const Trips = [
    {
      id: 1,
      seatType: 45,
      remainingSeat: 12,
      startTime: "5:15",
      endTime: "6:45",
      startPlace: "Hà Lội",
      endPlace: "Nha Chang",
      startDate: "",
      endDate: "",
    },
    {
      id: 2,
      seatType: 15,
      remainingSeat: 3,
      startTime: "5:15",
      endTime: "6:45",
      startPlace: "Hà Lội",
      endPlace: "Nha Chang",
      startDate: "",
      endDate: "",
    },
    {
      id: 3,
      seatType: 15,
      remainingSeat: 5,
      startTime: "5:15",
      endTime: "6:45",
      startPlace: "Hà Lội",
      endPlace: "Xài Gòn",
      startDate: "",
      endDate: "",
    },
    {
      id: 4,
      seatType: 45,
      remainingSeat: 14,
      startTime: "5:15",
      endTime: "6:45",
      startPlace: "Xài Gòn",
      endPlace: "Lam Định",
      startDate: "",
      endDate: "",
    },
  ];

  return (
    <div>
      <button
        className={`${styles.addTripButton} ${styles.buttons}`}
        onClick={() => setButtonPopup(true)}
      >
        Thêm chuyến xe
        <span></span>
      </button>

      <TripForm trigger={buttonPopup} setTrigger={setButtonPopup}>
        <div style={{ width: "100%" }}>
          <div className={styles.infoBoxWrapper}>
            <div className={styles.infoBoxTitle}>Thông tin chuyến xe</div>
            <form className={styles.infoBoxForm}>
              <div className={styles.places}>
                <div className={styles.inputContainer}>
                  <label className={styles.title}>Ma Tinh Di</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="startprovince-pid"
                    onChange={handleChange}
                    value={tripInfo.startprovince.pid}
                  ></input>
                </div>

                <div className={styles.inputContainer}>
                  <label className={styles.title}>Ten Tinh Di</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="startprovince-pname"
                    onChange={handleChange}
                    value={tripInfo.startprovince.pname}
                  ></input>
                </div>

                <div className={styles.inputContainer}>
                  <label className={styles.title}>Ma Tinh Den</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="endprovince-pid"
                    onChange={handleChange}
                    value={tripInfo.endprovince.pid}
                  ></input>
                </div>

                <div className={styles.inputContainer}>
                  <label className={styles.title}>Ten Tinh Den</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="endprovince-pname"
                    onChange={handleChange}
                    value={tripInfo.endprovince.pname}
                  ></input>
                </div>
              </div>

              <div className={styles.times}>
                <div className={styles.inputContainer}>
                  <label className={styles.title}>Thoi Gian Di</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="starttime"
                    onChange={handleChange}
                    value={tripInfo.starttime}
                  ></input>
                </div>

                <div className={styles.inputContainer}>
                  <label className={styles.title}>Thoi Gian Den</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="endtime"
                    onChange={handleChange}
                    value={tripInfo.endtime}
                  ></input>
                </div>
              </div>

              <div className={styles.types}>
                <div className={styles.inputContainer}>
                  <label className={styles.title}>Bien So Xe</label>
                  <input
                    type="text"
                    className={styles.input}
                    name="licenseplate"
                    onChange={handleChange}
                    value={tripInfo.licenseplate}
                  ></input>
                </div>
              </div>
            </form>

            <div className={styles.searchButton}>
              <button
                className={styles.buttons}
                id={styles.searchButton}
                onClick={addTrip}
              >
                Tạo chuyến xe
                <span></span>
              </button>
            </div>
          </div>
        </div>
      </TripForm>

      <div className={styles.navbar}>
        <div className={styles.headerLeft}></div>
        <ul className={styles.headerRight}>
          <div style={{ display: "flex", flexDirection: "column" }}>
            <li>Bạn đang đăng nhập dưới tư cách</li>
            <li style={{ fontSize: "16px", color: "#FFD333" }}>
              NHÀ XE {coachName}
            </li>
          </div>
          <div className={styles.signInButton}>
            <button className={styles.buttons}>
              <i className="material-icons-round">phone</i>
              Hotline 24/7
              <span></span>
            </button>
            <Link to="/homepage">
              <button className={styles.buttons}>
                Đăng xuất
                <span></span>
              </button>
            </Link>
          </div>
        </ul>
      </div>

      <div style={{ display: "flex", justifyContent: "center" }}>
        <div style={{ width: "1000px" }}>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginTop: "40px",
            }}
          >
            <div
              style={{ display: "flex", gap: "40px", flexDirection: "column" }}
            >
              {Trips.map((trip, i) => {
                return (
                  <Trip
                    key={i}
                    // id={trip.id}
                    // seatType={trip.seatType}
                    // remainingSeat={trip.remainingSeat}
                    // startTime={trip.startTime}
                    // endTime={trip.endTime}
                    // startPlace={trip.startPlace}
                    // endPlace={trip.endPlace}
                    trip={trip}
                  />
                );
              })}
            </div>
            <div className={styles.money}>
              <div className={styles.moneyWrapper}>
                <div className={styles.tripTitle}>Thống kê chuyến đi</div>
                <p>{Trips.length}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Coach;
