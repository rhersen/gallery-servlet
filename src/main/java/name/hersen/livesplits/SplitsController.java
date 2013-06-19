package name.hersen.livesplits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class SplitsController {

    @Autowired
    private
    ResultParser parser;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void listImages(PrintWriter printWriter) throws URISyntaxException {
        printWriter.print(parser.getImages());
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    @ResponseBody
    public List<String> listImages() throws URISyntaxException {
        return parser.getImages();
    }

    @RequestMapping(value = "/images/{fileName:.+}", method = RequestMethod.GET)
    public void getImage(OutputStream outputStream,
                         @PathVariable("fileName") String fileName,
                         @RequestParam(required = false) Integer width) throws IOException {
        byte[] bytes = parser.getImageBytes(fileName, width);
        outputStream.write(bytes);
        System.out.println("wrote " + bytes.length + " bytes");
    }

}
