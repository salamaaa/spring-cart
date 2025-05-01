package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.exception.ImageNotFoundException;
import com.softpedia.DemoShoppingCart.models.Image;
import com.softpedia.DemoShoppingCart.repos.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElseThrow(
                ()-> new ImageNotFoundException("Image not Found!")
        );
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImageById(MultipartFile multipartFile, Long imageId) {

    }

    @Override
    public void deleteImage(Long id) {

    }
}
