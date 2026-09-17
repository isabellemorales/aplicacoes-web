package com.example.imagemPecas.infra.repository;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String>,
        JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        return findAll();
    }
}