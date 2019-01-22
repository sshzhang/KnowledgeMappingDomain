package com.knowledge.Utils.CommonUtilsPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 写入错误日志到文件中
 */
public class LogsUtils {


    public synchronized static boolean WriteTheDataToFile(String messgae, String filepath, boolean isappend) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File("/home/zxj/scriptfile/KnowledgeMappingDomain/"+filepath);
            if (!file.exists()) {//文件不存在
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file, isappend);
            byte[] bytes = (messgae).getBytes();
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


    public synchronized static boolean WriteTheDataToFile(String messgae, String filepath) {
        return  WriteTheDataToFile(messgae, filepath, true);
    }

    public  static Map<String, Integer> ReadTheDataToFile(String filepath, String strpat) {



       Map<String,Integer> ids= new HashMap<>();
        BufferedReader bufferedReader=null;
        try {
            File file = new File("/home/zxj/scriptfile/KnowledgeMappingDomain/"+filepath);
            System.out.println(file.exists());
            if (!file.exists()) {//文件不存在
                return ids;
            }

            bufferedReader = new BufferedReader(new FileReader(file));

            String line = bufferedReader.readLine();
            System.out.println(line);

            Pattern compile = Pattern.compile(strpat);

            Matcher matcher =
                    compile.matcher(line);

            if (matcher.find()) {

                int x = Integer.parseInt(matcher.group(1));

                int y = Integer.parseInt(matcher.group(2));

                ids.put("i", x);
                ids.put("j", y);
            }

        } catch (Exception ex) {
            System.out.println("error "+ex.getMessage());
            return ids;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ids;


    }


    public static void main(String... args) throws IOException {

//        WriteTheDataToFile("i=11:j=22", "lastIndex.txt",false);
        ReadTheDataToFile("lastIndex.txt","i=(\\d+):j=(\\d+)");

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

