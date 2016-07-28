package unstudio.sinocraft.block.especial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import unstudio.sinocraft.client.gui.GuiID;
import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.tileentity.TileBuhrimill;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBuhrimill extends BlockContainer {

    private IIcon icon;

    public BlockBuhrimill() {
        super(Material.rock);
        setBlockName("buhrimill");
        setHardness(3.0F);
        setResistance(10.0F);
        setCreativeTab(SinoCraft.tabCore);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 1);
    }

    // @Override
    // public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
    // int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
    // List p_149743_6_, Entity p_149743_7_) {
    // this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    // super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
    // p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    // this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 0.875F, 0.75F);
    // super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
    // p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    // this.setBlockBoundsForItemRender();
    // }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
            EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (l == 1) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 3) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(SinoCraft.buhrimill);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.getItemFromBlock(SinoCraft.buhrimill);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileBuhrimill();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_,
            float p_149727_7_, float p_149727_8_, float p_149727_9_) {

        if (p_149727_5_.isSneaking()) {
            if (world.isRemote)
                return true;
            p_149727_5_.openGui(SinoCraft.instance, GuiID.GUI_Buhrimill, world, x, y, z);
        } else {
            if (world.getTileEntity(x, y, z) instanceof TileBuhrimill) {
                ((TileBuhrimill) world.getTileEntity(x, y, z)).addAngle(10);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World World, int x, int y, int z, Block Block, int var1) {

        TileBuhrimill tileentity = (TileBuhrimill) World.getTileEntity(x, y, z);
        Random random = World.rand;
        if (tileentity != null) {
            for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileentity.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = random.nextFloat() * 0.8F + 0.1F;
                    float f1 = random.nextFloat() * 0.8F + 0.1F;
                    float f2 = random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j1 = random.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(World, x + f, y + f1, z + f2,
                                new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem()
                                    .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (float) random.nextGaussian() * f3;
                        entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) random.nextGaussian() * f3;
                        World.spawnEntityInWorld(entityitem);
                    }
                }
            }

            World.func_147453_f(x, y, z, Block);
        }

        super.breakBlock(World, x, y, z, Block, var1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon("Minecraft:stone");
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }
}
