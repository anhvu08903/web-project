import React, {useState} from 'react';
import styles from './Coach.module.css';
import { Link, useNavigate} from 'react-router-dom';

const Trip = () => {
    const tripID = "{TripIDPlaceholder}";
    const seatType = "{45}";
    const remainingSeat = "{12}";

    const startTime = "5:15";
    const endTime = "6:45";
    const startPlace = "Hà Lội";
    const endPlace = "Nha Chang";

    const tripTime = "1h30m"

    return (
        <div className={styles.infoBox}>

            <div className={styles.tripInfoWrapper}>
                <div style={{display: "flex", justifyContent: "space-between"}}>
                    <div className={styles.tripTitle}>Chuyến đi {tripID}</div>
                    <div className={styles.tripTitle} style={{color: "#2474E5"}}>Còn {remainingSeat} chỗ</div>
                </div>
                <div className={styles.tripInfo}>
                    <div>
                        Chuyến xe {seatType} chỗ
                    </div>
                    <div className={styles.tripRoute}>
                        <svg class="TicketPC__LocationRouteSVG-sc-1mxgwjh-4 eKNjJr" xmlns="http://www.w3.org/2000/svg" width="14" height="74" viewBox="0 0 14 74"><path fill="none" stroke="#787878" stroke-linecap="round" stroke-width="2" stroke-dasharray="0 7" d="M7 13.5v46"></path><g fill="none" stroke="#484848" stroke-width="3"><circle cx="7" cy="7" r="7" stroke="none"></circle><circle cx="7" cy="7" r="5.5"></circle></g><path d="M7 58a5.953 5.953 0 0 0-6 5.891 5.657 5.657 0 0 0 .525 2.4 37.124 37.124 0 0 0 5.222 7.591.338.338 0 0 0 .506 0 37.142 37.142 0 0 0 5.222-7.582A5.655 5.655 0 0 0 13 63.9 5.953 5.953 0 0 0 7 58zm0 8.95a3.092 3.092 0 0 1-3.117-3.06 3.117 3.117 0 0 1 6.234 0A3.092 3.092 0 0 1 7 66.95z" fill="#787878"></path></svg>
                        <div className={styles.tripRouteInfo}>
                            
                                <div className={styles.contentTrip}>
                                    <div style={{color: "#484848", fontSize: "20px", fontWeight: "bold"}}>{startTime}</div>
                                    <div class="place">• {startPlace}</div>
                                </div>
                                <div style={{color: "#A1A1A1"}}>{tripTime}</div>
                                <div className={styles.contentTrip}>
                                    <div style={{color: "#707070", fontSize: "20px", fontWeight: "bold"}}>{endTime}</div>
                                    <div class="place">• {endPlace}</div>
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Trip;