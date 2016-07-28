package unstudio.sinocraft.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import unstudio.sinocraft.client.model.ModelBlackDog;
import unstudio.sinocraft.client.model.ModelChinaZombie;
import unstudio.sinocraft.client.model.ModelKongmingLantern;
import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.entity.animal.EntityBlackDog;
import unstudio.sinocraft.entity.especial.EntityKongmingLantern;
import unstudio.sinocraft.entity.mob.EntityChinaZombie;
import unstudio.sinocraft.entity.projectile.EntityThrownBomb;
import unstudio.sinocraft.entity.projectile.EntityThrownFirecracker;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EntityRenderingRegistry {


    /**
     * 开始注册渲染器
     */
    public static void init() {

        EntityRenderingHandler(EntityBlackDog.class, new ModelBlackDog(), "sinocraft",
                "textures/entity/blackwolf/blackwolf.png");
        EntityRenderingHandler(EntityChinaZombie.class, new ModelChinaZombie(), "sinocraft",
                "textures/entity/chinazombie/chinazombie.png");
        EntityRenderingHandler(EntityKongmingLantern.class, new ModelKongmingLantern(), "sinocraft",
                "textures/entity/kongminglantern/kongminglantern.png");
        RenderingRegistry.registerEntityRenderingHandler(EntityThrownBomb.class, new RenderSnowball(SinoCraft.bomb));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrownFirecracker.class,
                new RenderSnowball(SinoCraft.firecracker));
    }

    /**
     * 注册实体的渲染器
     * @param entityClass 实体主类
     * @param modelBase 模型
     * @param resource 材质资源主类,"sinocraft"
     * @param location 材质地址,"textures/entity/example.png"
     */
    public static void EntityRenderingHandler(Class<? extends Entity> entityClass, ModelBase modelBase,
            final String resource, final String location) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderLiving(modelBase, 0) {
            @Override
            protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
                return new ResourceLocation(resource, location);
            }
        });
    }
}
