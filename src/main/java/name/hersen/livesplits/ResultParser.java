package name.hersen.livesplits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

@Service
public class ResultParser {

    @Autowired
    private ImagesResources imagesResources;

    public List<String> getImages() {
        return imagesResources.getFileNames();
    }

    public byte[] getImageBytes(String fileName) throws IOException {
        byte[] r = imagesResources.getImageBytes(fileName);
        if (r == null) {
            throw new NotFoundException(fileName);
        }
        return r;
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
    public NotFoundException(String imageId) {
        super(imageId);
    }
}