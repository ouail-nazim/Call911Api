package com.example.fireapiv1.Repository;

import com.example.fireapiv1.Model.Fire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireRepository extends JpaRepository<Fire, Long> {
}
