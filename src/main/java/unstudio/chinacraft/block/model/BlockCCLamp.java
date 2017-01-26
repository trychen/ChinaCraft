package unstudio.chinacraft.block.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileModelBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCCLamp extends BlockCCModel {

    private boolean light;

    /**
     * @param material 材质
     * @param model    模型
     * @param name     名字
     */
    public BlockCCLamp(Material material, Class<? extends ModelBase> model, String name, boolean light) {
        super(material, model, name);
        if (light) this.setLightLevel(1.0F);
        this.light = light;

    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileModelBlock();
    }

    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        world.setBlockMetadataWithNotify(x, y, z, p_149689_6_.getItemDamage(), 3);
    }

    @Override
    public int damageDropped(int p_149692_1_) {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.wool.getIcon(0, 14);
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        int blockMetadata = w.getBlockMetadata(x,y,z);
        if ((player.capabilities.isCreativeMode && player.isSneaking()) || (player.getHeldItem() != null && player.getHeldItem().getItem() == ChinaCraft.artKnife)){
            if (blockMetadata > 0) {
                blockMetadata -= 1;
                w.setBlockMetadataWithNotify(x,y,z,blockMetadata, 0);
                if (!player.capabilities.isCreativeMode) player.getHeldItem().damageItem(1, player);
            }
        } else if (light && !(player.getHeldItem() != null && (player.getHeldItem().getItem() instanceof ItemBlock || player.getHeldItem().getItem() == Items.flint_and_steel))) {
            w.setBlock(x, y, z, ChinaCraft.lanternScaldfishOff);
            w.setBlockMetadataWithNotify(x, y, z, blockMetadata, 0);
            return true;
        } else if (!light && (player.capabilities.isCreativeMode || player.getHeldItem() != null)) {
            if (player.capabilities.isCreativeMode || player.getHeldItem().getItem() == ItemBlock.getItemFromBlock(Blocks.torch)) {
                w.setBlock(x, y, z, ChinaCraft.lanternScaldfish);
                w.setBlockMetadataWithNotify(x, y, z, blockMetadata, 0);
            } else if (player.getHeldItem().getItem() == Items.flint_and_steel) {
                player.getHeldItem().damageItem(1, player);
                player.playSound("fire.ignite", 1.0F, w.rand.nextFloat() * 0.4F + 0.8F);
                w.setBlock(x, y, z, ChinaCraft.lanternScaldfish);
                w.setBlockMetadataWithNotify(x, y, z, blockMetadata, 0);
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
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> is = new ArrayList<>();
        ItemStack itemStack = new ItemStack(ChinaCraft.lanternScaldfishOff, 1,metadata);
        is.add(itemStack);
        return is;
    }
}
