package name.hersen.livesplits;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@Service
public class ImagesResources {
    List<String> getFileNames() {
        URL resourceUrl = getClass().getResource("/img");
        try {
            if (resourceUrl != null && resourceUrl.getProtocol().equals("file")) {
                File file = new File(resourceUrl.toURI());
                return asList(file.list());
            }
        } catch (URISyntaxException e) {
            return emptyList();
        }
        return emptyList();
    }

    byte[] getFullImage(String fileName) throws IOException {
        String s = "/img/" + fileName;
        InputStream resourceAsStream = getClass().getResourceAsStream(s);
        if (resourceAsStream == null) {
            return null;
        }
        return IOUtils.toByteArray(resourceAsStream);
    }

    byte[] getPreview(String fileName, Integer width) throws IOException {
        InputStream resourceAsStream = getInputStream(fileName, width);
        if (resourceAsStream == null) {
            resourceAsStream = getInputStream(fileName, 256);
            if (resourceAsStream == null) {
                return null;
            }
        }
        return IOUtils.toByteArray(resourceAsStream);
    }

    private InputStream getInputStream(String fileName, Integer width) {
        String s = "/" + width + "/" + fileName;
        return getClass().getResourceAsStream(s);
    }
}
