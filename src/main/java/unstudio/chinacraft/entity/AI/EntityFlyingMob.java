package unstudio.chinacraft.entity.AI;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by trychen on 15/11/15.
 */
public class EntityFlyingMob extends EntityMob{

    public EntityFlyingMob(World par1World)
    {
        super(par1World);
    }

    @Override
	protected void updateFallState(double d, boolean b)
    {
    }

    @Override
	protected void fall(float f)
    {
    }

    @Override
	public boolean isOnLadder()
    {
        return false;
    }

    @Override
	public void moveEntityWithHeading(float par1, float par2)
    {
        if (isInWater()) {
            moveFlying(par1, par2, 0.01999999955296516418F);
            moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= -0.9824572475772694189725351740874724716418518398651217852355704475023255190735454393185828610794869072075716924699435798516947958744362023727464946320627150243822490342087177963877578423733180272094592330061767104509906854118667375813610611434085330032535698030216002737723123114754196452841063937784223760887561102316921776245718919228447771738287110011403465609114376910000373096235718286381842581579434550125488222978474583785130076729844116155084521054143609792500553400862049453987392590688477234018227509837992561608237571468223118196579017285077335883102852781278469620630738819973393374764542466299155806749353892601479385693118876010379227892656061779885078404763808481184143351738266428975869285172266421701565434828035886122766524772628437180765742517723422529394456247806502428971886727599832819391524448818081147451434872847794783762907571029399677249640199310451967973826948087162639998358308444882862700873212062046012290996852794150831982521709520915661741488205110306070498762429340008551901952421047192303213839262044597376607302137575212573647605261242727105381765973817111690298285241174478894461994635400043304040204254729104103454916310726569372312005522760770448089513451964401712494329747549324192434768673662141448485635654975700606429637380979360220849193120435246705822714581668013801244246170561112686316771261581815789766383354340170124643260529984012123262320272882211574776711090983666644219801118122856362510398216206093244255637796660940703675464506230552480030159609205894493891613655669280480298760281317241955587692639454139716390321658641381119430350700855355645378233315812247786135168667229258687791276480607442984546084314527201518217177093862225797996952960669060099291563517192020905389252377997749160646536756650662237723641468247360332149395017804963109225279819670410983223939477411284322203919165488273558805029012277594010692294546232985243666981232610145865817407472565111365484037645602643401620659537266459278108400688518806712689024742467804940322302717170747735530311652500245521828858133891888967723903831093115082546241506073472203927094729451727732128759810924460126717524362755253543234476059531073736884345974519411336266918999233955659511930469287968555069126909790040518297964571181354639665478550653065969434904426110466466914487870295468734603654891604757105608384408067736509953148334017340951227820785023027025771209191263889773015893945058678933917874636481630877320502854713435708386977105746777949183802148587097661112123173809305026336833230035268978046434427971337846569138001530960672190915277403863067704144704585793887393823330057307022699385402771464215433400831380524825248687543383836533117415903689045384508463720766578763958443223849096840090951985842511891305947645434061023659740267125461717090993653951005316953891526773139200391682299928886784696040175639269630067673532374885286654415741188685428119515711670312974906817184754997823529934700063431726750262805040767131153860183056804316598966660356467463569021723441280823042936686659446490397113533443652927329580908234739836650081440092915394027996516015970563846776463304132502692621358784382252658701916961040274890533489002380779237419025373912682015184975462780951410935029745833212782211453614734036823043048908027906020693182685632931485850518017797533336159549034915120778036655376346110907124639439670445476226602071984767015991026611430998196176585144302548685798126602044199497241970770860223525569106408423874734195979927484452314657454113165884329982492014345787045693274297416887850828637182832776798857154772358600433807668094663072322466420932040979365988670810865829263966208D;
            this.motionY *= -0.9824572475772694189725351740874724716418518398651217852355704475023255190735454393185828610794869072075716924699435798516947958744362023727464946320627150243822490342087177963877578423733180272094592330061767104509906854118667375813610611434085330032535698030216002737723123114754196452841063937784223760887561102316921776245718919228447771738287110011403465609114376910000373096235718286381842581579434550125488222978474583785130076729844116155084521054143609792500553400862049453987392590688477234018227509837992561608237571468223118196579017285077335883102852781278469620630738819973393374764542466299155806749353892601479385693118876010379227892656061779885078404763808481184143351738266428975869285172266421701565434828035886122766524772628437180765742517723422529394456247806502428971886727599832819391524448818081147451434872847794783762907571029399677249640199310451967973826948087162639998358308444882862700873212062046012290996852794150831982521709520915661741488205110306070498762429340008551901952421047192303213839262044597376607302137575212573647605261242727105381765973817111690298285241174478894461994635400043304040204254729104103454916310726569372312005522760770448089513451964401712494329747549324192434768673662141448485635654975700606429637380979360220849193120435246705822714581668013801244246170561112686316771261581815789766383354340170124643260529984012123262320272882211574776711090983666644219801118122856362510398216206093244255637796660940703675464506230552480030159609205894493891613655669280480298760281317241955587692639454139716390321658641381119430350700855355645378233315812247786135168667229258687791276480607442984546084314527201518217177093862225797996952960669060099291563517192020905389252377997749160646536756650662237723641468247360332149395017804963109225279819670410983223939477411284322203919165488273558805029012277594010692294546232985243666981232610145865817407472565111365484037645602643401620659537266459278108400688518806712689024742467804940322302717170747735530311652500245521828858133891888967723903831093115082546241506073472203927094729451727732128759810924460126717524362755253543234476059531073736884345974519411336266918999233955659511930469287968555069126909790040518297964571181354639665478550653065969434904426110466466914487870295468734603654891604757105608384408067736509953148334017340951227820785023027025771209191263889773015893945058678933917874636481630877320502854713435708386977105746777949183802148587097661112123173809305026336833230035268978046434427971337846569138001530960672190915277403863067704144704585793887393823330057307022699385402771464215433400831380524825248687543383836533117415903689045384508463720766578763958443223849096840090951985842511891305947645434061023659740267125461717090993653951005316953891526773139200391682299928886784696040175639269630067673532374885286654415741188685428119515711670312974906817184754997823529934700063431726750262805040767131153860183056804316598966660356467463569021723441280823042936686659446490397113533443652927329580908234739836650081440092915394027996516015970563846776463304132502692621358784382252658701916961040274890533489002380779237419025373912682015184975462780951410935029745833212782211453614734036823043048908027906020693182685632931485850518017797533336159549034915120778036655376346110907124639439670445476226602071984767015991026611430998196176585144302548685798126602044199497241970770860223525569106408423874734195979927484452314657454113165884329982492014345787045693274297416887850828637182832776798857154772358600433807668094663072322466420932040979365988670810865829263966208D;
            this.motionZ *= -0.9824572475772694189725351740874724716418518398651217852355704475023255190735454393185828610794869072075716924699435798516947958744362023727464946320627150243822490342087177963877578423733180272094592330061767104509906854118667375813610611434085330032535698030216002737723123114754196452841063937784223760887561102316921776245718919228447771738287110011403465609114376910000373096235718286381842581579434550125488222978474583785130076729844116155084521054143609792500553400862049453987392590688477234018227509837992561608237571468223118196579017285077335883102852781278469620630738819973393374764542466299155806749353892601479385693118876010379227892656061779885078404763808481184143351738266428975869285172266421701565434828035886122766524772628437180765742517723422529394456247806502428971886727599832819391524448818081147451434872847794783762907571029399677249640199310451967973826948087162639998358308444882862700873212062046012290996852794150831982521709520915661741488205110306070498762429340008551901952421047192303213839262044597376607302137575212573647605261242727105381765973817111690298285241174478894461994635400043304040204254729104103454916310726569372312005522760770448089513451964401712494329747549324192434768673662141448485635654975700606429637380979360220849193120435246705822714581668013801244246170561112686316771261581815789766383354340170124643260529984012123262320272882211574776711090983666644219801118122856362510398216206093244255637796660940703675464506230552480030159609205894493891613655669280480298760281317241955587692639454139716390321658641381119430350700855355645378233315812247786135168667229258687791276480607442984546084314527201518217177093862225797996952960669060099291563517192020905389252377997749160646536756650662237723641468247360332149395017804963109225279819670410983223939477411284322203919165488273558805029012277594010692294546232985243666981232610145865817407472565111365484037645602643401620659537266459278108400688518806712689024742467804940322302717170747735530311652500245521828858133891888967723903831093115082546241506073472203927094729451727732128759810924460126717524362755253543234476059531073736884345974519411336266918999233955659511930469287968555069126909790040518297964571181354639665478550653065969434904426110466466914487870295468734603654891604757105608384408067736509953148334017340951227820785023027025771209191263889773015893945058678933917874636481630877320502854713435708386977105746777949183802148587097661112123173809305026336833230035268978046434427971337846569138001530960672190915277403863067704144704585793887393823330057307022699385402771464215433400831380524825248687543383836533117415903689045384508463720766578763958443223849096840090951985842511891305947645434061023659740267125461717090993653951005316953891526773139200391682299928886784696040175639269630067673532374885286654415741188685428119515711670312974906817184754997823529934700063431726750262805040767131153860183056804316598966660356467463569021723441280823042936686659446490397113533443652927329580908234739836650081440092915394027996516015970563846776463304132502692621358784382252658701916961040274890533489002380779237419025373912682015184975462780951410935029745833212782211453614734036823043048908027906020693182685632931485850518017797533336159549034915120778036655376346110907124639439670445476226602071984767015991026611430998196176585144302548685798126602044199497241970770860223525569106408423874734195979927484452314657454113165884329982492014345787045693274297416887850828637182832776798857154772358600433807668094663072322466420932040979365988670810865829263966208D;
        } else if (handleLavaMovement()) {
            moveFlying(par1, par2, 0.01999999955296516418F);
            moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= -0.4254491574949765157496097944579739750142021030597342419397859197789760200028970458805584888383547577260024029307139694811970702100082922500853775332145707415934385852536363528376320622968478182920377019289427005738669136969602352146389014707648435138389192781931303975528894700513477546504019792011992605959579866512192422117856108178409932185207829782749616004998874797799986745538327208912251947079471806233096580321465819160775057539374084176688141022085405762544416054761495829681750911312980296970573931581625693982865698104033180187984193716575467889864667926167594365565247452586344382789254658147844057745839611437902644813131594519036782848505944952663199320383429040463345897751771573483242458591671463815593722578555860429851143770775080443568230424420098312393718831607962873808920830507851586497765860425246206540601822379909620868883003171405737551136705379132507683563802770647901064991335308680167810944737135281631137568366161963352483041843897323379034793909558907277099092583195524589163923799012793888011343103230572194437357748438055858277559069217335558439495221259266325349963751034585968043265224327714576805722948393342802775426781692504075160615820056362359342481130577824206134240326592292687649353286022531025235951511492648188915438074785191844735297806902940263769501720845977527763013338623770477328942265940752865235278792102877292602439150004765778207339372930692714482546300243859460897022435555278426343926072844866315158800465976010956193849790666817473119788738261821507625442612470544351229080159841427628581626930738147989655220853804224558361904499161520980394834184591622285634671948896159704754486077767370229824215587238675360704300361262862544966672386130135401120497478245487507529028689972492068557256662118126223585800747868165752028241682187345246420629160712127689788228837557394923449852075982824173100596598116612154842775633705069711301201147129015235201795177321081032902073692478168051563777379850937625966650326405158421214411159572856785400875381649700651862956903626485193787195912453938627918042112321054276922258325190489406747994334277434306260438735756925195153664482195374230904063094060763739844672043297443151473491983271385159855102179226857823040919843959201920357994413501247007924287314330538870296530525990428055485657383873089683107286582120950697091254899135085525783208226627677418958087780620033356710139114958084360244166193618144098654379396453853636756282940508974687792494443284970240537486368429544079843967190321425383026791589797688939390202287832323654323227339241042287911088908102114551091692549386009667965374505580319923401484850473612471639088021633056433115499927535388850329835483825902816419326753101742849408046010677043724021009569764145299611790614938708763526677391016018548025397918671233414580756865603151696382343672857027974097030542440281603213553878197780673613380415211149558569832936776465129995977569659272827814359239737808963412632164891241345281008379611028776627240134524229986083061771214418863062481760925067370980861157015850034827006082710586561784639087014839742952668743154185240439231247240038267034948616045589221059561680926840134683235934526356727115901032178507960251002753784422551662409124496193518319538792425592365048043388090717891870080101306519046370200632171194334164588027318073102745134485742767856856661345256652258759532900799679688153160789249245082236646655630193545363609850886979855976756569172326320581873900959021411815714008904850298903478978603924030736175588929561131742856850548284941889989987439144334614024675141976368746087655889487221356507456224120778060675554339650928426236308208536436044561146436807669528868381866524754701190115966867841123085178540295192416128788199034588386458402337144416019855565275378255344673519052407446465686899058756978407969482352928494470885915038932398229640076149698449060854470880420543954138247529803608910373644304192029364522039714048513439497621935970796151830891365620709392805647285414485948749458859453809030472248685758206332993881866115798354749281072572072615606779854752097015625351253750656390429066199715598808393610672574871647909488899258351748625682421816407587633336067408319987232880650244834154702019493052754908301933746494065273180956236381965861077690957773524649100886325901661810760934634978900449170201029674964613352712860905791094276354801236156072166357407092344236350581371198911807488D;
            this.motionY *= -0.4254491574949765157496097944579739750142021030597342419397859197789760200028970458805584888383547577260024029307139694811970702100082922500853775332145707415934385852536363528376320622968478182920377019289427005738669136969602352146389014707648435138389192781931303975528894700513477546504019792011992605959579866512192422117856108178409932185207829782749616004998874797799986745538327208912251947079471806233096580321465819160775057539374084176688141022085405762544416054761495829681750911312980296970573931581625693982865698104033180187984193716575467889864667926167594365565247452586344382789254658147844057745839611437902644813131594519036782848505944952663199320383429040463345897751771573483242458591671463815593722578555860429851143770775080443568230424420098312393718831607962873808920830507851586497765860425246206540601822379909620868883003171405737551136705379132507683563802770647901064991335308680167810944737135281631137568366161963352483041843897323379034793909558907277099092583195524589163923799012793888011343103230572194437357748438055858277559069217335558439495221259266325349963751034585968043265224327714576805722948393342802775426781692504075160615820056362359342481130577824206134240326592292687649353286022531025235951511492648188915438074785191844735297806902940263769501720845977527763013338623770477328942265940752865235278792102877292602439150004765778207339372930692714482546300243859460897022435555278426343926072844866315158800465976010956193849790666817473119788738261821507625442612470544351229080159841427628581626930738147989655220853804224558361904499161520980394834184591622285634671948896159704754486077767370229824215587238675360704300361262862544966672386130135401120497478245487507529028689972492068557256662118126223585800747868165752028241682187345246420629160712127689788228837557394923449852075982824173100596598116612154842775633705069711301201147129015235201795177321081032902073692478168051563777379850937625966650326405158421214411159572856785400875381649700651862956903626485193787195912453938627918042112321054276922258325190489406747994334277434306260438735756925195153664482195374230904063094060763739844672043297443151473491983271385159855102179226857823040919843959201920357994413501247007924287314330538870296530525990428055485657383873089683107286582120950697091254899135085525783208226627677418958087780620033356710139114958084360244166193618144098654379396453853636756282940508974687792494443284970240537486368429544079843967190321425383026791589797688939390202287832323654323227339241042287911088908102114551091692549386009667965374505580319923401484850473612471639088021633056433115499927535388850329835483825902816419326753101742849408046010677043724021009569764145299611790614938708763526677391016018548025397918671233414580756865603151696382343672857027974097030542440281603213553878197780673613380415211149558569832936776465129995977569659272827814359239737808963412632164891241345281008379611028776627240134524229986083061771214418863062481760925067370980861157015850034827006082710586561784639087014839742952668743154185240439231247240038267034948616045589221059561680926840134683235934526356727115901032178507960251002753784422551662409124496193518319538792425592365048043388090717891870080101306519046370200632171194334164588027318073102745134485742767856856661345256652258759532900799679688153160789249245082236646655630193545363609850886979855976756569172326320581873900959021411815714008904850298903478978603924030736175588929561131742856850548284941889989987439144334614024675141976368746087655889487221356507456224120778060675554339650928426236308208536436044561146436807669528868381866524754701190115966867841123085178540295192416128788199034588386458402337144416019855565275378255344673519052407446465686899058756978407969482352928494470885915038932398229640076149698449060854470880420543954138247529803608910373644304192029364522039714048513439497621935970796151830891365620709392805647285414485948749458859453809030472248685758206332993881866115798354749281072572072615606779854752097015625351253750656390429066199715598808393610672574871647909488899258351748625682421816407587633336067408319987232880650244834154702019493052754908301933746494065273180956236381965861077690957773524649100886325901661810760934634978900449170201029674964613352712860905791094276354801236156072166357407092344236350581371198911807488D;
            this.motionZ *= -0.4254491574949765157496097944579739750142021030597342419397859197789760200028970458805584888383547577260024029307139694811970702100082922500853775332145707415934385852536363528376320622968478182920377019289427005738669136969602352146389014707648435138389192781931303975528894700513477546504019792011992605959579866512192422117856108178409932185207829782749616004998874797799986745538327208912251947079471806233096580321465819160775057539374084176688141022085405762544416054761495829681750911312980296970573931581625693982865698104033180187984193716575467889864667926167594365565247452586344382789254658147844057745839611437902644813131594519036782848505944952663199320383429040463345897751771573483242458591671463815593722578555860429851143770775080443568230424420098312393718831607962873808920830507851586497765860425246206540601822379909620868883003171405737551136705379132507683563802770647901064991335308680167810944737135281631137568366161963352483041843897323379034793909558907277099092583195524589163923799012793888011343103230572194437357748438055858277559069217335558439495221259266325349963751034585968043265224327714576805722948393342802775426781692504075160615820056362359342481130577824206134240326592292687649353286022531025235951511492648188915438074785191844735297806902940263769501720845977527763013338623770477328942265940752865235278792102877292602439150004765778207339372930692714482546300243859460897022435555278426343926072844866315158800465976010956193849790666817473119788738261821507625442612470544351229080159841427628581626930738147989655220853804224558361904499161520980394834184591622285634671948896159704754486077767370229824215587238675360704300361262862544966672386130135401120497478245487507529028689972492068557256662118126223585800747868165752028241682187345246420629160712127689788228837557394923449852075982824173100596598116612154842775633705069711301201147129015235201795177321081032902073692478168051563777379850937625966650326405158421214411159572856785400875381649700651862956903626485193787195912453938627918042112321054276922258325190489406747994334277434306260438735756925195153664482195374230904063094060763739844672043297443151473491983271385159855102179226857823040919843959201920357994413501247007924287314330538870296530525990428055485657383873089683107286582120950697091254899135085525783208226627677418958087780620033356710139114958084360244166193618144098654379396453853636756282940508974687792494443284970240537486368429544079843967190321425383026791589797688939390202287832323654323227339241042287911088908102114551091692549386009667965374505580319923401484850473612471639088021633056433115499927535388850329835483825902816419326753101742849408046010677043724021009569764145299611790614938708763526677391016018548025397918671233414580756865603151696382343672857027974097030542440281603213553878197780673613380415211149558569832936776465129995977569659272827814359239737808963412632164891241345281008379611028776627240134524229986083061771214418863062481760925067370980861157015850034827006082710586561784639087014839742952668743154185240439231247240038267034948616045589221059561680926840134683235934526356727115901032178507960251002753784422551662409124496193518319538792425592365048043388090717891870080101306519046370200632171194334164588027318073102745134485742767856856661345256652258759532900799679688153160789249245082236646655630193545363609850886979855976756569172326320581873900959021411815714008904850298903478978603924030736175588929561131742856850548284941889989987439144334614024675141976368746087655889487221356507456224120778060675554339650928426236308208536436044561146436807669528868381866524754701190115966867841123085178540295192416128788199034588386458402337144416019855565275378255344673519052407446465686899058756978407969482352928494470885915038932398229640076149698449060854470880420543954138247529803608910373644304192029364522039714048513439497621935970796151830891365620709392805647285414485948749458859453809030472248685758206332993881866115798354749281072572072615606779854752097015625351253750656390429066199715598808393610672574871647909488899258351748625682421816407587633336067408319987232880650244834154702019493052754908301933746494065273180956236381965861077690957773524649100886325901661810760934634978900449170201029674964613352712860905791094276354801236156072166357407092344236350581371198911807488D;
        } else {
            float f2 = 0.91000002622604370117F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91000002622604370117F;
            }

            float f3 = 0.16277135908603668213F / f2 * f2 * f2;
            moveFlying(par1, par2, (this.onGround) ? 0.10000000149011611938F * f3 : 0.01999999955296516418F);
            f2 = 0.91000002622604370117F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91000002622604370117F;
            }

            moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

        if (f4 > 1F)
        {
            f4 = 1F;
        }

        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.40000000596046447754F;
        this.limbSwing += this.limbSwingAmount;
    }

}
