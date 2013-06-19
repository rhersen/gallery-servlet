package name.hersen.livesplits;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class ResultParserTest {

    private ResultParser target;
    private ImagesResources imagesResources;
    private byte[] full;
    private byte[] preview;

    @Before
    public void setUp() throws Exception {
        full = new byte[]{1};
        preview = new byte[]{2};
        imagesResources = mock(ImagesResources.class);
        when(imagesResources.getFileNames()).thenReturn(asList("single"));
        when(imagesResources.getFullImage("found")).thenReturn(full);
        when(imagesResources.getPreview("found", 256)).thenReturn(preview);
        target = new ResultParser();
        setField(target, "imagesResources", imagesResources);
    }

    @Test
    public void getImages() throws Exception {
        List<String> result = target.getImages();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void getImageBytes() throws Exception {
        byte[] result = target.getImageBytes("found", null);

        assertNotNull(result);
        assertEquals(full, result);
    }
    @Test
    public void getPreview() throws Exception {
        byte[] result = target.getImageBytes("found", 256);

        assertNotNull(result);
        assertEquals(preview, result);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundException() throws Exception {
        when(imagesResources.getFullImage("found")).thenReturn(new byte[]{1});

        target.getImageBytes("not-found", 111);
    }

}
