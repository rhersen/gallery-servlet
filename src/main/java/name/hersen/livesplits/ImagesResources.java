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
    List<String> getImages() {
        URL resourceUrl = getClass().getResource("/img");
        List<String> strings = null;
        try {
            if (resourceUrl != null && resourceUrl.getProtocol().equals("file")) {
                File file = new File(resourceUrl.toURI());
                strings = asList(file.list());
            }
        } catch (URISyntaxException e) {
            strings = emptyList();
        }
        return strings;
    }

    byte[] getImageBytes(String imageId) throws IOException {
        String s = "/" + imageId + ".JPG";
        InputStream resourceAsStream = getClass().getResourceAsStream("/img" + s);
        return IOUtils.toByteArray(resourceAsStream);
    }
}
