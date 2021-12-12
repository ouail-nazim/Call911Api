package com.example.fireapiv1.Repository;

import com.example.fireapiv1.Model.Fireman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmanRepository extends JpaRepository<Fireman, Long> {
}
