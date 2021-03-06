package com.jennilyn.repositories;

import com.jennilyn.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByTitle(String title);
}
