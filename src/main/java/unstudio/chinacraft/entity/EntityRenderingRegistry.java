package unstudio.chinacraft.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import unstudio.chinacraft.client.model.entity.ModelBlackDog;
import unstudio.chinacraft.client.model.entity.ModelChinaZombie;
import unstudio.chinacraft.client.model.block.ModelKongmingLantern;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.animal.EntityBlackDog;
import unstudio.chinacraft.entity.especial.EntityKongmingLantern;
import unstudio.chinacraft.entity.mob.EntityChinaZombie;
import unstudio.chinacraft.entity.projectile.EntityProjectile;
import unstudio.chinacraft.entity.projectile.EntityThrownBomb;
import unstudio.chinacraft.entity.projectile.EntityThrownFirecracker;
import cpw.mods.fml.client.registry.RenderingRegistry;
import unstudio.chinacraft.entity.render.RenderProjectile;

public class EntityRenderingRegistry {


    /**
     * 开始注册渲染器
     */
    public static void init() {

        EntityRenderingHandler(EntityBlackDog.class, new ModelBlackDog(), "chinacraft",
                "textures/entity/blackwolf/blackwolf.png");
        EntityRenderingHandler(EntityChinaZombie.class, new ModelChinaZombie(), "chinacraft",
                "textures/entity/chinazombie/chinazombie.png");
        EntityRenderingHandler(EntityKongmingLantern.class, new ModelKongmingLantern(), "chinacraft",
                "textures/entity/kongminglantern/kongminglantern.png");
        RenderingRegistry.registerEntityRenderingHandler(EntityThrownBomb.class, new RenderSnowball(ChinaCraft.bomb));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrownFirecracker.class,
                new RenderSnowball(ChinaCraft.firecracker));
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, new RenderProjectile());
    }

    /**
     * 注册实体的渲染器
     * @param entityClass 实体主类
     * @param modelBase 模型
     * @param resource 材质资源主类,"chinacraft"
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
