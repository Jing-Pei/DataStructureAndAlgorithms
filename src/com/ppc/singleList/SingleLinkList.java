package com.ppc.singleList;

/**
 * @author ppc
 * @create 2019-05-22-13:50
 */
public class SingleLinkList {
    private SingleNode singleNode;

    public SingleLinkList(){
        this(null,"头结点");
    }

    public SingleLinkList(SingleNode next,Object data){
        this.singleNode = new SingleNode(data,next);
    }

    public SingleNode getItem(int index){
        SingleNode result = null;
        for (int i = 0 ; i < index ; i++){
            result = singleNode.getNext();
        }
        if(result == null){
            throw new RuntimeException("第"+index+"元素不存在");
        }
        return result;
    }

    public static SingleLinkList init(){
        SingleLinkList linkList = new SingleLinkList();
        SingleNode 甲 = new SingleNode("甲", null);
        SingleNode 乙 = new SingleNode("乙", null);
        SingleNode 丙 = new SingleNode("丙", null);
        SingleNode 丁 = new SingleNode("丁", null);
        SingleNode 戊 = new SingleNode("戊", null);
        linkList.singleNode.setNext(甲);
        linkList.singleNode.setNext(乙);
        linkList.singleNode.setNext(丙);
        linkList.singleNode.setNext(丁);
        linkList.singleNode.setNext(戊);
        return linkList;
    }
}

/**
 * 单链表节点
 */
class SingleNode{
    private Object data;
    private SingleNode next;

    public Object getData() {
        return data;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next){
        this.next = next;
    }

    public SingleNode(){}

    public SingleNode(Object data, SingleNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}