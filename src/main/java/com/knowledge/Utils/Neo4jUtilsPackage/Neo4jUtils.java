package com.knowledge.Utils.Neo4jUtilsPackage;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import java.util.concurrent.Callable;

/**
 * Neo4j工具类
 */
public  abstract  class Neo4jUtils<T,F>   implements Callable<Boolean>{


    protected final Driver driver;
    protected static final String uri = "bolt://192.168.199.202:7687";
    protected  static final String user="neo4j";
    protected  static final String password = "09120912";
    // 0 表示评论数据的迭代   1表示 静态数据的迭代
    protected int status;
    //评论数据对象
    protected T CommentT;
    //静态数据对象
    protected F allStaticContent;

    private Neo4jUtils(T CommenT) {
        this();
        this.CommentT = CommenT;
    }

    public  Neo4jUtils(T CommenT,F allStaticContent,int status) {
        this(CommenT);
        this.allStaticContent = allStaticContent;
        this.status = status;
    }


    private  Neo4jUtils() {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    protected void close() throws Exception {
        if (this.driver != null)
            this.driver.close();
    }

    /**
     * 评论数据集写入到图数据库中
     *
     * @param CommentT 具体的评论信息
     */
    protected abstract boolean CreateApplicationCommentDataToNeo4jNode(T CommentT);


    /**
     * 把静态数据写入到图数据库中
     * @param allStaticContent 具体应用的静态数据
     */
    protected abstract boolean CreateApplicationStaticContentDataToNeo4jNode(F allStaticContent);

    @Override
    public Boolean call() {
        return this.status == 0 ? this.CreateApplicationCommentDataToNeo4jNode(this.CommentT) : this.CreateApplicationStaticContentDataToNeo4jNode(this.allStaticContent);
    }
}
