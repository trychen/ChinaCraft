package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileCooker extends TileEntity{
	
	protected boolean isCooking=false;
	protected CookerType cookerType = CookerType.Unknown;
	protected int time=0;
	
	protected ItemStack food[];
	
	/**
	 * 输入物品
	 * @param item 物品
	 * @param entityPlayer 玩家
	 * @return
	 */
	public boolean input(ItemStack item,EntityPlayer entityPlayer) {
		return false;
	}
	
	/**
	 * 输出物品
	 * @param item 物品
	 * @param entityPlayer 玩家
	 * @return
	 */
	public boolean output(ItemStack item,EntityPlayer entityPlayer) {
		return false;
	}
	
	/**
	 * 获取烹饪状态
	 * @return
	 */
	public boolean isCooking() {
		return isCooking;
	}
	
	/**
	 * 返回Cooker类型
	 * @return
	 */
	public CookerType getCookerType() {
		return cookerType;
	}
	
	/**
	 * 返回剩余时间，单位tick
	 * @return
	 */
	public int remainingTime() {
		return time;
	}
	
	public enum CookerType{
		/**
		 * 煮锅
		 */
		Pot,
		/**
		 * 蒸器
		 */
		Steamer,
		/**
		 * 未知
		 */
		Unknown
	}
}
