package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ShippingEntity;
@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, Integer>{

}
