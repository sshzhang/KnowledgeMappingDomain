package com.knowledge.Utils.practicePreProcess;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.*;

public class preProcessTheData {


    public static void main(String... args) throws                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              IOException {
        PreProcess();
    }

    private static void PreProcess() {
        //保存所有的用户id 从而得到对应的索引
        ArrayList<String> allUsers = new ArrayList<>();
        ArrayList<String> allCommoditys = new ArrayList<>();
        System.out.println("开始统计用户和商品个数");
        //获取所有的用户　和商品列表集合
        GainUserAndCommoditySize(allUsers, allCommoditys);
        System.out.println("统计用户和商品个数结束");
        System.out.println(allCommoditys.size()+" "+allUsers.size());
        HashMap<Integer, List<Integer>> umatrixLines = new HashMap<>();
        HashMap<Integer, List<Integer>> imatrixLines = new HashMap<>();
        ArrayList<UserCommodityPair> removesDatas = new ArrayList<>();
        System.out.println("开始计算链接边的矩阵");
        GainMatrixLinksAndLinksNum(allUsers, allCommoditys, umatrixLines,imatrixLines);
        System.out.println("计算链接边的矩阵结束");
        boolean flage = true;
        System.out.println("开始收集需要删除的边");
        while (flage) {
            System.out.println("开始循环");
            flage = false;
            //查找并删除相应元素
            for (Integer useId : umatrixLines.keySet()) {

                List<Integer> uValues = umatrixLines.get(useId);
                int ulen = uValues.size();
                if (ulen > 0 && ulen < 5) {
                    for (int i = 0; i < ulen; i++) {

                        //添加到移除列表
                        removesDatas.add(new UserCommodityPair(allUsers.get(useId), allCommoditys.get(uValues.get(i))));

                        //商品列表中删除
                        List<Integer> iValues = imatrixLines.get(uValues.get(i));
                        iValues.remove(useId);
                    }
                    //用户已经移除
                    umatrixLines.put(useId, new ArrayList<>());
                }
            }

            //删除相应的元素
            for (Integer iId : imatrixLines.keySet()) {

                List<Integer> iValues = imatrixLines.get(iId);
                int ilen = iValues.size();
                if (ilen > 0 && ilen < 5) {
                    flage = true;
                    for (int j = 0; j < ilen; j++) {

                        removesDatas.add(new UserCommodityPair(allUsers.get(iValues.get(j)), allCommoditys.get(iId)));

                        List<Integer> uValues = umatrixLines.get(iValues.get(j));
                        uValues.remove(iId);
                    }
                    imatrixLines.put(iId, new ArrayList<>());
                }
            }

        }

        System.out.println("收集需要删除的边结束");

        System.out.println("开始写入数据到新的文件");
        RemoveTheAbundanDataAndWriteToNewFile(removesDatas);
        System.out.println("写入数据到新的文件结束");

        System.out.println("sucess!!!");
    }


    private static void RemoveTheAbundanDataAndWriteToNewFile(List<UserCommodityPair> removesDatas) {

        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {

            inputStreamReader = new InputStreamReader(new FileInputStream(new File("/home/zxj/IdeaProjects/KnowledgeMappingDomain/src/main/resources/reviews_Office_Products.json")));
            bufferedReader = new BufferedReader(inputStreamReader);

            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File("/home/zxj/IdeaProjects/KnowledgeMappingDomain/src/main/resources/new_reviews_Office_Products.json")));
            bufferedWriter = new BufferedWriter(outputStreamWriter);


            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                UserCommodityPair userCommodityPair = JSON.parseObject(line, UserCommodityPair.class);
                if (!removesDatas.contains(userCommodityPair)) {
                    bufferedWriter.write(line + "\n");
                }

            }

        } catch (IOException ex) {
            throw new RuntimeException("Runtime Exception " + ex.getMessage());

        } finally {

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }


    private static void GainMatrixLinksAndLinksNum(List<String> allUsers, List<String> allCommoditys, Map<Integer,List<Integer>> umatrixLines,Map<Integer,List<Integer>> imatrixLines) {


        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {

            inputStreamReader = new InputStreamReader(new FileInputStream(new File("/home/zxj/IdeaProjects/KnowledgeMappingDomain/src/main/resources/reviews_Office_Products.json")));
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                UserCommodityPair userCommodityPair = JSON.parseObject(line, UserCommodityPair.class);
                String userId = userCommodityPair.getReviewerID();
                String commodityId = userCommodityPair.getAsin();

                int indexu = allUsers.indexOf(userId);

                int indexi = allCommoditys.indexOf(commodityId);

                List<Integer> uValues = umatrixLines.get(indexu);

                if (uValues == null) {
                    uValues = new ArrayList<Integer>();
                    uValues.add(indexi);
                    umatrixLines.put(indexu, uValues);
                }else{
                    uValues.add(indexi);
                }

                List<Integer> iValues = imatrixLines.get(indexi);

                if (iValues == null) {
                    iValues = new ArrayList<Integer>();
                    iValues.add(indexu);
                    imatrixLines.put(indexi, iValues);
                }else{
                    iValues.add(indexu);
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Runtime Exception " + ex.getMessage());

        } finally {

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }


    private static void GainUserAndCommoditySize(ArrayList<String> allUsers, ArrayList<String> allCommoditys) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {

            inputStreamReader = new InputStreamReader(new FileInputStream(new File("/home/zxj/IdeaProjects/KnowledgeMappingDomain/src/main/resources/reviews_Office_Products.json")));
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                UserCommodityPair userCommodityPair = JSON.parseObject(line, UserCommodityPair.class);

                String userId = userCommodityPair.getReviewerID();

                String commodityId = userCommodityPair.getAsin();

                if (allUsers.contains(userId)) {
                } else {
                    allUsers.add(userId);
                }


                if (allCommoditys.contains(commodityId)) {
                } else {
                    allCommoditys.add(commodityId);
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Runtime Exception " + ex.getMessage());

        } finally {

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
