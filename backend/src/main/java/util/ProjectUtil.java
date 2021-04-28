package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ProjectUtil {
    public static final ProjectUtil instance = new ProjectUtil();

    public static ProjectUtil getInstance() {
        return instance;
    }

    /**
     * 生成验证码
     *
     * @param height 验证码高度，宽度自动计算
     * @param num    验证码长度
     * @param os     输出对象
     * @return 返回验证码字符串
     * @throws IOException
     */
    public String getVerificationCode(int height, int num, OutputStream os) throws IOException {
        int fontSize = 30;
        int x = 10;  //旋转原点的 x 坐标
        int width = (fontSize + 2 * x) * num;
        BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();
        graphics.setColor(Color.WHITE);//设置画笔颜色-验证码背景色
        graphics.fillRect(0, 0, width, height);//填充背景
        graphics.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
        //数字和字母的组合
        String baseNumLetter = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

        StringBuilder sBuffer = new StringBuilder();

        String ch = "";
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            graphics.setColor(getRandomColor());
            //设置字体旋转角度
            int degree = random.nextInt() % 30;  //角度小于30度
            int dot = random.nextInt(baseNumLetter.length());
            ch = baseNumLetter.charAt(dot) + "";
            sBuffer.append(ch);
            //正向旋转
            graphics.rotate(degree * Math.PI / 180, x, 45);
            graphics.drawString(ch, x, 45);
            //反向旋转
            graphics.rotate(-degree * Math.PI / 180, x, 45);
            x += 48;
        }
        //画干扰线
        for (int i = 0; i < 10; i++) {
            // 设置随机颜色
            graphics.setColor(getRandomColor());
            // 随机画线
            graphics.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
        //添加噪点
        for (int i = 0; i < 200; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.setColor(getRandomColor());
            graphics.fillRect(x1, y1, 2, 2);
        }
        ImageIO.write(verifyImg, "png", os);
        os.flush();
        os.close();
        return sBuffer.toString();
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
        return color;
    }
}
