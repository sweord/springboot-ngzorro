package com.fj.springbootngzorro;

import com.fj.entity.Media;
import com.fj.service.MediaService;
import org.h2.store.fs.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Autowired
    MediaService mediaService;

    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadFile) {

        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String filename = uploadFile.getOriginalFilename();
            String homeDirectory = System.getProperty("user.home");
            Path directory = Paths.get(homeDirectory + File.separator + "uploads");
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            String filePath = directory + File.separator + filename;

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(uploadFile.getBytes());
            stream.close();

            Media media = new Media();
            media.setSize((int)uploadFile.getSize());
            media.setStorePath(filePath);
            mediaService.saveMedia(media);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    } // method uploadFile
}
