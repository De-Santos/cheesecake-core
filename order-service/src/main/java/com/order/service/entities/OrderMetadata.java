package com.order.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "order_metadata")
@NoArgsConstructor
@AllArgsConstructor
public class OrderMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    private Order order;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "message")
    private String message;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "required_done_time")
    private Date requiredDoneTime;
}
