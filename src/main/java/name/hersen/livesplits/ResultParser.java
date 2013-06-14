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
public class ResultParser {

    static final String resourceDirectory = "/img";

    List<String> getImages(SplitsController splitsController) {
        URL resourceUrl = splitsController.getClass().getResource(resourceDirectory);
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

    byte[] getImageBytes(String imageId, SplitsController splitsController) throws IOException {
        String s = "/" + imageId + ".JPG";
        InputStream resourceAsStream = splitsController.getClass().getResourceAsStream(resourceDirectory + s);
        return IOUtils.toByteArray(resourceAsStream);
    }
}
