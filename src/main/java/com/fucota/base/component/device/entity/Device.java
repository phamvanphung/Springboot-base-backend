package com.fucota.base.component.device.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.time.ZonedDateTime;

@Entity
@Table(name = "device")
@Getter
@Setter
@Accessors(chain = true)
@FieldNameConstants
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "terminal_id")
    private String terminalId;
    private String actualAccount;
    private String keyNetwork;
    private String nameAccount;
    private ZonedDateTime createdAt;
}
