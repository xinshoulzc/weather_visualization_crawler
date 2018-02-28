package com.adc.crawler.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;

/**
 * @author zhichengliu
 * @create -02-27-21:52
 **/

public class FileHelper {
    public static String [] getDateByFileName(String fileName) {
        String dateName = fileName.split("\\.")[0];
        return dateName.split("-");
    }
    public static void save2file(String data, String filepath){
        try {
//            System.out.println(filepath);
            File fout = new File(filepath);
//            System.out.println(filepath);
            if (!fout.exists()) fout.createNewFile();
            FileWriter fw = new FileWriter(fout);
            fw.write(data);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
