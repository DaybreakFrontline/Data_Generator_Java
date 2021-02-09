package com.test.dataFlush.peopleBase.utils;

public class PhoneGenerator {

    public static void main(String[] args) {

        haoma(); //遍历生成随机号码
    }

    public static String haoma() {
        StringBuilder sb = new StringBuilder(); // 使用StringBuilder来连接字符串

        sb.append(haoduans[(int) (Math.random() * 52)]); // ①添加号段
        for (int i = 0; i < 8; i++) { //②随机生成后8位
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    public static String[] haoduans = { "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "150",
            "151", "152", "153", "154", "155", "156", "157", "158", "159", "180", "181", "182", "183", "184", "185",
            "186", "187", "188", "189", "141", "145", "146", "147", "148", "149", "162", "165", "166", "167", "170",
            "171", "172", "173", "174", "175", "176", "177", "178", "191", "198", "199" };

}
