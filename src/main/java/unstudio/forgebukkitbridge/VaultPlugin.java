package unstudio.forgebukkitbridge;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPlugin {
    /** 使用前需要先判断是否为null ，防止因为没有被依赖插件 **/
    /** Vault权限系统 */
    public Permission permission = null;

    /** Vault权限系统 */
    public Economy economy = null;

    /** Vault聊天系统 */
    public Chat chat = null;

    public VaultPlugin() {
        setupChat();
        setupEconomy();
        setupPermissions();
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    /**
     * 查询账户余额
     * 
     * @param name
     *            玩家名
     * @return 金钱数
     */
    public double bankBalance(String name) {
        if (economy != null)
            return economy.bankBalance(name).balance;
        else
            return 0;
    }

    /**
     * 给指定账户存入金钱,不要使用负数
     * 
     * @param name
     * @param amount
     * @return 是否成功
     */
    public boolean bankDeposit(String name, double amount) {
        if (economy != null)
            return economy.bankDeposit(name, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 判断该账户是否拥有足够数量的金钱，不要使用负数
     * 
     * @param name
     * @param amount
     * @return
     */
    public boolean bankHas(String name, double amount) {
        if (economy != null)
            return economy.bankHas(name, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 从该账户扣除一定数量的钱，不要使用负数
     * 
     * @param name
     * @param amount
     * @return 是否成功
     */
    public boolean bankWithdraw(String name, double amount) {
        if (economy != null)
            return economy.bankWithdraw(name, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 创建一个属于指定玩家的账户
     * 
     * @param name
     * @param player
     * @return
     */
    public boolean createBank(String name, OfflinePlayer player) {
        if (economy != null)
            return economy.createBank(name, player).transactionSuccess();
        else
            return false;
    }

    /**
     * 创建一个属于指定玩家的账户
     * 
     * @param name
     * @param player
     * @return
     */
    @Deprecated
    public boolean createBank(String name, String player) {
        if (economy != null)
            return economy.createBank(name, player).transactionSuccess();
        else
            return false;
    }

    /**
     * 给指定玩家存入金钱，不要使用负数
     * 
     * @param player
     * @param amount
     * @return
     */
    public boolean depositPlayer(OfflinePlayer player, double amount) {
        if (economy != null)
            return economy.depositPlayer(player, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 给指定玩家存入金钱，不要使用负数
     * 
     * @param player
     * @param amount
     * @return
     */
    @Deprecated
    public boolean depositPlayer(String player, double amount) {
        if (economy != null)
            return economy.depositPlayer(player, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 给指定玩家扣除金钱，不要使用负数
     * 
     * @param player
     * @param amount
     * @return
     */
    public boolean withdrawPlayer(OfflinePlayer player, double amount) {
        if (economy != null)
            return economy.withdrawPlayer(player, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 给指定玩家扣除金钱，不要使用负数
     * 
     * @param player
     * @param amount
     * @return
     */
    @Deprecated
    public boolean withdrawPlayer(String player, double amount) {
        if (economy != null)
            return economy.withdrawPlayer(player, amount).transactionSuccess();
        else
            return false;
    }

    /**
     * 获取指定玩家金钱
     * 
     * @param player
     * @return
     */
    public double getBalance(OfflinePlayer player) {
        if (economy != null)
            return economy.getBalance(player);
        else
            return 0.0;
    }

    /**
     * 判断指定玩家是否拥有指定金钱
     * 
     * @param player
     * @return
     */
    public boolean has(OfflinePlayer player, double amount) {
        if (economy != null)
            return economy.has(player, amount);
        else
            return false;
    }
}
