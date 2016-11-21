package unstudio.chinacraft.client.waila;

import java.util.List;

import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unstudio.chinacraft.block.especial.BlockBuhrimill;

public class WailaCompatibility implements IWailaDataProvider {

    public static final WailaCompatibility INSTANCE = new WailaCompatibility();
        
    public static void init() {
        FMLInterModComms.sendMessage("Waila", "register", "unstudio.chinacraft.client.waila.WailaCompatibility.registerWailaCompat");
    }
    
    public static void registerWailaCompat(IWailaRegistrar registrar) {
        registrar.registerBodyProvider(INSTANCE, BlockBuhrimill.class);
    }
    
    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int posX,
            int posY, int posZ) {
        return tag;
    }
    
    // 不使用此方法
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler configHandler) {
        return null;
    }
    
    // 不使用此方法
    @Override
    public List<String> getWailaHead(ItemStack stack, List<String> tipList, IWailaDataAccessor accessor,
            IWailaConfigHandler configHandler) {
        return tipList;
    }
    
    // 在Waila窗体中添加自定义信息
    @Override
    public List<String> getWailaBody(ItemStack stack, List<String> tipList, IWailaDataAccessor accessor,
            IWailaConfigHandler configHandler) {
        Block block = accessor.getBlock();
        if (block instanceof CCWailaInfoProvider) {
            return ((CCWailaInfoProvider) block).addWailaBodyInfo(stack, tipList, accessor, configHandler);
        }
        return tipList;
    }
    
    // 不使用此方法
    @Override
    public List<String> getWailaTail(ItemStack stack, List<String> tipList, IWailaDataAccessor accessor,
            IWailaConfigHandler configHandler) {
        return tipList;
    }
    
    /**
     * 凡是需要在Waila窗体中添加信息的方块都需实现此接口
     */
    public interface CCWailaInfoProvider {
        List<String> addWailaBodyInfo(ItemStack stack, List<String> tipList, IWailaDataAccessor accessor, IWailaConfigHandler configHandler);
    }
    
}
