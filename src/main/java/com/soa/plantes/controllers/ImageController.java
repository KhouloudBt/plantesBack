package com.soa.plantes.controllers;

import com.soa.plantes.dao.ImageRepository;
import com.soa.plantes.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    public Image uploadImage (@RequestParam("myFile")MultipartFile file ) throws IOException{

        Image img= new Image((file.getOriginalFilename()), file.getContentType(), file.getBytes());
        final Image savedImage = imageRepository.save(img);
        return savedImage;
    }
}
