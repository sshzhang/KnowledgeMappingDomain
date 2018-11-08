package com.knowledge.Utils.CommonUtilsPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 写入错误日志到文件中
 */
public class LogsUtils {


    public synchronized static boolean WriteTheDataToFile(String messgae, String filepath) {

        FileOutputStream fileOutputStream = null;
        try {
            File file = new File("/home/zxj/scriptfile/KnowledgeMappingDomain/"+filepath);
            if (!file.exists()) {//文件不存在
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(new File(filepath), true);
            byte[] bytes = messgae.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes());
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public synchronized static  List<idStrcuts> ReadTheDataToFile(String filepath) {

        BufferedReader bufferedReader = null;
        List<idStrcuts> ids = new ArrayList<>();

        try {
            File file = new File("/home/zxj/scriptfile/KnowledgeMappingDomain/"+filepath);
            System.out.println(file.exists());
            if (!file.exists()) {//文件不存在
                return ids;
            }

            bufferedReader = new BufferedReader(new FileReader(file));

            String tempt = null;
            while ((tempt = bufferedReader.readLine()) != null && tempt != "\n") {

                String[] sm = tempt.split(" ");

                if (sm.length == 2) {
                    ids.add(new idStrcuts(sm[0], sm[1]));
                }
            }

        } catch (Exception ex) {
            System.out.println("error "+ex.getMessage());
            return ids;
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ids;
    }


}

