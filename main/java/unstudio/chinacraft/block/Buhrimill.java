package unstudio.chinacraft.block;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.GuiID;
import unstudio.chinacraft.renderer.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Buhrimill extends BlockContainer {

	IIcon top, down;

	public Buhrimill() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("buhrimill"));
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 1);
	}

	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
			int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
			List p_149743_6_, Entity p_149743_7_) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
				p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 0.875F, 0.75F);
		super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
				p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		this.setBlockBoundsForItemRender();
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }
    }


	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int par2) {
		if (i == 0)
			return down;
		else if (i >= 1 && i <= 6)
			return top;
		else
			return top;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon("chinacraft:empty");
		this.down = reg.registerIcon("minecraft:stone");
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return ChinaCraft.itemBuhrimill;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_,
			int p_149694_4_) {
		return ChinaCraft.itemBuhrimill;
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileBuhrimill();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	if(p_149727_5_.isSneaking()) {
            ItemStack stack = p_149727_5_.inventory.mainInventory[p_149727_5_.inventory.currentItem];
            p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_Buhrimill, world, x, y, z);
            return true;
        	}else {
        		TileBuhrimill tile = (TileBuhrimill)world.getTileEntity(x, y, z);
        		tile.addAngle(10);
                return true;
        	}
        }
    }
}
