import React, {useState} from 'react';
import styles from './Coach.module.css';
import { Link, useNavigate} from 'react-router-dom';
import Trip from './Trip';
import TripForm from './TripForm';
import axios from 'axios';

const Coach = () => {
    
    const coachName = "{Placeholder}";

    const [buttonPopup, setButtonPopup] = useState(false);

    const [tripInfo, setTripInfo] = useState({
        startPlace: "",
        endPlace: "",
        startDate: "",
        endDate: "",
        startTime: "",
        endTime: "",
        seatType: "",
        remainingSeat: "0"
    })

    const handleChange = (e) => {
        setTripInfo({...tripInfo, [e.target.name]: e.target.value})
    } 

    const addTrip = async() => {
        let trip = tripInfo;
        console.log(trip);
        await axios.post("url nhe", trip)
            .then((res) => {
                res.json();
                
            })
        setButtonPopup(false);
        setTripInfo({
            startPlace: "",
            endPlace: "",
            startDate: "",
            endDate: "",
            startTime: "",
            endTime: "",
            seatType: "",
            remainingSeat: "0"
        })
    }

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
            endDate: ""
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
            endDate: ""
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
            endDate: ""
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
            endDate: ""
        }
    ]

    return (
        <div>
            <button className={`${styles.addTripButton} ${styles.buttons}`} onClick={() => setButtonPopup(true)}>
                Thêm chuyến xe
                <span></span>
            </button>

            <TripForm trigger={buttonPopup} setTrigger={setButtonPopup}>

                <div style={{width: "100%"}}>
                    <div className={styles.infoBoxWrapper}>
                        <div className={styles.infoBoxTitle}>Thông tin chuyến xe</div>
                        <form className={styles.infoBoxForm}>
                            <div className={styles.places}>
                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Nơi xuất phát*
                                    </label>
                                    <input className={styles.input} name='startPlace' onChange={handleChange} value={tripInfo.startPlace}></input>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Nơi đến*
                                    </label>
                                    <input className={styles.input} name='endPlace' onChange={handleChange} value={tripInfo.endPlace}></input>
                                </div>
                            </div>

                            <div className={styles.times}>
                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Ngày đi*
                                    </label>
                                    <input className={styles.input} name='startDate' onChange={handleChange} value={tripInfo.startDate}></input>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Ngày về*
                                    </label>
                                    <input className={styles.input} name='endDate' onChange={handleChange} value={tripInfo.endDate}></input>
                                </div>
                            </div>

                            <div className={styles.types}>
                                <div className={styles.types} style={{width: "100%"}}>
                                    <div className={styles.inputContainer}>
                                        <label className={styles.title}>
                                            Giờ đi*
                                        </label>
                                        <input className={styles.input} name='startTime' onChange={handleChange} value={tripInfo.startTime}></input>
                                    </div>

                                    <div className={styles.inputContainer}>
                                        <label className={styles.title}>
                                            Giờ về*
                                        </label>
                                        <input className={styles.input} name='endTime' onChange={handleChange} value={tripInfo.endTime}></input>
                                    </div>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Loại xe*
                                    </label>
                                    <input className={styles.input} name='seatType' onChange={handleChange} value={tripInfo.seatType}></input>
                                </div>
                            </div>
                        </form>

                        <div className={styles.searchButton}>
                            <button className={styles.buttons} id={styles.searchButton} onClick={addTrip}>
                                Tạo chuyến xe
                                <span></span>
                            </button>
                        </div>

                    </div>
                </div>

            </TripForm>

            <div className={styles.navbar}>
                <div className={styles.headerLeft}>

                </div>
                <ul className={styles.headerRight}>
                <div style={{display: "flex", flexDirection: "column"}}>
                    <li>Bạn đang đăng nhập dưới tư cách</li>
                    <li style={{fontSize: "16px", color: "#FFD333"}}>NHÀ XE {coachName}</li>
                </div>
                <div className={styles.signInButton}>
                    <button className={styles.buttons}>
                    <i className="material-icons-round">phone</i>
                    Hotline 24/7
                    <span></span>
                    </button>
                    <Link to='/homepage'>
                    <button className={styles.buttons}>
                        Đăng xuất
                        <span></span>
                    </button>
                    </Link>
                </div>
                </ul>
            </div>

            <div style={{display: "flex", justifyContent: "center"}}>
                <div style={{width: "1000px"}}>
                    <div style={{display: "flex", justifyContent: "space-between", marginTop: "40px"}}>
                        <div style={{display: "flex", gap: "40px", flexDirection: "column"}}>
                            {Trips.map((trip, i) => {
                                return <Trip 
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
    )
}

export default Coach;