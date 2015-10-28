package com.vgaw.one;

/**
 * Created by Administrator on 2015/9/25.
 */
 class Boy {
    protected String name;
    String sex;

    public void hello(String raw, int i){
        if (i != 1){
            char[] rawC = raw.toCharArray();
            String next = "";
            for (int j = 0;j < raw.length();j++){
                if (j % 2 == 0){
                    next += rawC[j];
                }
            }
            hello(next, next.length());
        }else{
            System.out.println(raw);
        }
    }

    public static void main(String[] args){
        String single = "123456789";
        String raw = "";
        for (int i = 0;i < 50;i++){
            raw += single;
        }
        new Boy().hello(raw, raw.length());
    }
}
