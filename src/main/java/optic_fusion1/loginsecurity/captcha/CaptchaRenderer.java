package optic_fusion1.loginsecurity.captcha;

import java.awt.Color;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CaptchaRenderer extends MapRenderer {

    private String captcha;
    
    public CaptchaRenderer(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public void render(MapView view, MapCanvas canvas, Player player) {
        // Clear map
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                canvas.setPixelColor(x, y, Color.BLACK);
            }
        }
        // Draw captcha
        int y = 128 / 2 - CaptchaFont.getInstance().getHeight() / 2;
        int x = 128 / 2 - CaptchaFont.getInstance().getWidth(captcha) / 2;
        canvas.drawText(x, y, CaptchaFont.getInstance(), captcha);
    }

}
