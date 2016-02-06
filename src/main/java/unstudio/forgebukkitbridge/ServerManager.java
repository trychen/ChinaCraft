package unstudio.forgebukkitbridge;

public class ServerManager {
    private static boolean isCauldronServer = loadCauldronServer();

    /**
     * @return 返回true表示包存在，Bukkit类存在
     */
    private static boolean loadCauldronServer() {
        try {
            Class.forName("org.bukkit.Bukkit");
            return true;
        } catch (ClassNotFoundException error) {
            return false;
        }
    }

    /**
     * 获得服务器是不是Cauldron服务器
     * 
     * @return
     */
    public static boolean isCauldronServer() {
        return isCauldronServer;
    }

    /**
     * 获得Cauldron服务器
     * 
     * @return
     */
    public static CauldronServer getCaulrdonServer() {
        if (isCauldronServer) {
            return new CauldronServer();
        } else {
            return null;
        }
    }

    /**
     * 判断服务器是否存在Vault插件
     * 
     * @return
     */
    public static boolean hasVaultPlugin() {
        if (isCauldronServer) {
            return getCaulrdonServer().isPluginEnabled("Vault");
        }
        return false;
    }

    /**
     * 获得Vault插件(服务器必须是Cauldron服务器)
     * 
     * @return
     */
    public static VaultPlugin getVaultPlugin() {
        if (isCauldronServer && getCaulrdonServer().isPluginEnabled("Vault")) {
            return new VaultPlugin();
        } else {
            return null;
        }
    }
}
