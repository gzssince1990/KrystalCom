package com.krystal.dao;

import com.krystal.model.Image;

import java.util.List;

/**
 * Created by zhisong on 3/10/2016.
 */
public interface ImageDAO {
    public void save(Image image);

    public List<Image> getImages(int product_id);

    public Image getImage(int image_id);

    public void delete(int product_id);
}
