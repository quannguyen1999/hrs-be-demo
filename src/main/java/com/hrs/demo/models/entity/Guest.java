package com.hrs.demo.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Guest")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Guest extends CommonBaseEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private boolean checkedIn;

    private String phone;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

}
