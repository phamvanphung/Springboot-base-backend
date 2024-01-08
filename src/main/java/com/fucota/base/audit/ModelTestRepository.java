package com.fucota.base.audit;

import com.fucota.base.audit.entity.ModelTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTestRepository extends JpaRepository<ModelTest, String> {
}
