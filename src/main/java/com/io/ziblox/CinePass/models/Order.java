package com.io.ziblox.CinePass.models;

import com.io.ziblox.CinePass.enums.OrderStatus;
import com.io.ziblox.CinePass.enums.PaymentMethod;
import com.io.ziblox.CinePass.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Keep the User relationship

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false) // You can keep this or rename it to totalPrice
    private double totalAmount; // Or rename to totalPrice

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;

    @Column(name = "full_name") // Added fullName
    private String fullName;

    @Column // Added email
    private String email;

    @Column(name = "phone_number") // Added phoneNumber
    private String phoneNumber;

    @Column  // Added address
    private String address;

    @Column // Added note
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column
    private String currency;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;
}
