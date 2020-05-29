package com.app;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyProcessor {

//    public String readConfig() {
//        try {
//            URL url = getClass().getResource("/data.txt");
//            URI uri = url.toURI();
//            Path path = Paths.get(uri);
//            byte[] data = Files.readAllBytes(path);
//            return new String(data);
//        }catch (Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }

    public String readConfig() {
        try {
            byte[] data = Files.readAllBytes(Paths.get(getClass().getResource("/data.txt").toURI()));
            return new String(data);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String readConfigA(){
        try(InputStream in = getClass().getResourceAsStream("/a/dataA.txt")){
            byte[] data = new byte[in.available()];
            in.read(data);
            return new String(data);
            //String str = new String(data);
            //return str;
        }catch (Exception ex){
            throw  new RuntimeException(ex);
        }
    }

}
