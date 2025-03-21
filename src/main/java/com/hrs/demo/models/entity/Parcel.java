package com.hrs.demo.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Parcel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Parcel extends CommonBaseEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID guestId;

    private LocalDateTime receivedAt;

    private boolean pickedUp;

}
