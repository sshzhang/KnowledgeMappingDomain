package com.knowledge.Utils.Neo4jUtilsPackage;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import java.util.concurrent.Callable;

/**
 * Neo4j工具类
 */
public  abstract  class Neo4jUtils  extends Thread implements Callable<String>{


    protected final Driver driver;
    protected static final String uri = "bolt://localhost:7687";
    protected  static final String user="neo4j";
    protected  static final String password = "09120912";

    public Neo4jUtils() {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    protected void close() throws Exception {
        if (this.driver != null)
            this.driver.close();
    }

}
