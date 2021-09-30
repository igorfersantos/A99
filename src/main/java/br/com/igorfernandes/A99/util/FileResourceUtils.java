package br.com.igorfernandes.A99.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileResourceUtils {

    private static String root = "/br/com/igorfernandes/A99/";

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public static InputStream getFileFromResourcesAsStream(String fileName) {

        InputStream inputStream = FileResourceUtils.class.getResourceAsStream(root.concat(fileName));

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + root.concat(fileName));
        } else {
            return inputStream;
        }

    }

    public static URL getResource(String fileName) {

        URL resource = FileResourceUtils.class.getResource(root.concat(fileName));

        // the stream holding the file content
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + root.concat(fileName));
        } else {
            return resource;
        }

    }

    // Get all paths from a folder that inside the JAR file
    public static List<Path> getPathsFromResourceJAR(String folder)
            throws URISyntaxException, IOException {

        List<Path> result;

        // get path of the current running JAR
        String jarPath = FileResourceUtils.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        System.out.println("JAR Path :" + jarPath);

        // file walks JAR
        URI uri = URI.create("jar:file:" + jarPath);
        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            result = Files.walk(fs.getPath(folder))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        return result;

    }

    // print input stream
    public static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}