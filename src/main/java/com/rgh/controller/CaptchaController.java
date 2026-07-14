package com.rgh.controller;

import com.rgh.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码接口
 */
@RestController
public class CaptchaController {

    private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_LEN = 4;

    @GetMapping("/captcha")
    public ResultUtil captcha() {
        try {
            SecureRandom random = new SecureRandom();

            // 生成随机验证码
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < CODE_LEN; i++) {
                code.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            String verifyCode = code.toString();

            // 生成图片
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setFont(new Font("Arial", Font.BOLD, 24));

            for (int i = 0; i < CODE_LEN; i++) {
                g.setColor(new Color(random.nextInt(128), random.nextInt(128), random.nextInt(128)));
                g.drawString(String.valueOf(verifyCode.charAt(i)), 20 + i * 22, 28 + random.nextInt(8) - 4);
            }

            // 干扰线
            for (int i = 0; i < 6; i++) {
                g.setColor(new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200)));
                g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT));
            }
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());

            Map<String, String> result = new HashMap<>();
            result.put("identifyCode", base64);
            result.put("verifyKey", verifyCode);

            return ResultUtil.success("获取成功", result);
        } catch (Exception e) {
            return ResultUtil.fail(500, "验证码生成失败");
        }
    }
}
