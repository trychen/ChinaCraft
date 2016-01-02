package unstudio.chinacraft.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import unstudio.chinacraft.entity.projectile.EntitySpear;

/**
 * Use for nothing.
 * Created by trychen on 15/11/27.
 */

@SideOnly(Side.CLIENT)
public class RenderSpear extends Render
{
    public void renderArrow(EntitySpear entityironspear, double d, double d1, double d2, float f, float f1)
    {
        if ((entityironspear.prevRotationYaw == 0F) && (entityironspear.prevRotationPitch == 0F))
            return;

        bindEntityTexture(entityironspear);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(entityironspear.prevRotationYaw + (entityironspear.rotationYaw - entityironspear.prevRotationYaw) * f1 - 90.0F, 0F, 1F, 0F);
        GL11.glRotatef(entityironspear.prevRotationPitch + (entityironspear.rotationPitch - entityironspear.prevRotationPitch) * f1, 0F, 0F, 1F);
        Tessellator tessellator = Tessellator.instance;
        int i = 0;
        float f2 = 0F;
        float f3 = 0.5F;
        float f4 = (0 + i * 10) / 32.0F;
        float f5 = (5 + i * 10) / 32.0F;
        float f6 = 0F;
        float f7 = 0.15625F;
        float f8 = (5 + i * 10) / 32.0F;
        float f9 = (10 + i * 10) / 32.0F;
        float f10 = 0.05624999850988388062F;
        GL11.glEnable(32826);
        float f11 = entityironspear.arrowShake - f1;
        if (f11 > 0F) {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, 0F, 0F, 1F);
        }
        GL11.glRotatef(45.0F, 1F, 0F, 0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0F, 0F, 0F);
        GL11.glNormal3f(f10, 0F, 0F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f6, f8);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f7, f8);
        tessellator.addVertexWithUV(0.0D, -0.9838022897473774795854025772485719629113659430657608468349620745330499387812798364939620460227097030431203854355552577199843941899912646459793288341574305094908481597119208064880886790180530567610448576550854531511312589867061401806555876965792418427723856499566031631790791036673693033956489705496789980374999662216733419693245869133614697696656399919278051357569388444263781432394897591277268891363480712164200600198746284603972536231088389404922005227668735540197234916748646423523295802556998446637518172759761809631886453489418007061601216143754544854597449702392584820887473015645935891494854495831851525760360476901570452716787202151950366744992726764091113090339644399161015803202518083163213651676283511783366485748112158846405476444175658385152045167372273722717437191986896824650240978707060066073221566426402460993799584372090011839497990335458245284542434787894495995300863997875912004871944114735539971752808946835060897033417385780599544302331673363233388193923382916062774002455557916848743778503799432686191332602874697517404039700189362054895501306574451208723140544254806614002540514499364157119042122701813710583667004423924106844636284230933231343179050615330160525919228759918102026580025150850412523832062243345329899888467374199768151934753449642358451459769284127839519458373431446585448534025687181796793627381097959699737340676328959232060743669855248432696054845328457101666199195286755453545039933605713780352755817514291253140515709204561564397021721942422667792598899605034404350835326068928049644344620206848818740461304135410058551448698762404349867477817775470586471899550143425502093647574805851587200692132143429811653111316859686486916034532410972737837769899852353350871033787881085384903237745273712172392820152787162205275901108513703989292011457934262330256404296844865962467555941822439149110376343830795494233387522993107818748060936845499622016445836163047572826202486232036832848393801584172953173604752762494235948762821973356656392934624377690085537008094224914506237183328188855894574468579431209049903375540325162663066205648719536524697467577804678801477620814941909500470673438289403560214108966451998701454998350778676415608911764878198363702644801724452007669218569954301100807093255866763629712452840989976741728672709143568546597149183447527730105523493410323566887444641117408553724022621204063945122624740499558852737191176563541311258949285731760437038633673202874853892884138319082791126977738109243742693493084794400129899063761221875807854037128739391844495699885747120215753088975244359524543733910224962828755038098155848826243314819602104585222686753608036127982006091659409936569099127861435803644110785292181277959234064044307136359660199696344834858239935358377714927969648471695289913907422765456150015314051267378818798163240151823360375474753310596080394546194091763752643525265418776040669041338887882387737481421603726477847013460498395199872315080063924728790000680647114288681681599962387184298426218328359572426293984997146197413050975268823226228586213200435269197225591040688609828929651175873739232018009027466713488705395011997600885522385714515242715668603416762385202348308448002125804892524694771556908662713511811383654769066883365430054558953362820545418106173601281484571359958763347056735035669483644115204382713499594857245444231251106483978225080973793288543867610828902526739493409517064686757279952435539840810572811782882369431657022144813318347475753934089387555601611261345884865190304906907093198407814535439873996763222691526108080981299620333613894706573625616207595856746596041956061716266314230812539544440935070746904887296D, -0.9838022897473774795854025772485719629113659430657608468349620745330499387812798364939620460227097030431203854355552577199843941899912646459793288341574305094908481597119208064880886790180530567610448576550854531511312589867061401806555876965792418427723856499566031631790791036673693033956489705496789980374999662216733419693245869133614697696656399919278051357569388444263781432394897591277268891363480712164200600198746284603972536231088389404922005227668735540197234916748646423523295802556998446637518172759761809631886453489418007061601216143754544854597449702392584820887473015645935891494854495831851525760360476901570452716787202151950366744992726764091113090339644399161015803202518083163213651676283511783366485748112158846405476444175658385152045167372273722717437191986896824650240978707060066073221566426402460993799584372090011839497990335458245284542434787894495995300863997875912004871944114735539971752808946835060897033417385780599544302331673363233388193923382916062774002455557916848743778503799432686191332602874697517404039700189362054895501306574451208723140544254806614002540514499364157119042122701813710583667004423924106844636284230933231343179050615330160525919228759918102026580025150850412523832062243345329899888467374199768151934753449642358451459769284127839519458373431446585448534025687181796793627381097959699737340676328959232060743669855248432696054845328457101666199195286755453545039933605713780352755817514291253140515709204561564397021721942422667792598899605034404350835326068928049644344620206848818740461304135410058551448698762404349867477817775470586471899550143425502093647574805851587200692132143429811653111316859686486916034532410972737837769899852353350871033787881085384903237745273712172392820152787162205275901108513703989292011457934262330256404296844865962467555941822439149110376343830795494233387522993107818748060936845499622016445836163047572826202486232036832848393801584172953173604752762494235948762821973356656392934624377690085537008094224914506237183328188855894574468579431209049903375540325162663066205648719536524697467577804678801477620814941909500470673438289403560214108966451998701454998350778676415608911764878198363702644801724452007669218569954301100807093255866763629712452840989976741728672709143568546597149183447527730105523493410323566887444641117408553724022621204063945122624740499558852737191176563541311258949285731760437038633673202874853892884138319082791126977738109243742693493084794400129899063761221875807854037128739391844495699885747120215753088975244359524543733910224962828755038098155848826243314819602104585222686753608036127982006091659409936569099127861435803644110785292181277959234064044307136359660199696344834858239935358377714927969648471695289913907422765456150015314051267378818798163240151823360375474753310596080394546194091763752643525265418776040669041338887882387737481421603726477847013460498395199872315080063924728790000680647114288681681599962387184298426218328359572426293984997146197413050975268823226228586213200435269197225591040688609828929651175873739232018009027466713488705395011997600885522385714515242715668603416762385202348308448002125804892524694771556908662713511811383654769066883365430054558953362820545418106173601281484571359958763347056735035669483644115204382713499594857245444231251106483978225080973793288543867610828902526739493409517064686757279952435539840810572811782882369431657022144813318347475753934089387555601611261345884865190304906907093198407814535439873996763222691526108080981299620333613894706573625616207595856746596041956061716266314230812539544440935070746904887296D, f7, f9);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f6, f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0F, 0F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f6, f8);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f7, f8);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f7, f9);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f6, f9);
        tessellator.draw();
        for (int j = 0; j < 4; ++j) {
            GL11.glRotatef(90.0F, 1F, 0F, 0F);
            GL11.glNormal3f(0F, 0F, f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0.0D, 0.0D, 0D, f2, f4);
            tessellator.addVertexWithUV(0.0D, 0.0D, 0D, f3, f4);
            tessellator.addVertexWithUV(0.0D, 0.0D, 0D, f3, f5);
            tessellator.addVertexWithUV(0.0D, 0.0D, 0D, f2, f5);
            tessellator.draw();
        }

        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110779_a(EntitySpear var1) {
        return new ResourceLocation("");
    }

    @Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110779_a((EntitySpear)par1Entity);
    }

    @Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        renderArrow((EntitySpear)entity, d, d1, d2, f, f1);
    }
}
