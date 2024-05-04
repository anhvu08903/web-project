import React, {useState} from 'react';
import styles from './Coach.module.css';
import { Link, useNavigate} from 'react-router-dom';
import Trip from './Trip';
import TripForm from './TripForm';

const Coach = () => {
    
const coachName = "{Placeholder}";

const [buttonPopup, setButtonPopup] = useState(false);

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
                                    <input className={styles.input}></input>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Nơi đến*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>
                            </div>

                            <div className={styles.times}>
                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Ngày đi*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Ngày về*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>
                            </div>

                            <div className={styles.types}>
                                <div className={styles.inputContainer} style={{width: "17%"}}>
                                    <label className={styles.title}>
                                        Giờ đi*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>

                                <div className={styles.inputContainer} style={{width: "17%"}}>
                                    <label className={styles.title}>
                                        Giờ về*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.title}>
                                        Loại xe*
                                    </label>
                                    <input className={styles.input}></input>
                                </div>
                            </div>
                        </form>

                        <div className={styles.searchButton}>
                            <button className={styles.buttons} id={styles.searchButton}>
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
                            <Trip></Trip>
                            <Trip></Trip>
                        </div>
                        <div className={styles.money}>
                            <div className={styles.moneyWrapper}>
                                <div className={styles.tripTitle}>Thống kê chuyến đi</div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Coach;