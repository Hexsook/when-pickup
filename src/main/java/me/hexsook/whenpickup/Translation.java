package me.hexsook.whenpickup;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Translation {

    public static String inventoryTitle(Player player) {
        return LocaleTranslation.getLocaleTranslation(player).inventoryTitle();
    }

    public static String inventoryFrameItem(Player player) {
        return LocaleTranslation.getLocaleTranslation(player).inventoryFrameItem();
    }

    public static String inventoryFramePlayers(Player player) {
        return LocaleTranslation.getLocaleTranslation(player).inventoryFramePlayers();
    }

    public static String inventoryNoNearbyPlayers(Player player) {
        return LocaleTranslation.getLocaleTranslation(player).inventoryNoNearbyPlayers();
    }

    public static String actionbar(Player player) {
        return LocaleTranslation.getLocaleTranslation(player).actionbar();
    }

    private abstract static class LocaleTranslation {

        public static final LocaleTranslation defaultTranslation;
        public static final Map<String, LocaleTranslation> registeredTranslations = new HashMap<>();

        static {
            defaultTranslation = new en_us();

            registeredTranslations.put("en_us", defaultTranslation);
            registeredTranslations.put("zh_cn", new zh_cn());
            registeredTranslations.put("en_gb", new en_gb());
            registeredTranslations.put("de_de", new de_de());
            registeredTranslations.put("ja_jp", new ja_jp());
            registeredTranslations.put("it_it", new it_it());
            registeredTranslations.put("ko_kr", new ko_kr());
            registeredTranslations.put("la_la", new la_la());
            registeredTranslations.put("ru_ru", new ru_ru());
            registeredTranslations.put("zh_hk", new zh_hk());
            registeredTranslations.put("zh_tw", new zh_tw());
            registeredTranslations.put("es_es", new es_es());
        }

        public abstract String inventoryTitle();
        public abstract String inventoryFrameItem();
        public abstract String inventoryFramePlayers();
        public abstract String inventoryNoNearbyPlayers();
        public abstract String actionbar();

        public static LocaleTranslation getLocaleTranslation(Player player) {
            String locale = player.getLocale();

            if (!registeredTranslations.containsKey(locale)) {
                return defaultTranslation;
            }
            return registeredTranslations.get(locale);
        }
    }

    private static class en_us extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Nearby Items";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Items";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Other Players in the View";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cThere are no players in the view!";
        }

        @Override
        public String actionbar() {
            return "§ePress §f{button} §eto open nearby items.";
        }
    }

    private static class zh_cn extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "附近的物品";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7物品";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7其他在视图中的玩家";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c当前没有玩家在视图中！";
        }

        @Override
        public String actionbar() {
            return "§e按下 §f{button} §e以打开附近的物品";
        }
    }

    private static class en_gb extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Nearby Items";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Items";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Other Players in the View";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cThere are no players in the view!";
        }

        @Override
        public String actionbar() {
            return "§ePress §f{button} §eto open nearby items.";
        }
    }

    private static class de_de extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Nahegelegene Gegenstände";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Gegenstände";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Andere Spieler in der Nähe";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cEs sind keine Spieler in der Nähe!";
        }

        @Override
        public String actionbar() {
            return "§eDrücke §f{button} §eum nahegelegene Gegenstände zu öffnen.";
        }
    }

    private static class ja_jp extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "近くのアイテム";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7アイテム";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7視界内の他のプレイヤー";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c視界内にプレイヤーはいません！";
        }

        @Override
        public String actionbar() {
            return "§e§f{button}§eを押して、近くのアイテムを開いてください。";
        }
    }

    private static class it_it extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Oggetti Vicini";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Oggetti";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Altri giocatori nella vista";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cNon ci sono giocatori nella vista!";
        }

        @Override
        public String actionbar() {
            return "§ePremi §f{button} §eper aprire gli oggetti vicini.";
        }
    }

    private static class ko_kr extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "주변 아이템";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7아이템";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7주변 플레이어";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c주변에 플레이어가 없습니다!";
        }

        @Override
        public String actionbar() {
            return "§e§f{button}§e를 눌러 주변 아이템을 열어주세요.";
        }
    }

    private static class la_la extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Res Item";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Items";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Alii lusores in conspectu";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cNulli lusores sunt in conspectu!";
        }

        @Override
        public String actionbar() {
            return "§ePreme §f{button} §eut proximos res aperias.";
        }
    }

    private static class ru_ru extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Близлежащие предметы";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Предметы";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Другие игроки в поле зрения";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§cНет игроков в поле зрения!";
        }

        @Override
        public String actionbar() {
            return "§eНажмите §f{button} §eчтобы открыть близлежащие предметы.";
        }
    }

    private static class zh_hk extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "附近物品";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7物品";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7視野內的其他玩家";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c視野內沒有玩家！";
        }

        @Override
        public String actionbar() {
            return "§e按下 §f{button} §e以打開附近物品";
        }
    }

    private static class zh_tw extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "附近物品";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7物品";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7其他玩家";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c沒有附近的玩家！";
        }

        @Override
        public String actionbar() {
            return "§e按下 §f{button} §e以開啟附近物品";
        }
    }

    private static class es_es extends LocaleTranslation {

        @Override
        public String inventoryTitle() {
            return "Artículos Cercanos";
        }

        @Override
        public String inventoryFrameItem() {
            return "§8↑ §7Artículos";
        }

        @Override
        public String inventoryFramePlayers() {
            return "§8↓ §7Otros jugadores en la vista";
        }

        @Override
        public String inventoryNoNearbyPlayers() {
            return "§c¡No hay jugadores en la vista!";
        }

        @Override
        public String actionbar() {
            return "§ePresiona §f{button} §epara abrir los artículos cercanos.";
        }
    }
}
