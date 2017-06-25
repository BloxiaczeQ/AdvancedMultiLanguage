package me.smessie.MultiLanguage.bungeecord.commands;

import java.sql.SQLException;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bungeecord.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bungeecord.Implement;
import me.smessie.MultiLanguage.bungeecord.Main;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Langue extends Command {

    public Langue() {
        super("langue");
    }

    ChatColor red = ChatColor.RED;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {

            final ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 1) {

                String taal = args[0];

                if (Languages.isSupportedLanguage(taal.toUpperCase())) {
                    final String formatTaal = taal.toUpperCase();
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Vous avez mit votre langue en " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Attention! Tu ne dois pas parler " + taal + " dans le chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Cette langue est désactivée! :("));
                    }
                } else if (Languages.isSupportedLanguageFull(taal.toLowerCase())) {
                    final String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Vous avez mit votre langue en " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Attention! Tu ne dois pas parler " + taal + " dans le chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Cette langue est désactivée! :("));
                    }
                } else if (Languages.isSupportedLanguageOwn(taal.toLowerCase())) {
                    final String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Vous avez mit votre langue en " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Attention! Tu ne dois pas parler " + taal + " dans le chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Cette langue est désactivée! :("));
                    }
                } else {
                    player.sendMessage(new TextComponent(red + "La langue " + args[0] + " n'éxiste pas !"));
                }
            } else {
                player.sendMessage(new TextComponent(red + "Utilisation: /langue <langue>"));
            }
        } else {
            sender.sendMessage(new TextComponent(red + "Hé, Seuls les joueurs en jeu peuvent définir la langue ! :o"));
        }
    }

}
