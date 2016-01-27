package unstudio.forgebukkitbridge;

public class ServerManager {
	private static boolean isCauldronServer = loadCauldronServer();
	
	private static boolean loadCauldronServer(){
		try{
			Class.forName("org.bukkit.Bukkit");
			return true;
		}catch(ClassNotFoundException error){
			return false;
		}
	}
	
	/**
	 * 获得服务器是不是Cauldron服务器
	 * @return
	 */
	public static boolean isCauldronServer(){
		return isCauldronServer;
	}
	
	/**
	 * 获得Cauldron服务器
	 * @return
	 */
	public static CauldronServer getCaulrdonServer(){
		if(isCauldronServer){
			return new CauldronServer();
		}else{
			return null;
		}
	}
	
	/**
	 * 获得Vault插件(服务器必须是Cauldron服务器)
	 * @return
	 */
	public static VaultPlugin getVaultPlugin(){
		if(isCauldronServer&&getCaulrdonServer().isPluginEnable("Vault")){
			return new VaultPlugin();
		}else{
			return null;
		}
	}
}
