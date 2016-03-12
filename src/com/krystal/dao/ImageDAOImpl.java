package com.krystal.dao;

import com.krystal.model.Image;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhisong on 3/10/2016.
 */
@Repository
@Transactional
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Image image) {
        delete(image.getProduct_id());
        sessionFactory.getCurrentSession().save(image);
    }

    public List<Image> getImages(int product_id) {
        List<Image> images = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM image WHERE product_id="+product_id)
                .addEntity(Image.class).list();

        return images;
    }

    public Image getImage(int image_id) {
        List<Image> images = sessionFactory.getCurrentSession()
                        .createSQLQuery("SELECT * FROM image WHERE image_id="+image_id)
                        .addEntity(Image.class).list();

        return images.isEmpty()?null:images.get(0);
    }

    public void delete(int product_id) {
        List<Image> images = getImages(product_id);
        for (Image image: images){
            sessionFactory.getCurrentSession().delete(image);
        }
    }

}
