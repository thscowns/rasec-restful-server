package com.rasec.server.core;

import com.rasec.server.config.DeviceConfig;
import com.rasec.server.model.Device;
import com.rasec.server.model.PhotoGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ApiController {
    @GetMapping("RaSec/devices/**")
    public Device getDevices() {
        return DeviceConfig.device;
    }

    @GetMapping("RaSec/videos/{movieName}")
    public ModelAndView getStreaming(@PathVariable String movieName) throws UnsupportedEncodingException{
        return new ModelAndView("streamView", "movieName", movieName);
    }

    @GetMapping("RaSec/photos/{photoID}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String photoID) throws IOException{
        log.info(photoID);
        BufferedImage bImage = ImageIO.read(new File("./photos/" + photoID + ".jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", baos);
        byte[] image = baos.toByteArray();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @GetMapping("RaSec/Photos")
    public PhotoGroup getPhotoGroup(){
        File[] files = new File("./photos/").listFiles();
        List<String> results = new ArrayList<String>();
        for (File file : files){
            if (file.isFile()){
                results.add(file.getName());
            }
        }
        return PhotoGroup.builder()
                .groupID("RaSec")
                .fileList(results)
                .build();
    }

    @PostMapping("RaSec/devices")
    public Device postDevice(@RequestBody Device device) {
        log.info(device.toString());
        if (DeviceConfig.device.getDeviceId().equals(device.getDeviceId())){
            if (device.getBuzzerState() != null){
                DeviceConfig.device.setBuzzerState(device.getBuzzerState());
            }
            if (device.getCamState() != null) {
                DeviceConfig.device.setCamState(device.getCamState());
            }
        }
        else{
            throw new NotFoundException(device.getDeviceId() + " does not exists");
        }
        return DeviceConfig.device;
    }

    @PutMapping("RaSec/devices")
    public Device putDevice(@RequestBody Device device) {
        return postDevice(device);
    }
}
