package com.cheng;

/**
 * Created by admin on 2018/2/27.
 */
public class test {

    public static void main(String[] args){

        String aa = "111";
        Integer bb = 2;
    }


    public static byte[] getShiftStr(byte[] c, Integer s) {
        byte[] key = procKey(s);
        byte[] result = new byte[c.length];
        int j = 0;
        for (int i = 0; i <= c.length - 1; i++) {
            result[i] = (byte) (c[i] ^ key[j]);
            j++;
            if (j >= key.length) {
                j = 0;
            }
        }
        return result;
    }

    private static byte[] procKey(Integer s) {
        byte[] key = ("Digimon" + Integer.toString(s) + "Evolution").getBytes();
        return key;
    }
}
