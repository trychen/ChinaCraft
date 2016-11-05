package unstudio.chinacraft.block.model;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileModelBlock;

import java.util.Random;

public class BlockCCLamp extends BlockCCModel {
    private boolean light;

    /**
     * @param material 材质
     * @param model    模型
     * @param name     名字
     */
    public BlockCCLamp(Material material, Class<? extends ModelBase> model, String name, boolean light) {
        super(material, model, name, null);
        if (light) this.setLightLevel(1.0F);
        this.light = light;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileModelBlock(this.getModel(), this.name);
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.wool.getIcon(0,14);
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (light && !(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemBlock)) {
            w.setBlock(x, y, z, ChinaCraft.lanternScaldfishOff);
            return true;
        } else if (!light&&player.capabilities.isCreativeMode || player.getHeldItem() != null){
            if (player.getHeldItem().getItem() == ItemBlock.getItemFromBlock(Blocks.torch)){
                w.setBlock(x, y, z, ChinaCraft.lanternScaldfish);
            } else if (player.getHeldItem().getItem() == Items.flint){
                player.getHeldItem().damageItem(1,player);
                w.setBlock(x, y, z, ChinaCraft.lanternScaldfish);
            }
            return true;
        }
        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(ChinaCraft.lanternScaldfishOff);
    }

    @Override
    protected ItemStack createStackedBlock(int p_149644_1_) {
        return new ItemStack(light ? ChinaCraft.lanternScaldfish : ChinaCraft.lanternScaldfishOff);
    }
}
