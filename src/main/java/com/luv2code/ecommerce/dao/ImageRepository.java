package com.luv2code.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.ecommerce.entity.ImageModel;
 

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

	Page<ImageModel> findByImagenameContaining(@RequestParam("name") String name, Pageable pageable);
}
