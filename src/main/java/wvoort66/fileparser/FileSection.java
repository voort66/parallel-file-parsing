package wvoort66.fileparser;

import java.nio.file.Path;


public class FileSection {
    private Path filePath;

    private long offset;

    private long bytesToRead;

    public FileSection(final Path filePath, long offset, long bytesToRead) {
        this.filePath = filePath;
        this.offset = offset;
        this.bytesToRead = bytesToRead;
    }

    public long getOffset() {
        return offset;
    }

    public long getBytesToRead() {
        return bytesToRead;
    }

    public Path getFilePath() {
        return filePath;
    }

    public long getLastBitOffset() {
        return offset + bytesToRead;
    }


}
