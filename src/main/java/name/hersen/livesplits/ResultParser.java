package name.hersen.livesplits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ResultParser {

    @Autowired
    private ImagesResources imagesResources;

    public List<String> getImages() {
        return imagesResources.getImages();
    }

    public byte[] getImageBytes(String imageId) throws IOException {
        return imagesResources.getImageBytes(imageId);
    }
}
