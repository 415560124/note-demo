package com.rhy.note.work;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author Rhy
 * @title 获取本机Mac地址
 * @description 获取本机Mac地址
 * @createTime 2020年12月23日 11:23:00
 * @modifier：Rhy
 * @modification_time：2020-12-23 11:23
 */
public class MacTool {
    //获取本机的Mac地址
    public String GetMac()
    {
        InetAddress ia;
        byte[] mac = null;
        try {
            //获取本地IP对象
            ia = InetAddress.getLocalHost();
            //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
            mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<mac.length;i++){
            if(i!=0){
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length()==1?0+s:s);
        }

        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }
    public static void main(String[] args)
    {
        MacTool t=new MacTool();
        String mac=t.GetMac();
        System.out.println(mac);
    }
}
