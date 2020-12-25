package com.example.txmessagepoint.repository;

import com.example.txmessagepoint.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findByOrderNo(Long orderNo);
}
