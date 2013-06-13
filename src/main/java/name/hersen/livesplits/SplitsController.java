package name.hersen.livesplits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class SplitsController {

    @Autowired
    private
    ResultParser parser;

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public void listImages(PrintWriter printWriter) throws URISyntaxException {
        List<String> strings = parser.getImages(this);
        printWriter.print(strings);
    }

    @RequestMapping(value = "/images/{imageId}", method = RequestMethod.GET)
    public void getImage(OutputStream outputStream, @PathVariable("imageId") String imageId) throws IOException {
        byte[] bytes = parser.getImageBytes(imageId, this);
        outputStream.write(bytes);
        System.out.println("wrote " + bytes.length + " bytes");
    }

}
