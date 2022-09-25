package io.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

    public static void main(String[] args) {
        Path absolute = Paths.get("/home", "ling");
        Path relative = Paths.get("myprog", "confg", "user.properties");


        System.out.println();
    }
}
