package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.item.ItemMoth;
import unstudio.chinacraft.item.ItemSilkworm;

public class TileSericultureFrame extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    private static final int[] slotsSide = new int[] { 9 };
    private static final int[] slotsBottom = new int[] { 10 };

    private ItemStack stack[] = new ItemStack[11];
    public boolean canRun;
    public boolean isRunning;
    public String statusInfo = "None";

    @Override
    public int getSizeInventory() {
        return stack.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return stack[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.stack[index] != null) {
            ItemStack itemstack;

            if (this.stack[index].stackSize <= count) {
                itemstack = this.stack[index];
                this.stack[index] = null;
                return itemstack;
            } else {
                itemstack = this.stack[index].splitStack(count);

                if (this.stack[index].stackSize == 0) {
                    this.stack[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        if (this.stack[p_70304_1_] != null) {
            ItemStack itemstack = this.stack[p_70304_1_];
            this.stack[p_70304_1_] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stack[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            boolean needUpdate = false;        
            boolean isRunning = false;
            if (checkBasicRunningCondition()) {
                for (int i = 0; i < 9; i++) {
                    ItemStack stack = getStackInSlot(i);
                    if (stack == null ||stack.getItem() != ChinaCraft.silkworm && stack.getItem() != ChinaCraft.itemMoth)
                        continue;
                    needUpdate = true;
                    if (stack.hasTagCompound()) {
                        // 对于蚕卵、蚕和蚕蛹
                        if (stack.getTagCompound().hasKey("Schedule")) {
                            NBTTagCompound nbt = stack.getTagCompound();
                            int x = nbt.getInteger("Schedule");
                            // 当蚕在当前阶段演化完，正准备进入下一阶段
                            if (x >= ItemSilkworm.lifeSpan[stack.getItemDamage()]) {
                                if (stack.getItemDamage() >= 2) {
                                    int count = FeatureConfig.enableAdvancedSericulture? stack.getTagCompound().getInteger("Productivity"): 1;
                                    if (getStackInSlot(10) == null)
                                        setInventorySlotContents(10, new ItemStack(ChinaCraft.silkwormChrysalis, count)); 
                                    else if (getStackInSlot(10).stackSize + count <= 64)
                                        getStackInSlot(10).stackSize += count;
                                    else {
                                        this.statusInfo = "gui.sericulture_frame.outputSlotStucks.info";
                                        continue;
                                    }        
                                    ItemStack moth = new ItemStack(ChinaCraft.itemMoth, 1, worldObj.rand.nextInt(2));
                                    if (FeatureConfig.enableAdvancedSericulture) {
                                        NBTTagCompound newNBT = new NBTTagCompound();
                                        newNBT.setInteger("Generation", stack.getTagCompound().getInteger("Generation"));
                                        newNBT.setInteger("Productivity", stack.getTagCompound().getInteger("Productivity"));
                                        newNBT.setInteger("Speed", stack.getTagCompound().getInteger("Speed"));
                                        newNBT.setInteger("Fertility", stack.getTagCompound().getInteger("Fertility"));
                                        moth.setTagCompound(newNBT);
                                    }
                                    setInventorySlotContents(i, moth);              
                                } else {
                                    stack.setItemDamage(stack.getItemDamage() + 1);
                                    nbt.setInteger("Schedule", 0);
                                }
                            } else {
                                // 不然的话，如果在蚕的阶段没有桑叶，则工作终止
                                if (stack.getItemDamage() == 1) {
                                    if (getStackInSlot(9) == null || getStackInSlot(9).getItem() != ChinaCraft.itemMulberryLeaf) {
                                        statusInfo = "gui.sericulture_frame.noMulberryLeaf.info";
                                        continue;
                                    }
                                    // 蚕有几率死亡
                                    if (FeatureConfig.enableAdvancedSericulture && worldObj.rand.nextInt(getMortalityDenominator()) < 1 * getBaseMortalityFactor() * getGenerationMortalityFactor(stack.getTagCompound().getInteger("Generation")))
                                        setInventorySlotContents(i, new ItemStack(ChinaCraft.itemSilkwormDead));
                                    // 蚕有几率吃掉桑叶
                                    if (worldObj.rand.nextInt(getMulberryLeafEatenDenominator()) < 1) 
                                        decrStackSize(9, 1);
                                }
                                x += FeatureConfig.enableAdvancedSericulture? nbt.getInteger("Speed"): 1;
                                nbt.setInteger("Schedule", x);
                            }
                            stack.setTagCompound(nbt);
                            isRunning = true;
                        }
                        else {
                            // 对于蛾子,遍历物品槽找到异性后直接产生后代蚕卵
                            for (int j = 0; j < 9 && j != i; j++)
                                if (getStackInSlot(j) != null && getStackInSlot(j).getItem() == ChinaCraft.itemMoth && getStackInSlot(j).getItemDamage() == 1 - stack.getItemDamage()) {
                                    if (FeatureConfig.enableAdvancedSericulture) {
                                        int generation = Math.max(stack.getTagCompound().getInteger("Generation"), getStackInSlot(j).getTagCompound().getInteger("Generation"));
                                        int productivity = (stack.getTagCompound().getInteger("Productivity") + getStackInSlot(j).getTagCompound().getInteger("Productivity")) / 2;
                                        int speed = (stack.getTagCompound().getInteger("Speed") + getStackInSlot(j).getTagCompound().getInteger("Speed")) / 2;
                                        int fertiltiy = (stack.getTagCompound().getInteger("Fertility") + getStackInSlot(j).getTagCompound().getInteger("Fertility")) / 2;
                                        ItemStack graine = new ItemStack(ChinaCraft.silkworm);
                                        NBTTagCompound newNBT = new NBTTagCompound();
                                        newNBT.setInteger("Schedule", 0);
                                        newNBT.setInteger("Generation", 1 + generation);
                                        newNBT.setInteger("Productivity", worldObj.rand.nextInt(2) + productivity);
                                        newNBT.setInteger("Speed", worldObj.rand.nextInt(2) + speed);
                                        newNBT.setInteger("Fertility", worldObj.rand.nextInt(2) + fertiltiy);
                                        graine.setTagCompound(newNBT);
                                        setInventorySlotContents(Math.min(i, j), graine);
                                        if (worldObj.rand.nextInt(3) < fertiltiy) 
                                            setInventorySlotContents(Math.max(i, j), graine.copy());
                                        else
                                            setInventorySlotContents(Math.max(i, j), null);
                                        break;
                                    }
                                    else {
                                        setInventorySlotContents(Math.max(i, j), new ItemStack(ChinaCraft.silkworm));
                                        setInventorySlotContents(Math.min(i, j), new ItemStack(ChinaCraft.silkworm));
                                        break;
                                    }
                                }
                        }
                    }
                    else {
                        // stack无NBT时，设置默认NBT数据如下
                        NBTTagCompound nbt = new NBTTagCompound();
                        if (stack.getItem() == ChinaCraft.silkworm) nbt.setInteger("Schedule", 0);
                        if (FeatureConfig.enableAdvancedSericulture) {
                            nbt.setInteger("Generation", 1);
                            nbt.setInteger("Productivity", 1);
                            nbt.setInteger("Speed", 1);
                            nbt.setInteger("Fertility", 1);
                        }
                        stack.setTagCompound(nbt);
                    }  
                }
            }
                    
            if (needUpdate || this.isRunning != isRunning) {
                this.isRunning = isRunning;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                markDirty();
            }
        }
    }

    public boolean checkBasicRunningCondition() {
        boolean newStatus = true;
        String statusInfo = "None";
        if (getStackInSlot(10) != null && getStackInSlot(10).stackSize == 64) {
            newStatus = false;
            statusInfo = "gui.sericulture_frame.stopWork.outputSlotFull.info";
        }
        if ((!worldObj.isDaytime() && worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 7) || (worldObj.isDaytime() && worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord) < 7 && worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 7)) {
            newStatus = false;
            statusInfo = "gui.sericulture_frame.stopWork.lowLight.info";
        }
        else if (worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) > 12) {
            newStatus = false;
            statusInfo = "gui.sericulture_frame.stopWork.highLight.info";
        }
        else {
            BiomeGenBase biome = worldObj.getBiomeGenForCoords(xCoord, zCoord);
            float temperature = biome.temperature;
            float rainfall = biome.rainfall;
            // The temperature and rainfall of Plain are 0.8F, 0.4F
            if (temperature < 0.34F || temperature > 1.26F || rainfall < 0.09F || rainfall > 0.91F || BiomeDictionary.isBiomeOfType(biome, Type.NETHER) || BiomeDictionary.isBiomeOfType(biome, Type.END) || BiomeDictionary.isBiomeOfType(biome, Type.WASTELAND)) {
                newStatus = false;
                statusInfo = "gui.sericulture_frame.stopWork.biome.info";
            }
        }

        if (newStatus != this.canRun || !statusInfo.equals(this.statusInfo)) {
            this.canRun = newStatus;
            this.statusInfo = statusInfo;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();         
        }
        return newStatus;
    }
    
    public int getBaseMortalityFactor() {
        if (worldObj.isRaining() && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {
            statusInfo = "gui.sericulture_frame.noRainProtection.info";
            return 10;
        }
        else
            return 1;
    }  
    
    public int getGenerationMortalityFactor(int generation) {
        if (generation <= 20)
            return 1;
        else
            return generation - 20;
    }
    
    public int getMortalityDenominator() {
        return 300000;
    }

    public int getMulberryLeafEatenDenominator() {
        return 4000;
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }
    
    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 >= 0 && b0 < this.stack.length) {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.canRun = p_145839_1_.getBoolean("CanRun");
        this.isRunning = p_145839_1_.getBoolean("IsRunning");
        this.statusInfo = p_145839_1_.getString("statusInfo");
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setBoolean("CanRun", this.canRun);
        p_145841_1_.setBoolean("IsRunning", this.isRunning);
        p_145841_1_.setString("statusInfo", this.statusInfo);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.stack.length; ++i) {
            if (this.stack[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.stack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        p_145841_1_.setTag("Items", nbttaglist);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return p_94128_1_ == 0 ? slotsBottom : p_94128_1_ == 1 ? slotsTop : slotsSide;
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_3_ != 0 || p_102008_1_ != 0;
    }

}
