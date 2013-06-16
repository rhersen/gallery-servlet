package name.hersen.livesplits;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
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
        when(imagesResources.getImages()).thenReturn(asList("single"));

        List<String> result = target.getImages();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void getImageBytes() throws Exception {
        when(imagesResources.getImageBytes(anyString())).thenReturn(new byte[]{1});

        byte[] result = target.getImageBytes("single");

        assertNotNull(result);
        assertEquals(1, result.length);
    }

}
