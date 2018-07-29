package test;

import java.util.ArrayList;

/**
 * Created by woody on 2018/7/8.
 */
public class ArrayTest {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList();
        al.add("a");
        al.add("b");

        for(String s:al){
            System.out.println(s);
        }

    }
}
