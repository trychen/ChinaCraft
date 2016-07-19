package unstudio.chinacraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TileSericultureFrame;

public class ItemDebug extends Item {

    public ItemDebug() {
        setCreativeTab(ChinaCraft.tabCore);
        setUnlocalizedName("debug");
        setMaxStackSize(1);
    }

    @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return false;
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        int metadata = block.getMetaFromState(state);
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.firstline")));
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.blockinfo") + ": "
                + (StatCollector.canTranslate(block.getUnlocalizedName() + ".name")
                        ? StatCollector.translateToLocal(block.getUnlocalizedName() + ".name")
                        : StatCollector
                                .translateToLocal(block.getUnlocalizedName() + ".default.name"))
                + " " + Block.getIdFromBlock(block) + " "
                + block.getUnlocalizedName().replace("tile.", "")));
        playerIn.addChatMessage(new ChatComponentText(
                StatCollector.translateToLocal("debug.position") + ": " + pos));
        playerIn.addChatMessage(new ChatComponentText(
                StatCollector.translateToLocal("debug.metadata") + ": " + metadata));
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.material")+"&"+StatCollector.translateToLocal("debug.rendertype")+": "+getMaterialName(block.getMaterial())+"  "+block.getRenderType()));
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.hardness") + "&"+StatCollector.translateToLocal("debug.antiknock")+": "+block.getBlockHardness(worldIn,pos)+"  "+block.getExplosionResistance(null)*5F/3F));
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.brightness") + "&"+StatCollector.translateToLocal("debug.transmittance")+": "+block.getLightValue()+"  "+block.getLightOpacity()));
        playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.need_tools") + "&"+StatCollector.translateToLocal("debug.need_tools_level")+": "+block.getHarvestTool(state)+"  "+block.getHarvestLevel(state)));
        // player.addChatMessage(new
        // ChatComponentText(String.valueOf(player.inventory.currentItem)));
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            playerIn.addChatMessage(new ChatComponentText("TileEntity:" + tile.getClass().getSimpleName()));
        }
        if (tile instanceof TileBuhrimill) {

        } else if (tile instanceof TileSericultureFrame) {
            playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.deathrate") + ": "
                    + ((TileSericultureFrame) tile).getMortality()));
            // player.addChatMessage(new
            // ChatComponentText(StatCollector.translateToLocal("debug.progress")+":
            // "+((TileSericultureFrame)tile).getSchedule()));
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(StatCollector.translateToLocal("item.debug.lore"));
    }

    private String getMaterialName(Material material){
        if(material==null){
            return null;
        }else if(material.equals(Material.air)){
            return "air";
        }else if(material.equals(Material.anvil)){
            return "anvil";
        }else if(material.equals(Material.cactus)){
            return "cactus";
        }else if(material.equals(Material.cake)){
            return "cake";
        }else if(material.equals(Material.carpet)){
            return "carpet";
        }else if(material.equals(Material.circuits)){
            return "circuits";
        }else if(material.equals(Material.clay)){
            return "clay";
        }else if(material.equals(Material.cloth)){
            return "cloth";
        }else if(material.equals(Material.coral)){
            return "coral";
        }else if(material.equals(Material.craftedSnow)){
            return "craftedSnow";
        }else if(material.equals(Material.dragonEgg)){
            return "dragonEgg";
        }else if(material.equals(Material.fire)){
            return "fire";
        }else if(material.equals(Material.glass)) {
            return "glass";
        }else if(material.equals(Material.gourd)){
            return "gourd";
        }else if(material.equals(Material.grass)){
            return "grass";
        }else if(material.equals(Material.ground)){
            return "ground";
        }else if(material.equals(Material.ice)){
            return "ice";
        }else if(material.equals(Material.iron)){
            return "iron";
        }else if(material.equals(Material.lava)){
            return "lava";
        }else if(material.equals(Material.leaves)){
            return "leaves";
        }else if(material.equals(Material.packedIce)){
            return "packedIce";
        }else if(material.equals(Material.piston)){
            return "piston";
        }else if(material.equals(Material.plants)){
            return "plants";
        }else if(material.equals(Material.portal)){
            return "portal";
        }else if(material.equals(Material.redstoneLight)){
            return "redstoneLight";
        }else if(material.equals(Material.rock)){
            return "rock";
        }else if(material.equals(Material.sand)){
            return "sand";
        }else if(material.equals(Material.snow)){
            return "snow";
        }else if(material.equals(Material.sponge)){
            return "sponge";
        }else if(material.equals(Material.tnt)){
            return "tnt";
        }else if(material.equals(Material.vine)){
            return "vine";
        }else if(material.equals(Material.water)){
            return "water";
        }else if(material.equals(Material.web)){
            return "web";
        }else if(material.equals(Material.wood)){
            return "wood";
        }else{
            return "unknown";
        }
    }
}
