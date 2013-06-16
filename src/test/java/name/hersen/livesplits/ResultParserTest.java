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

    @Before
    public void setUp() throws Exception {
        target = new ResultParser();
        imagesResources = mock(ImagesResources.class);
        setField(target, "imagesResources", imagesResources);
    }

    @Test
    public void getImages() throws Exception {
        when(imagesResources.getFileNames()).thenReturn(asList("single"));

        List<String> result = target.getImages();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void getImageBytes() throws Exception {
        when(imagesResources.getImageBytes("found")).thenReturn(new byte[]{1});

        byte[] result = target.getImageBytes("found");

        assertNotNull(result);
        assertEquals(1, result.length);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundException() throws Exception {
        when(imagesResources.getImageBytes("found")).thenReturn(new byte[]{1});

        target.getImageBytes("not-found");
    }

}
