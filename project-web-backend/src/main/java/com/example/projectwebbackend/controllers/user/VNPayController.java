package com.example.projectwebbackend.controllers.user;

import com.example.projectwebbackend.service.VNPayService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@CrossOrigin
@Controller
@RequestMapping("/users")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;
    @PostMapping("/vnpay/{amount}")
    public ResponseEntity<String> submitPayment(@PathVariable long amount) throws UnsupportedEncodingException {
        String vnpayUrl = vnPayService.getPay(amount);
        return ResponseEntity.ok(vnpayUrl);
    }
    @GetMapping("/vnpay-status")
    public String payStatus(HttpServletRequest request, Model model){
        int paymentStatus =vnPayService.orderReturn(request);
        System.out.println(model);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        // Định dạng lại thời gian thanh toán
        LocalDateTime paymentDateTime = LocalDateTime.parse(paymentTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String formattedPaymentTime = paymentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", formattedPaymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
