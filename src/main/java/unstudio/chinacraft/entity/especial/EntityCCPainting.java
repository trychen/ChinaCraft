package unstudio.chinacraft.entity.especial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.ArrayList;

public class EntityCCPainting extends EntityHanging {
    public EntityCCPainting.EnumArt art = EnumArt.ZhongKui;

    public EntityCCPainting(World world) {
        super(world);
    }


    public EntityCCPainting(World p_i1589_1_, int p_i1589_2_, int p_i1589_3_, int p_i1589_4_, int p_i1589_5_) {
        this(p_i1589_1_);
        this.field_146063_b = p_i1589_2_;
        this.field_146064_c = p_i1589_3_;
        this.field_146062_d = p_i1589_4_;

        ArrayList arraylist = new ArrayList();
        EnumArt[] aenumart = EnumArt.values();
        int i1 = aenumart.length;

        for (int j1 = 0; j1 < i1; ++j1) {
            EnumArt enumart = aenumart[j1];
            this.art = enumart;
            this.setDirection(p_i1589_5_);

            if (this.onValidSurface()) {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty()) {
            this.art = (EnumArt) arraylist.get(this.rand.nextInt(arraylist.size()));
        }

        if (art==null){
            art = EnumArt.ZhongKui;
        }

        this.setDirection(p_i1589_5_);
    }

    public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        p_70014_1_.setString("Motive", this.art.name());
        super.writeEntityToNBT(p_70014_1_);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        String s = p_70037_1_.getString("Motive");

        this.art = EnumArt.valueOf(s);

        if (this.art == null) {
            this.art = EnumArt.ZhongKui;
        }

        super.readEntityFromNBT(p_70037_1_);
    }


    public void onBroken(Entity p_110128_1_) {
        if (p_110128_1_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) p_110128_1_;

            if (entityplayer.capabilities.isCreativeMode) {
                return;
            }
        }

        this.entityDropItem(new ItemStack(ChinaCraft.traditionalPainting), 0.0F);
    }


    public int getWidthPixels() {
        return this.art.sizeX;
    }

    public int getHeightPixels() {
        return this.art.sizeY;
    }

    public enum EnumArt {
        ZhongKui("ZhongKui", 32, 96, 0, 0);

        /**
         * Painting Title.
         */
        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        private static final String __OBFID = "CL_00001557";

        EnumArt(String p_i1598_3_, int p_i1598_4_, int p_i1598_5_, int p_i1598_6_, int p_i1598_7_) {
            this.title = p_i1598_3_;
            this.sizeX = p_i1598_4_;
            this.sizeY = p_i1598_5_;
            this.offsetX = p_i1598_6_;
            this.offsetY = p_i1598_7_;
        }
    }
}
