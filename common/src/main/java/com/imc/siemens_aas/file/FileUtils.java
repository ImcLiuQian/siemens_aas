package com.imc.siemens_aas.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String fileToString(MultipartFile file) {
        BufferedReader reader;
        String res = "";
        try {
            InputStream fileInputStream = file.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString ;
            while ((tempString = reader.readLine()) != null){
                res += tempString;
            }
            reader.close();
        }catch (IOException e){
            throw new RuntimeException("文件转换出错");
        }
        return res;
    }
}
