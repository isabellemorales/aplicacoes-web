package com.example.imagemPecas.application.images;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import com.example.imagemPecas.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;
    private final imageMapper mapper;

    // POST
    @PostMapping
    public ResponseEntity save (
            @RequestParam("file")MultipartFile file,
            @RequestParam("name")String name,
            @RequestParam("tags") List<String> tags
            )throws IOException
    {
        log.info("Imagem recebida: name: {}, size? {}", file.getOriginalFilename(), file.getSize());

        //log.info("Nome definido para a imagem: {},", name);
        //log.info("Tags: {}", tags);
        // tipo de arquivo que está recebendo:
        //log.info("Content type:{}", file.getContentType());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);

        URI imageUri = buildImageURL(savedImage);
        return ResponseEntity.created(imageUri).build();
    }

    // GET endereço ocalhost:8080/v1/images/{id}
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
        var possibleImage = service.getById(id);
        if(possibleImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        //POSTMAN INTERPRETE E RENDERIZE A IMAGEM
        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("", image.getName().concat("").concat(image.getExtension().name()));
    }


    //localhost:8080/v1/images/id
    private URI buildImageURL(Image image){
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest().path(imagePath).build().toUri();
    }
}
