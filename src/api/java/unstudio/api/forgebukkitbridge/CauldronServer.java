package unstudio.api.forgebukkitbridge;

import org.bukkit.Bukkit;

public class CauldronServer {

	public CauldronServer(){
		
	}
	
	/**
	 * 获取服务器版本
	 * @return
	 */
	public String getCauldronVersion(){
		return Bukkit.getBukkitVersion();
	}
	
	/**
	 * 获得插件启动情况
	 * @param plugin 插件名
	 * @return
	 */
	public boolean isPluginEnabled(String plugin){
		return Bukkit.getPluginManager().isPluginEnabled(plugin);
	}
}
