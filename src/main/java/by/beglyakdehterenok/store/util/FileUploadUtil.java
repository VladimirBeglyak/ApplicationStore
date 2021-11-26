package by.beglyakdehterenok.store.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        String basePath = "/users/BEGLYAK/work" + uploadDir;
        Path imageFullPath = Path.of(basePath, fileName);

        if (!Files.exists(imageFullPath)) {
            Files.createDirectories(imageFullPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, imageFullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
