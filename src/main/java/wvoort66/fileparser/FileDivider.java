package wvoort66.fileparser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class FileDivider {

    private int chunks;

    private Path filePath;

    private long fileSize;

    public FileDivider(final Path filePath, final int chunks) {
        this.chunks = chunks;
        this.filePath = filePath;
    }


    public List<FileSection> getFileSections() throws IOException {
        fileSize = Files.size(filePath);
        long chunckSize = fileSize / chunks;

        List<FileSection> fileSections = new LinkedList<>();

        long offset = 0;
        for (int ndx = 0; ndx < chunks; ndx++) {
            FileSection fileSection = createFileSection(offset, chunckSize);
            offset = fileSection.getLastBitOffset() ;
            fileSections.add(fileSection);
        }

        return fileSections;

    }

    private FileSection createFileSection(long offset, long chunckSize) throws IOException {
        if (offset + chunckSize > fileSize) {
            long bytesToRead = fileSize - offset;
            return new FileSection(filePath, offset, bytesToRead);
        }


        return new FileSection(filePath, offset , chunckSize + getNrOfBytesToEOL(filePath, offset + chunckSize));

    }


    private int getNrOfBytesToEOL(final Path filePath, final long position) throws IOException {
        try (FileChannel fc = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            int nrOfBytesRead = 0;
            fc.position(position);
            while (fc.read(byteBuffer) != -1) {
                byteBuffer.flip();
                nrOfBytesRead++;
                byte b = byteBuffer.get();
                if(b == 10) {
                    return nrOfBytesRead;
                }
                byteBuffer.clear();
            }

        }

        return 0;
    }

}
