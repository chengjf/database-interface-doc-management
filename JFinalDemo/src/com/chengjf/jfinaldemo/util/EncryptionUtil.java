package com.chengjf.jfinaldemo.util;

import com.jfinal.kit.HashKit;

/**
 * Created by liuyang on 15/4/21.
 */
public class EncryptionUtil extends HashKit {

    /**
     * 鐮佽〃;
     */
    public static char[] encodeTable = {'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * Base64鐨勭紪鐮�
     *
     * @param value
     * @return
     */
    public static String encoderBase64(byte[] value) {
        StringBuilder sb = new StringBuilder();
        //鑾峰彇缂栫爜瀛楄妭鏄�勫�嶆暟;
        int len = value.length;
        int len3 = len / 3;
        //鍏堝鐞嗘病鏈夊姞鎹㈣绗�
        for (int i = 0; i < len3; i++) {

            //寰楀埌绗竴涓瓧绗�
            int b1 = (value[i * 3] >> 2) & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            //寰楀埌绗簩涓瓧绗�
            int b2 = ((value[i * 3] << 4 & 0x3F) + (value[i * 3 + 1] >> 4)) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);

            //寰楀埌绗笁涓瓧绗�
            int b3 = ((value[i * 3 + 1] << 2 & 0x3C) + (value[i * 3 + 2] >> 6)) & 0x3F;
            char c3 = encodeTable[b3];
            sb.append(c3);

            //寰楀埌绗洓涓瓧绗�
            int b4 = value[i * 3 + 2] & 0x3F;
            char c4 = encodeTable[b4];
            sb.append(c4);

        }

        //濡傛灉鏈夊墿浣欑殑瀛楃灏辫ˉ0;
        //鍓╀綑鐨勪釜鏁�
        int less = len % 3;
        if (less == 1) {//鍓╀綑涓�涓瓧绗�--琛ュ厖涓や釜绛夊彿;;

            //寰楀埌绗竴涓瓧绗�
            int b1 = value[len3 * 3] >> 2 & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            //寰楀埌绗簩涓瓧绗�
            int b2 = (value[len3 * 3] << 4 & 0x30) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);
            sb.append("==");

        } else if (less == 2) {//鍓╀綑涓や釜瀛楃--琛ュ厖涓�涓瓑鍙�

            //寰楀埌绗竴涓瓧绗�
            int b1 = value[len3 * 3] >> 2 & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            //寰楀埌绗簩涓瓧绗�
            int b2 = ((value[len3 * 3] << 4 & 0x30) + (value[len3 * 3 + 1] >> 4)) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);

            //寰楀埌绗笁涓瓧绗�
            int b3 = (value[len3 * 3 + 1] << 2 & 0x3C) & 0x3F;
            char c3 = encodeTable[b3];
            sb.append(c3);
            sb.append("=");

        }

        return sb.toString();
    }

    /**
     * Base64鐨勮В鐮�
     *
     * @param value
     * @return
     */
    public static String decoderBase64(byte[] value) {

        //姣忓洓涓竴缁勮繘琛岃В鐮�
        int len = value.length;
        int len4 = len / 4;
        StringBuilder sb = new StringBuilder();
        //闄ゅ幓鏈熬鐨勫洓涓彲鑳界壒娈婄殑瀛楃;
        int i = 0;
        for (i = 0; i < len4 - 1; i++) {

            //绗竴涓瓧绗�
            byte b1 = (byte) ((char2Index((char) value[i * 4]) << 2) + (char2Index((char) value[i * 4 + 1]) >> 4));
            sb.append((char) b1);
            //绗簩涓瓧绗�
            byte b2 = (byte) ((char2Index((char) value[i * 4 + 1]) << 4)
                    + (char2Index((char) value[i * 4 + 2]) >> 2));
            sb.append((char) b2);
            //绗笁涓瓧绗�
            byte b3 = (byte) ((char2Index((char) value[i * 4 + 2]) << 6) + (char2Index((char) value[i * 4 + 3])));
            sb.append((char) b3);

        }

        //澶勭悊鏈�鍚庣殑鍥涗釜瀛楃涓�
        for (int j = 0; j < 3; j++) {
            int index = i * 4 + j;
            if ((char) value[index + 1] != '=') {

                if (j == 0) {
                    byte b = (byte) ((char2Index((char) value[index]) << 2)
                            + (char2Index((char) value[index + 1]) >> 4));
                    sb.append((char) b);
                } else if (j == 1) {
                    byte b = (byte) ((char2Index((char) value[index]) << 4)
                            + (char2Index((char) value[index + 1]) >> 2));
                    sb.append((char) b);
                } else if (j == 2) {
                    byte b = (byte) ((char2Index((char) value[index]) << 6)
                            + (char2Index((char) value[index + 1])));
                    sb.append((char) b);
                }

            } else {
                break;
            }
        }

        return sb.toString();
    }

    /**
     * 灏嗙爜琛ㄤ腑鐨勫瓧绗︽槧灏勫埌绱㈠紩鍊�
     *
     * @param ch
     * @return
     */
    public static int char2Index(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A';
        } else if (ch >= 'a' && ch <= 'z') {
            return 26 + ch - 'a';
        } else if (ch >= '0' && ch <= '9') {
            return 52 + ch - '0';
        } else if (ch == '+') {
            return 62;
        } else if (ch == '/') {
            return 63;
        }
        return 0;
    }
}
