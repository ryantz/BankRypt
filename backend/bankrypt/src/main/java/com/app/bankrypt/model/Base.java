package com.app.bankrypt.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", nullable=true)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="updated_by", nullable=true)
    private Users updatedBy;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isDeleted;
    private Boolean isUpdated;
}
