package com.fj.dao;

import com.fj.entity.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import static javafx.scene.input.KeyCode.T;

@Repository
public interface MediaDAO extends CrudRepository<Media, Integer> {
    @Override
    Media save(Media media);
}
