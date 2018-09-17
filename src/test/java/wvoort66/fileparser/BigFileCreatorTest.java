package wvoort66.fileparser;

import org.junit.Test;

public class BigFileCreatorTest {

    @Test
    public void createBigFileTest() {
        String folderPath = "src/test/resources";
        BigFileCreator bfc = new BigFileCreator();
        bfc.createBigFile(150L,folderPath);
    }
}