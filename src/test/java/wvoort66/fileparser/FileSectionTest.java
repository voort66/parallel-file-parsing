package wvoort66.fileparser;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FileSectionTest {
    @Test
    public void testGetLastBitOffset() {
        Path path = Paths.get("out/test/resources/bigfile_150.csv");
        long offset = 10;
        long bytesToRead = 100;
        FileSection fileSection = new FileSection(path, offset, bytesToRead);
        assertEquals(110, fileSection.getLastBitOffset());
    }
}