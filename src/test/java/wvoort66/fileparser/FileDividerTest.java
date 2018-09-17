package wvoort66.fileparser;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileDividerTest {

    private Path filePath;

    @Before
    public void setUp() {
        filePath = Paths.get("out/test/resources/bigfile_150000.csv");

    }

    @Test
    public void testGetFileSections() throws IOException {
        FileDivider fileDivider = new FileDivider(filePath, 3);
        List<FileSection> sections = fileDivider.getFileSections();
        assertNotNull(sections);
        assertEquals(3, sections.size());

    }
}