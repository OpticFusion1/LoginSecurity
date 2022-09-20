package optic_fusion1.loginsecurity.captcha;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import static com.lenis0012.bukkit.loginsecurity.LoginSecurity.translate;
import static com.lenis0012.bukkit.loginsecurity.modules.language.LanguageKeys.REGISTER_SUCCESS;
import com.lenis0012.bukkit.loginsecurity.session.AuthAction;
import com.lenis0012.bukkit.loginsecurity.session.AuthService;
import com.lenis0012.bukkit.loginsecurity.session.PlayerSession;
import com.lenis0012.bukkit.loginsecurity.session.action.RegisterAction;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class Captcha implements Listener {

    private LoginSecurity loginSecurity;
    private UUID uniqueId;
    private PlayerSession session;
    private ItemStack mainHandItem;
    private Generator generator;
    private String password;

    public Captcha(LoginSecurity loginSecurity, UUID uniqueId, String password, PlayerSession session) {
        this.loginSecurity = loginSecurity;
        this.uniqueId = uniqueId;
        this.password = password;
        this.session = session;
        generator = new Generator();
        mainHandItem = Bukkit.getPlayer(uniqueId).getInventory().getItemInMainHand();
        Bukkit.getPluginManager().registerEvents(this, loginSecurity);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerQuitEvent event) {
        Bukkit.getPlayer(uniqueId).getInventory().setItemInMainHand(mainHandItem);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId() != uniqueId) {
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType() != Material.FILLED_MAP) {
            return;
        }
        event.setCancelled(true);
        if (!event.getMessage().trim().equals(generator.captcha)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(loginSecurity, () -> {
                player.kickPlayer("Wrong Captcha! Please try again.");
            });
            return;
        }
        player.getInventory().setItemInMainHand(mainHandItem);
        AuthAction action = new RegisterAction(AuthService.PLAYER, player, password);
        session.performAction(action);
        player.sendMessage(translate(REGISTER_SUCCESS).toString());
    }

    public class Generator {

        String captcha;

        public void generateCaptcha(Player player) {
            captcha = randomCaptcha(5); // Sets the captcha key

            // Generates MapView
            MapView view = Bukkit.createMap(player.getWorld());
            view.getRenderers().clear();
            view.addRenderer(new CaptchaRenderer(captcha));
            view.setCenterX(player.getLocation().getBlockX());
            view.setCenterZ(player.getLocation().getBlockZ());
            view.setScale(MapView.Scale.FARTHEST);

            // Creates ItemStack and gives it to the player
            ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
            MapMeta itemMeta = (MapMeta) itemStack.getItemMeta();
            itemMeta.setMapView(view);
            itemMeta.setDisplayName("Captcha [Enter In Chat]");
            itemStack.setItemMeta(itemMeta);

            player.getInventory().setItemInMainHand(itemStack);
            player.sendMap(view);
        }

        public String randomCaptcha(int length) {
            final String sheet = "ABCDEFHIJKLMNOPQRSTUVWXYZ123456789";
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(sheet.charAt(ThreadLocalRandom.current().nextInt(sheet.length())));
            }
            return builder.toString();
        }

    }

    public Generator getGenerator() {
        return generator;
    }

}
