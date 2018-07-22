package com.knowledge.Utils.DomainUtilsPackage;

import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConnectionPoolFactory {

    private static final Map<String, Driver> connPools = new HashMap<>();
    protected static final String uri = "bolt://localhost:7687";
    protected  static final String user="neo4j";
    protected static final String password = "09120912";

    public static synchronized  Driver getDriverInfo(String key) {

        Driver driver = null;
        if (!connPools.containsKey(key)) {
            driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

            driver.isEncrypted();

            connPools.put(key, driver);
        }else{
            driver = connPools.get(key);
        }
        LogsUtils.WriteTheDataToFile(connPools.size() + "", "/home/xiujiezhang/IdeaProjects/KnowledgeMappingDomain/src/resources/count.txt");

        return driver;
    }


    public static void close() {
        if (connPools == null || connPools.size() == 0) {
            return;
        }
        Set<String> strings =
                connPools.keySet();
        for (String str : strings) {
            connPools.get(str).closeAsync();
        }
        connPools.clear();
    }
}
