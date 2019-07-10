package com.ppc.shunxu;

import java.util.Arrays;

/**
 * @author ppc
 * @create 2019-05-21-15:26
 */
public class ElemType {
    private String[] data;
    private final int MAX_SIZE;
    private int length;

    public ElemType(){
        this(100);
    }

    public ElemType(int length){
        MAX_SIZE = length;
        data = new String[length];
    }

    public int length(){
        return length;
    }

    public String get(int index){
        if(length() == 0 || index < 0 || index > length()){
            return "error";
        }
        return data[index];
    }

    public String remove(int index){
        if (length() == 0){
            //线性表已满
            throw new RuntimeException("线性表为空!");
        }
        if (index < 0 || index > MAX_SIZE){
            throw new RuntimeException("index不在范围内!");
        }
        if (index <= length() - 1){
            for ( int i = length() - 1 ;i > index -1 ; i --){
                data[i - 1] = data [i];
            }
            //可有可无，但是有的话可以释放
            data [length() - 1] = null;
        }
        length --;
        return data[index];
    }

    /**
     *
     * @param index 数据角标位置
     * @param item 数据data
     */
    public void add(int index, String item){
        if (length() == MAX_SIZE){
            //线性表已满
            throw new RuntimeException("线性表已满!");
        }
        if (index < 0 || index > MAX_SIZE){
            throw new RuntimeException("index = " +index+ "不在范围内!");
        }
        if (index <= length() - 1){
            for ( int i = length() - 1 ;i > index -1 ; i --){
                data[i + 1] = data [i];
            }
        }
        data[index] = item;
        length ++;
        System.out.println(length());
    }

    public void add (String item){
        add(length(),item);
    }

    @Override
    public String toString() {
        return "ElemType{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length() +
                '}';
    }
}
