package com.example.demo.repositories;

import com.example.demo.entities.Position;
import com.example.demo.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface PositionRepository extends JpaRepository<Position, Long> {


}
