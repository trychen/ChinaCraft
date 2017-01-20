package unstudio.chinacraft.util;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;

/**
 * Created by trychen on 17/1/20.
 */
public final class FestivalHelper {
    public static void initFestival() throws Exception{
//        Festival festival = Festival.Spring;
        Festival festival = getFestival();
        if (festival == null) return;
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
    }

    enum Festival {
        Spring("christmas", 1, 1);

        public final ResourceLocation texture, doubleTexture;
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
        for (Festival festival : Festival.values()) {
            if (festival.month == calendar.getMonth() && calendar.getDay() == festival.day){
                return festival;
            }
        }
        return null;
    }
}
