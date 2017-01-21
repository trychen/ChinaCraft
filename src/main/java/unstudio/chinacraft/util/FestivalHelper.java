package unstudio.chinacraft.util;

import net.minecraft.block.BlockDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;

/**
 * Created by trychen on 17/1/20.
 */
public final class FestivalHelper {
    public static void initFestival() throws Exception {
        Festival festival = Festival.Spring;
//        Festival festival = getFestival();
        if (festival == null) return;
//        TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(TileEntityChest.class, new unstudio.chinacraft.client.render.tileentity.TileEntityChestRenderer(festival));

        Class tileEntityChestRendererClass = TileEntityChestRenderer.class;
        Field isChristmas = tileEntityChestRendererClass.getDeclaredField("field_147509_j");
        Field texture = tileEntityChestRendererClass.getDeclaredField("field_147503_f");
        Field textureDouble = tileEntityChestRendererClass.getDeclaredField("field_147508_c");
        //打破封装
        isChristmas.setAccessible(true);
        texture.setAccessible(true);
        textureDouble.setAccessible(true);
        //打破final
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(texture, texture.getModifiers() & ~Modifier.FINAL);
        modifiersField.setInt(textureDouble, textureDouble.getModifiers() & ~Modifier.FINAL);
        //开始设置
        isChristmas.setBoolean(TileEntityRendererDispatcher.instance.mapSpecialRenderers.get(TileEntityChest.class), true);
        texture.set(null, festival.texture);
        textureDouble.set(null, festival.doubleTexture);

//        Class blockDoorClass = BlockDoor.class;
//        Field a = blockDoorClass.getDeclaredField("field_150017_a");
//        Field b = blockDoorClass.getDeclaredField("field_150016_b");
//        a.setAccessible(true);
//        b.setAccessible(true);
//
//        IIcon[] ai = new IIcon[2];
//        IIcon[] bi = new IIcon[2];
//        ai[0] = Minecraft.getMinecraft().getTextureMapBlocks().registerIcon("door_wood_upper");
//        bi[0] = Minecraft.getMinecraft().getTextureMapBlocks().registerIcon("door_wood_lower");
//        ai[1] = new IconFlipped(ai[0], true, false);
//        bi[1] = new IconFlipped(bi[0], true, false);
//
//        a.set(Blocks.iron_door,ai);
//        b.set(Blocks.iron_door,bi);

        Class renderPigClass = RenderPig.class;
        Field pigTextures;
        try {
            pigTextures = renderPigClass.getDeclaredField("field_110887_f");
        } catch (NoSuchFieldException e){
            pigTextures = renderPigClass.getDeclaredField("pigTextures");
        }
        pigTextures.setAccessible(true);
        modifiersField.setInt(pigTextures, pigTextures.getModifiers() & ~Modifier.FINAL);

        pigTextures.set(null, new ResourceLocation("chinacraft:textures/entity/pig/pig.png"));

        System.out.println("Hooked Successful");
    }

    public enum Festival {
        Spring("spring", 1, 1);

        public ResourceLocation texture, doubleTexture;
        public final int month, day;

        Festival(String texture, int month, int day) {
            this.texture = new ResourceLocation("chinacraft:textures/entity/chest/" + texture + ".png");
            this.doubleTexture = new ResourceLocation("chinacraft:textures/entity/chest/" + texture + "_double.png");
            this.month = month;
            this.day = day;
        }
    }

    public static Festival getFestival() {
        LunarCalendar calendar = new LunarCalendar(Calendar.getInstance());
        if ((calendar.getMonth() == 12 && calendar.getDay() == LunarCalendar.monthDays(calendar.getYear(),12)) || (calendar.getMonth() == 1 && calendar.getDay() < 15)){
            return Festival.Spring;
        }
        for (Festival festival : Festival.values()) {
            if (festival.month == calendar.getMonth() && calendar.getDay() == festival.day) {
                return festival;
            }
        }
        return null;
    }
}
