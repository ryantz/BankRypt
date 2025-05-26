package com.app.bankrypt.model;

import com.app.bankrypt.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User_Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @Column(name="transaction_type", nullable=false)
    private TransactionType transactType;

    @Column(name="requires_approval", nullable=false)
    private Boolean requiresApproval = false;

    @Column(name="before_transaction_value", nullable=false)
    private BigDecimal balanceBeforeTransact;

    @Column(name="after_transaction_value", nullable=false, precision=19, scale=2)
    private BigDecimal balanceAfterTransact;

    @Column(name="transaction_datetime", nullable=false)
    private LocalDateTime transactTimestamp;

    @Column(name="is_approved", nullable=false)
    private Boolean isApproved = false;

    // only needed when transactions exceed an amount, LARGE_PAYMENT
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id", nullable = true)
    private Users approverId;
}
