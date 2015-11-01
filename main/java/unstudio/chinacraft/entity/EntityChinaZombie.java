package unstudio.chinacraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChinaZombie extends EntityZombie
{
    public EntityChinaZombie(World var1)
    {
        super(var1);
//        this.setSize(0.9f, 1.9f);
    }

    protected void dropFewItems(boolean par1, int par2){
        int random = this.rand.nextInt(15) + this.rand.nextInt(1 + par2);

        for(int k = 0; k<random-1; k++){
            if(k==8){
                this.dropItem(Items.gunpowder, 1);
            }else if(k==1){
                this.dropItem(Items.rotten_flesh, 1);
            }else if(k==14){
                this.dropItem(Item.getItemFromBlock(Blocks.tnt), 1);
            }
        }
    }
    public EntityMob createChild (EntityAgeable p_90011_1_)
    {
    	EntityChinaZombie entityzombie = new EntityChinaZombie(this.worldObj);
    	String s = this.func_146067_o(arrowHitTimer);
    	
    	if (s != null && s.trim().length() > 0){
    		entityzombie.func_142017_o(getAge());
    		entityzombie.canPickUpLoot();
    	}
    	
    	return entityzombie;
    }
    public void onUpdate()
    {
        if (targetTasks != null) new EntityJumpHelper(this).doJump();
        if (isJumping == true) {
            motionY *= 1.6;
            motionX *= 1.002D;
            motionZ *= 1.002D;
        }
        super.onUpdate();
    }
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.28000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.7D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }
    public static ResourceLocation getResourceLocation(){
        return new ResourceLocation("chinacraft", "textures/entity/blackwolf/blackwolf.png");
    }
}