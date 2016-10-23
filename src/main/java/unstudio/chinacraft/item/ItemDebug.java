package unstudio.chinacraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
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
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return false;
        player.addChatMessage(new ChatComponentText("========== Debug =========="));
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.firstline")));
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.blockinfo") + ": "
                + (StatCollector.canTranslate(world.getBlock(x, y, z).getUnlocalizedName() + ".name")
                        ? StatCollector.translateToLocal(world.getBlock(x, y, z).getUnlocalizedName() + ".name")
                        : StatCollector
                                .translateToLocal(world.getBlock(x, y, z).getUnlocalizedName() + ".default.name"))
                + " " + Block.getIdFromBlock(world.getBlock(x, y, z)) + " "
                + world.getBlock(x, y, z).getUnlocalizedName().replace("tile.", "")));
        player.addChatMessage(new ChatComponentText(
                StatCollector.translateToLocal("debug.position") + ": " + x + "/" + y + "/" + z + " (X/Y/Z)"));
        player.addChatMessage(new ChatComponentText(
                StatCollector.translateToLocal("debug.metadata") + ": " + world.getBlockMetadata(x, y, z)));
        Block block = world.getBlock(x,y,z);
        int metadata = world.getBlockMetadata(x, y, z);
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.material")+"&"+StatCollector.translateToLocal("debug.rendertype")+": "+getMaterialName(block.getMaterial())+"  "+block.getRenderType()));
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.hardness") + "&"+StatCollector.translateToLocal("debug.antiknock")+": "+block.getBlockHardness(world,x,y,z)+"  "+block.getExplosionResistance(null)*5F/3F));
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.brightness") + "&"+StatCollector.translateToLocal("debug.transmittance")+": "+block.getLightValue()+"  "+block.getLightOpacity()));
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.need_tools") + "&"+StatCollector.translateToLocal("debug.need_tools_level")+": "+block.getHarvestTool(metadata)+"  "+block.getHarvestLevel(metadata)));
        // player.addChatMessage(new
        // ChatComponentText(String.valueOf(player.inventory.currentItem)));
        TileEntity tile = world.getTileEntity(x, y, z);
        if (world.getTileEntity(x, y, z) != null) {
            player.addChatMessage(new ChatComponentText("TileEntity:" + tile.getClass().getSimpleName()));
        }
        if (tile instanceof TileBuhrimill) {

        } else if (tile instanceof TileSericultureFrame) {
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("debug.deathrate") + ": "
                    + ((TileSericultureFrame) tile).getMortality()));
            // player.addChatMessage(new
            // ChatComponentText(StatCollector.translateToLocal("debug.progress")+":
            // "+((TileSericultureFrame)tile).getSchedule()));
        }
        player.addChatMessage(new ChatComponentText("==========================="));
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
