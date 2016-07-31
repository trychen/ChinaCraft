package unstudio.chinacraft.util.annotation.register;

import unstudio.chinacraft.client.render.item.SpecialItemRender;

/**
 * Created by trychen on 16/7/31.
 */
public interface ISpecialEquippedRender {
    void doRender();
    SpecialItemRender.RenderType getSpecialRenderType();
}
