package com.lenis0012.bukkit.loginsecurity.commands;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.bukkit.loginsecurity.session.AuthAction;
import com.lenis0012.bukkit.loginsecurity.session.AuthService;
import com.lenis0012.bukkit.loginsecurity.session.PlayerSession;
import com.lenis0012.bukkit.loginsecurity.session.action.ActionResponse;
import com.lenis0012.bukkit.loginsecurity.session.action.LogoutAction;
import com.lenis0012.pluginutils.modules.command.Command;
import org.bukkit.entity.Player;

import static com.lenis0012.bukkit.loginsecurity.LoginSecurity.translate;
import com.lenis0012.bukkit.loginsecurity.LoginSecurityConfig;
import static com.lenis0012.bukkit.loginsecurity.modules.language.LanguageKeys.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandLogout extends Command {

    private final LoginSecurity plugin;

    public CommandLogout(LoginSecurity plugin) {
        this.plugin = plugin;
        setAllowConsole(false);
    }

    @Override
    public void execute() {
        PlayerSession session = plugin.getSessionManager().getPlayerSession(player);

        // Verify auth mode
        if (!session.isLoggedIn()) {
            reply(false, translate(GENERAL_NOT_LOGGED_IN));
            return;
        }

        final Player player = this.player;
        AuthAction action = new LogoutAction(AuthService.PLAYER, player);
        session.performActionAsync(action, response -> {
            if (!response.isSuccess()) {
                reply(player, false, translate(LOGOUT_FAIL).param("error", response.getErrorMessage()));
                return;
            }
            reply(player, true, translate(LOGOUT_SUCCESS));
            LoginSecurityConfig config = plugin.getConfiguration();
            if (config.isBlindness()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1));
            }
        });
    }
}
