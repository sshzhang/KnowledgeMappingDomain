package com.knowledge.Utils.CommonUtilsPackage;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;
public class MongoDBConnectionUtils {
    private static MongoClient localServiceClient ;
    private static MongoClient remoteServideClient;
    private static MongoClient remoteConnection(String host,int port) {
        try {
            ServerAddress serverAddress = new ServerAddress(host, port);
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            serverAddresses.add(serverAddress);
            //三个参数 用户名 数据库名称 密码
            MongoClient mongoClient = new MongoClient(serverAddresses);
            return mongoClient;
        } catch (Exception e) {
            System.out.println(e.getStackTrace() + ":" + e.getMessage());
            return null;
        }
    }

    public static MongoClient getLocalServiceClient() {
            localServiceClient = remoteConnection("192.168.199.202",27017);
        return localServiceClient;
    }

    public static MongoClient getRemoteServiceClient() {
            remoteServideClient = remoteConnection("10.1.17.15",27017);
        return remoteServideClient;
    }
}
