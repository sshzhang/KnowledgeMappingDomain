package com.knowledge.Utils.CommonUtilsPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 写入错误日志到文件中
 */
public class LogsUtils {


    public static boolean WriteTheDataToFile(String messgae, String filepath){

        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filepath);
            if (!file.exists()) {//文件不存在
                file.createNewFile();
            }
             fileOutputStream = new FileOutputStream(new File(filepath),true);
            byte[] bytes = messgae.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes());
        } catch (Exception ex) {

            return false;
        }finally {
            try {
                if(fileOutputStream!=null)
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }





}
