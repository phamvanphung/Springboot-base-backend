package com.fucota.base.component.device.repository;

import com.fucota.base.component.device.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String>, JpaSpecificationExecutor<Device> {
    Optional<Device> getDeviceByDeviceId(String did);
}
