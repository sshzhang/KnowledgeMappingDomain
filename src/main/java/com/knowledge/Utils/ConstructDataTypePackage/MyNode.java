package com.knowledge.Utils.ConstructDataTypePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyNode<T> implements Serializable {

    private MyNode parentMyNode;
    private T MyNodeEntity;
    private List<MyNode> childMyNodes;
    private boolean isend = false;
    private boolean isstart = false;

    public MyNode(T MyNodeEntity) {
        this.MyNodeEntity = MyNodeEntity;
    }

    public MyNode() {
    }

    public void addChildMyNode(MyNode childMyNode) {
        childMyNode.setParentMyNode(this);
        if (this.childMyNodes == null) {
            this.childMyNodes = new ArrayList<MyNode>();
        }
        this.childMyNodes.add(childMyNode);
    }

    public void removeChildMyNode(MyNode childMyNode) {
        if (this.childMyNodes != null) {
            this.childMyNodes.remove(childMyNode);
        }
    }

    public MyNode getParentMyNode() {
        return parentMyNode;
    }

    public void setParentMyNode(MyNode parentMyNode) {
        this.parentMyNode = parentMyNode;
    }

    public T getMyNodeEntity() {
        return MyNodeEntity;
    }

    public void setMyNodeEntity(T MyNodeEntity) {
        this.MyNodeEntity = MyNodeEntity;
    }

    public List<MyNode> getChildMyNodes() {
        return childMyNodes;
    }

    public void setChildMyNodes(List<MyNode> childMyNodes) {
        this.childMyNodes = childMyNodes;

    }

    public void setIsend(boolean isend) {
        this.isend = isend;
    }

    public void setIsstart(boolean isstart) {
        this.isstart = isstart;
    }

    public boolean isIsend() {
        return isend;
    }

    public boolean isIsstart() {
        return isstart;
    }
}

