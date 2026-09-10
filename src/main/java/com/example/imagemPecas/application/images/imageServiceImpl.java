package com.example.imagemPecas.application.images;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.service.ImageService;
import com.example.imagemPecas.infra.repository.imageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class imageServiceImpl implements ImageService {

    private final imageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return  repository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }
}
