package com.softpedia.DemoShoppingCart.repos;

import com.softpedia.DemoShoppingCart.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
