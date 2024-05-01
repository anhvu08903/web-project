import React, {useState} from 'react';
import styles from './Coach.module.css';
import { Link, useNavigate} from 'react-router-dom';
import Trip from './Trip';

const Coach = () => {
    
const coachName = "{Placeholder}";

    return (
        <div>
            <button className={`${styles.addTripButton} ${styles.buttons}`}>
                Thêm chuyến xe
                <span></span>
            </button>

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