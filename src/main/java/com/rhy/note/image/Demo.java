package com.rhy.note.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Demo {

    public static void test() throws IOException {
        int width = 1800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.cyan);
        g2d.fillRect(0, 0, width, height);

        //打开抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
//        g2d.drawString("Hello,World!", 50,50);
//        g2d.setColor(Color.CYAN);
//        g2d.drawString("aaaaaa!", 80,80);
        // 设置颜色，绘制色块
        g2d.setColor(new Color(132, 167, 206));
        g2d.fillRect(85, 85, 30, 30);

        // 设置画笔颜色，例如黑色
        g2d.setColor(Color.BLACK);
        // 默认情况下线条就是实线，但也可以显式设置线条宽度
        g2d.setStroke(new BasicStroke(1)); // 设置线条宽度为3
        // 绘制一条从(50, 50)到(200, 200)的实线
        g2d.drawLine(50, 50, 200, 200);

        // 设置画笔颜色
        g2d.setColor(Color.BLUE);
        // 创建虚线样式，参数分别表示 dash（实线长度）、gap（间隔长度）、... 可以按需添加更多组
        float[] dashPattern = {5.0f, 3.0f}; // 代表实线5像素，空隙3像素
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 20.0f, dashPattern, 0));
        // 绘制一条从(50, 150)到(200, 250)的虚线
        g2d.drawLine(50, 150, 200, 250);

        // 设置图片
        g2d.drawImage(
                ImageIO.read(new File("C:\\Users\\DELL\\Downloads\\容器 26_slices\\mipmap-hdpi\\容器 26.png")),
                90, 90,
                null
        );
        g2d.dispose();
        try {
            ImageIO.write(image, "png", new File("E:\\data\\output.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
