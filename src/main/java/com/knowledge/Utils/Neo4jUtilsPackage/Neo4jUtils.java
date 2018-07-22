package com.knowledge.Utils.Neo4jUtilsPackage;

import java.util.concurrent.Callable;

/**
 * Neo4j工具类
 */
public  abstract  class Neo4jUtils<T,F>   implements Callable<Boolean>{



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
    }



    /**
     * 评论数据集写入到图数据库中
     *
     * @param CommentT 具体的评论信息
     */
    protected abstract boolean CreateApplicationCommentDataToNeo4jNode(T CommentT) throws InterruptedException;


    /**
     * 把静态数据写入到图数据库中
     * @param allStaticContent 具体应用的静态数据
     */
    protected abstract boolean CreateApplicationStaticContentDataToNeo4jNode(F allStaticContent);

    @Override
    public Boolean call() throws InterruptedException {
        return this.status == 0 ? this.CreateApplicationCommentDataToNeo4jNode(this.CommentT) : this.CreateApplicationStaticContentDataToNeo4jNode(this.allStaticContent);
    }
}
