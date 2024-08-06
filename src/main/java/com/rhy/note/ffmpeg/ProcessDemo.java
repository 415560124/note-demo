package com.rhy.note.ffmpeg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessDemo {
    private static Process process = null;
    public static void main(String[] args) {
        int flag = 0;
        // ffmpeg位置，最好写在配置文件中
        String ffmpegPath = "D:\\ffmpeg\\bin\\";
        try {
            // 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
            if(process != null){
                process.destroy();
                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
            }
            // cmd命令拼接，注意命令中存在空格
            String command = ffmpegPath+"ffmpeg -hwaccel cuda -re -i D:\\ffmpeg\\bin\\DJI_20220805110354_0003_W.MP4 -bf 0 -vcodec h264 -acodec aac -strict -2 -f flv rtmp://12.30.4.137:10086/live/javaffmpeg2"; // ffmpeg位置

            // 运行cmd命令，获取其进程
            process = Runtime.getRuntime().exec(command);
            // 输出ffmpeg推流日志
            BufferedReader br= new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("视频推流信息[" + line + "]");
            }
            flag = process.waitFor();
            System.out.println(flag);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
