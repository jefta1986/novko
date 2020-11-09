package com.novko.internal.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

    void deleteById(Long id);
    List<Images> findAll();

    @Query("select i from Images i where i.product = ?1")
    List<Images> findImagesByProductId(Long id);

    @Query("select i from Images i where i.product = ?1 and i.defaultPicture=true")
    Images findDefaultImageByProductId(Long id);

}
