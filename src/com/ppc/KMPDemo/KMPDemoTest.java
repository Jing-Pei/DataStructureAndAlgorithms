package com.ppc.KMPDemo;

import java.util.Arrays;

/**
 * 改下注释，测试下
 * @author Administrator
 * @create 2019-06-17 18:30
 */
public class KMPDemoTest {
    public static void main(String[] args){
        String a = "aaaaax";//011123
        int[] result = new int[a.length()];
        getNext(a,result);
        System.out.println(Arrays.toString(result));
        getNextval(a,result);
        System.out.println(Arrays.toString(result));
        int s =indexStr("aaaabcde","aaaaax",0);
        System.out.println(s);
        int s2 =indexStr2("aaaabcde","aaaaax",0);
        System.out.println(s2);
    }

    /**
     * KMP算法------------核心思想：计算子串数组，减少嵌套循环
     * @param t
     * @param next
     */
    public static void getNext(String t, int[] next){
        int i = 0,j = -1;
        next[0] = - 1;
        while (i < t.length() -1 ){
            if (j == -1 || t.charAt(i) == t.charAt(j)){
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

    /**
     * 改良版KMP算法
     * @param t
     * @param nextval
     */
    public static void getNextval(String t, int[] nextval){
        int i = 0,j = -1;
        nextval[0] = - 1;
        while (i < t.length() -1 ){
            if (j == -1 || t.charAt(i) == t.charAt(j)){
                ++i;
                ++j;
                //判断当前字符和原数组位置上字符是否相等，如果相等j回溯到之前位置，避免重复判断
                if(t.charAt(i) != t.charAt(j)){
                    nextval[i] = j;
                }else {
                    nextval[i] = nextval[j];
                }
            } else {
                j = nextval[j];
            }
        }
    }

    public static int indexStr(String source,String indexString,int pos){
        int i = pos;
        int j = 0;
        int[] next = new int[indexString.length()];
        getNext(indexString,next);
        int count =0;
        while ((i < source.length() ) && (j < indexString.length() )){
            if (j == -1 || source.charAt(i) == indexString.charAt(j)){
                ++i;
                ++j;
            } else {
                j = next[j];
            }
            if (j > indexString.length() - 1){
                return i - j;
            }
            count ++;
        }
        System.out.println("indexStr ----------" + count);
        return -1;
    }

    /**
     * 测试用方法，循环次数打印
     * @param source
     * @param indexString
     * @param pos
     * @return
     */
    public static int indexStr2(String source,String indexString,int pos){
        int i = pos;
        int j = 0;
        int[] next = new int[indexString.length()];
        getNextval(indexString,next);
        int count =0;
        while ((i < source.length() ) && (j < indexString.length() )){
            if (j == -1 || source.charAt(i) == indexString.charAt(j)){
                ++i;
                ++j;
            } else {
                j = next[j];
            }
            if (j > indexString.length() - 1){
                return i - j;
            }
            count ++;
        }
        System.out.println("indexStr2 -------------" + count);
        return -1;
    }
}
