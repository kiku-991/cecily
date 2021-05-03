package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.MyOrderItemEntity;

@Repository
public interface MyOrderItemRepository extends JpaRepository<MyOrderItemEntity, Integer> {


}
