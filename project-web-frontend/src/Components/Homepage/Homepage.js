import React from 'react';
import styles from './Homepage.module.css';
import { useNavigate } from 'react-router-dom';



const Homepage = () => {
  // const navigate = useNavigate();

  // const handleLoginButtonClick = () => {
  //   navigate('/login');
  // };


  return (
    <div>
      <div className={styles.navbar}>
        <div className={styles.headerLeft}>

        </div>
        <ul className={styles.headerRight}>
          <li>Đăng ký mở bán vé</li>
          <div className={styles.signInButton}>
            <button className={styles.buttons}>
              <i className="material-icons-round">phone</i>
              Hotline 24/7
              <span></span>
            </button>
            {/* thêm chức năng xử lý route vào button */}
            <button className={styles.buttons} /*onClick={handleLoginButtonClick}*/>
              Đăng nhập
              <span></span>
            </button>
          </div>
        </ul>
      </div>
      <div className={styles.banner}>
        {/* test ảnh vì bị lỗi homepage */}
        <img className={styles.bannerBackground} src="https://229a2c9fe669f7b.cmccloud.com.vn/images/banner-main-vi.jpg"/>
        <div className={styles.bannerMiddle}>
          <h2>Đặt vé xe đơn giản và an toàn</h2>
          <div className={styles.ticketWidget}>
            <div className={styles.infoRow}>
              <div className={styles.info} id={styles.startPoint}>

              </div>
              <div className={styles.info} id={styles.endPoint}>
 
              </div>
              <div className={styles.info} id={styles.startDate}>
   
              </div>
              <div className={styles.info} id={styles.endDate}>
   
              </div>
            </div>
            <div className={styles.searchButton}>
              <button className={styles.buttons} id={styles.searchButton}>
                Tìm kiếm chuyến xe ngay
                <span></span>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.end}>
        <div className={styles.background}></div>
        <div className={styles.endItems}>
          <div className={styles.items}>
            <img className={styles.itemImg} src="https://229a2c9fe669f7b.cmccloud.com.vn/svgIcon/verified_yellow.svg" alt="Verified" />
            <p className={styles.title}>Chắc chắn có chỗ</p>
          </div>
          <div className={styles.items}>
            <img className={styles.itemImg} src="https://229a2c9fe669f7b.cmccloud.com.vn/svgIcon/headset_mic_yellow.svg" alt="Headset" />
            <p className={styles.title}>Hỗ trợ 24/7</p>
          </div>
          <div className={styles.items}>
            <img className={styles.itemImg} src="https://229a2c9fe669f7b.cmccloud.com.vn/svgIcon/discount_yellow.svg" alt="Discount" />
            <p className={styles.title}>Nhiều ưu đãi</p>
          </div>
          <div className={styles.items}>
            <img className={styles.itemImg} src="https://229a2c9fe669f7b.cmccloud.com.vn/svgIcon/monetization_on_yellow.svg" alt="Payment" />
            <p className={styles.title}>Thanh toán đa dạng</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Homepage;
