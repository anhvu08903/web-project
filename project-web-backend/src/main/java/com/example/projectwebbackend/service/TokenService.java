//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//import java.util.Date;
//
//@Service
//public class TokenService {
//
//    private static final String SECRET_KEY = "yourSecretKey"; // Key bí mật để tạo token
//
//    public String generateToken(String adminAccount) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + 3600000); // Token hết hạn sau 1 giờ
//        return Jwts.builder()
//                .setSubject(adminAccount)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//}
