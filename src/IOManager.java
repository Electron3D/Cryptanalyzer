import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IOManager {
    public List<String> read(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(List<String> text, Path path, String operation) {
        String pathStr = path.toString();
        String extension = pathStr.substring(pathStr.lastIndexOf("."));
        String destination =  pathStr.substring(0, pathStr.lastIndexOf(".")) + "_" + operation + "d" + extension;
        try {
            Files.write(Path.of(destination), text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
