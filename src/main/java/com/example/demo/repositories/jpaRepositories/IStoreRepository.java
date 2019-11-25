package com.example.demo.repositories.jpaRepositories;

import com.example.demo.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface IStoreRepository extends JpaRepository<Store, Long> {
}
