package com.soa.plantes.controllers;

import com.soa.plantes.dao.ImageRepository;
import com.soa.plantes.dao.PlanteRepository;
import com.soa.plantes.models.Image;
import com.soa.plantes.models.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PlanteRepository planteRepository;

    @PostMapping("/upload/{id}")
    public Image uploadImage (@RequestParam("myFile")MultipartFile file ,@PathVariable("id") Long id) throws IOException{

        Optional<Plante> p = this.planteRepository.findById(id);
        Image img= new Image((file.getOriginalFilename()), file.getContentType(), file.getBytes(), p.get());
        final Image savedImage = imageRepository.save(img);
        return savedImage;
    }
}
