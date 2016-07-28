package unstudio.sinocraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by AAA on 2016/2/9.
 */
public class BlockCCSlab extends BlockSlab{

    private Block block;
    private IIcon icon;

    public BlockCCSlab(boolean p_i45410_1_, Material p_i45410_2_) {
        super(p_i45410_1_, p_i45410_2_);
        this.useNeighborBrightness = !p_i45410_1_;
    }

    @Override
    public String func_150002_b(int p_150002_1_){
        return getUnlocalizedName();
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
        return block==null?Item.getItemFromBlock(this):Item.getItemFromBlock(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_){
        return block==null?Item.getItemFromBlock(this):Item.getItemFromBlock(block);
    }

    public Block getBlockSlab() {
        return block;
    }

    public BlockCCSlab setBlockSlab(Block block) {
        this.block = block;
        return this;
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {

        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(getTextureName());
    }

    public BlockCCSlab setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }
}
