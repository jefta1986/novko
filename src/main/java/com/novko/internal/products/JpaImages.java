package com.novko.internal.products;

public interface JpaImages {

    void setDefault(Long productId, Long imageId);
    Images getById(Long imageId);
    void remove(Long imageId);
    void update(Long imageId);

}
