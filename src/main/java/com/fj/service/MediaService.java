package com.fj.service;

import com.fj.dao.MediaDAO;
import com.fj.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    private MediaDAO mediaDAO;

    @Autowired
    public void setMediaDAO(MediaDAO mediaDAO) {
        this.mediaDAO = mediaDAO;
    }

    public Media saveMedia(Media media) {
        return mediaDAO.save(media);
    }
}
