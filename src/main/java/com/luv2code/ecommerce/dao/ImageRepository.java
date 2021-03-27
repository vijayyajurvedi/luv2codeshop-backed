package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

}
