package com.knowledge.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testUtils  {



    public static void main(String... args) {


       /* String str = "[\"口味8.2\", \"环境9.0\", \"服务8.3\"]";
        str = str.substring(1, str.length() - 1);
        String[] splits = str.split(",");
        Pattern compile = Pattern.compile("(?<=\")(?<key>.*?)(?<value>[0-9]+[\\.][0-9]*)");
        for (String sp : splits) {
            Matcher matcher =
                    compile.matcher(sp);
            matcher.find();
            System.out.println(matcher.group("key")+"  "+matcher.group("value"));
        }*/


        String str = "[\"口味：很好\", \"环境：很好\", \"服务：好\"]";
        Pattern compile = Pattern.compile("(\"(?<name>.+?)：(?<value>.+?)\")+?");
        Matcher matcher = compile.matcher(str);
        while (matcher.find())
            System.out.println(matcher.group("name") + " " + matcher.group("value"));
        System.out.println(ArrayList.class.isAssignableFrom(List.class));

        System.out.println(List.class.isAssignableFrom(ArrayList.class));


    }
}
