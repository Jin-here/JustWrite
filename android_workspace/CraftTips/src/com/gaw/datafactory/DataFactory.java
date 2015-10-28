package com.gaw.datafactory;

import java.util.ArrayList;
import java.util.List;

import com.gaw.activity.R;
import com.gaw.pojo.Thing;

public class DataFactory {
	//一共的模板数量,第一次输入数据后，设置为0
	private int firstaddnum = 172;
	//更新模板数量
	private int updateaddnum = 0;
	//修改数据库数据
	private int xiugainum = 0;
	//删除数据库数据
	private int shanchunum = 0;
	
	
	
	/**************get*****************set*********************/


	public int getFirstaddnum() {
		return firstaddnum;
	}

	

	public int getUpdateaddnum() {
		return updateaddnum;
	}

	

	public int getXiugainum() {
		return xiugainum;
	}



	public int getShanchunum() {
		return shanchunum;
	}




	/**************模*****************板*********************/
	
	public List<Thing> getThings_first(){
		List<Thing> things = new ArrayList<Thing>();
		//**玻璃，铁锭，金锭,红砖
		
		/********************name**jp********picture**ischild*isshow*zuoyong*fenglei*****************/
		things.add(new Thing("红砖","hz",R.drawable.hongzhuan,1,1,"材料","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.niantu,R.drawable.kong,R.drawable.kong,R.drawable.ranshao,R.drawable.kong));
		things.add(new Thing("金锭","jd",R.drawable.jingding,1,1,"材料","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.jingkuangshi,R.drawable.kong,R.drawable.kong,R.drawable.ranshao,R.drawable.kong));
		things.add(new Thing("铁锭","td",R.drawable.tieding,1,1,"材料","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tiekuangshi,R.drawable.kong,R.drawable.kong,R.drawable.ranshao,R.drawable.kong));
		things.add(new Thing("玻璃","bl",R.drawable.boli,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.shazi,R.drawable.kong,R.drawable.kong,R.drawable.ranshao,R.drawable.kong));
		things.add(new Thing("骨头","gt",R.drawable.gutou,1,1,"骷髅掉落","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kulou,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("火药","hy",R.drawable.huoyao,1,1,"恶魂掉落","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.ehun,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("蜘蛛眼","zzy",R.drawable.zhizhuyan,1,1,"蜘蛛、洞穴蜘蛛掉落","7",R.drawable.kong,R.drawable.dongxuezhizhu,R.drawable.kong,R.drawable.kong,R.drawable.zhizhu,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("线","x",R.drawable.xian,1,1,"蜘蛛、洞穴蜘蛛掉落","7",R.drawable.kong,R.drawable.dongxuezhizhu,R.drawable.kong,R.drawable.kong,R.drawable.zhizhu,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烈焰棒","lyb",R.drawable.lieyanbang,1,1,"烈焰人掉落","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.lieyanreng,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("末影珍珠","myzz",R.drawable.moyingzhengzhu,1,1,"末影人掉落","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.moyingreng,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_苦力怕","yhzxklp",R.drawable.yanhuozhixingkulipa,1,1,"颜色对应染料颜色,各种头颅均可","14",R.drawable.huoyao,R.drawable.kuloutoulu,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_爆裂状","yhzxblz",R.drawable.yanhuozhixingbaoliezhuang,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.yumao,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_星状","yhzxxz",R.drawable.yanhuozhixingxingzhuang,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.jingli,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_大球状","yhzxdqz",R.drawable.yanhuozhixingdaqiuzhuang,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.huoyandan,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_闪烁","yhzxss",R.drawable.yanhuozhixingshanshuo,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.yingshifeng,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_扩散","yhzxks",R.drawable.yanhuozhixingkuosan,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.zuanshi,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟火之星_小球状","yhzxxqz",R.drawable.yanhuozhixingxiaoqiuzhuang,1,1,"颜色对应染料颜色","14",R.drawable.huoyao,R.drawable.kong,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("烟花","yh",R.drawable.yanhua,1,1,"特效对应‘烟火之星’种类","14",R.drawable.kong,R.drawable.yanhuozhixingxiaoqiuzhuang,R.drawable.kong,R.drawable.kong,R.drawable.zhi,R.drawable.kong,R.drawable.kong,R.drawable.huoyao,R.drawable.kong));
		things.add(new Thing("火箭","hj",R.drawable.huojian,1,1,"可制作烟花,增加火药提升高度","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.zhi,R.drawable.kong,R.drawable.kong,R.drawable.huoyao,R.drawable.kong));
		things.add(new Thing("马鞍","ma",R.drawable.maan,1,1,"驯服马后，控制马用","21",R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.tieding,R.drawable.pige,R.drawable.tieding,R.drawable.kong,R.drawable.tieding));
		things.add(new Thing("地毯","dt",R.drawable.ditan,1,1,"铺地板等，颜色对应毛色","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.yangmao,R.drawable.kong,R.drawable.yangmao,R.drawable.yangmao));
		things.add(new Thing("栓绳","ss",R.drawable.shuansheng,1,1,"牵引或栓住动物","21",R.drawable.xian,R.drawable.xian,R.drawable.kong,R.drawable.xian,R.drawable.nianyeqiu,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xian));
		things.add(new Thing("木板","mb",R.drawable.muban,1,1,"基础材料","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yuanmu,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("木棍","mg",R.drawable.mugun,1,1,"合成基本工具","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("火把","hb",R.drawable.huoba,1,1,"照明","13",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.meitan,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("工作台","zgt",R.drawable.gongzuotai,1,1,"物品合成台","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("熔炉","rl",R.drawable.ronglu,1,1,"烧制食物、材料","21",R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.kong,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi));
		things.add(new Thing("箱子","xz",R.drawable.xiangzi,1,1,"存放物品，2个可叠加","7",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("梯子","tz",R.drawable.tizi,1,1,"爬墙","21",R.drawable.mugun,R.drawable.kong,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.kong,R.drawable.mugun));
		things.add(new Thing("木栅栏","mzl",R.drawable.muzhalan,1,1,"搭建牲畜窝、装饰品,地狱砖亦可","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun));
		things.add(new Thing("船","c",R.drawable.chuan,1,1,"水中行驶","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("木台阶","mtj",R.drawable.mutaijie,1,1,"石头|沙石|圆石|砖块|石砖|地狱砖|石英块","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("告示牌","gsp",R.drawable.gaoshipai,1,1,"门牌、路标、贴在楼梯两侧可组成沙发","14",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("木门","mm",R.drawable.mumeng,1,1,"手、红石系统可打开","7",R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("铁门","tm",R.drawable.tiemeng,1,1,"只能用红石系统打开","7",R.drawable.tieding,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.muban,R.drawable.tieding,R.drawable.tieding,R.drawable.kong));
		things.add(new Thing("萤石块","ysk",R.drawable.yingshikuai,1,1,"水下照明,地狱可挖到","13",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yingshifeng,R.drawable.yingshifeng,R.drawable.kong,R.drawable.yingshifeng,R.drawable.yingshifeng,R.drawable.kong));
		things.add(new Thing("雪块","xk",R.drawable.xuekuai,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xueqiu,R.drawable.xueqiu,R.drawable.kong,R.drawable.xueqiu,R.drawable.xueqiu,R.drawable.kong));
		things.add(new Thing("TNT","tnt",R.drawable.tnt,1,1,"爆炸物","21",R.drawable.huoyao,R.drawable.shazi,R.drawable.huoyao,R.drawable.shazi,R.drawable.huoyao,R.drawable.shazi,R.drawable.huoyao,R.drawable.shazi,R.drawable.huoyao));
		things.add(new Thing("粘土块","ntk",R.drawable.niantukuai,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.niantu,R.drawable.niantu,R.drawable.kong,R.drawable.niantu,R.drawable.niantu,R.drawable.kong));
		things.add(new Thing("砖块","zk",R.drawable.zhuankuai,1,1,"坚硬的建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.hongzhuan,R.drawable.hongzhuan,R.drawable.kong,R.drawable.hongzhuan,R.drawable.hongzhuan,R.drawable.kong));
		things.add(new Thing("书架","sj",R.drawable.shujia,1,1,"装饰和附魔","14",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.shubeng,R.drawable.shubeng,R.drawable.shubeng,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("沙石","ss",R.drawable.shashi,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.shazi,R.drawable.shazi,R.drawable.kong,R.drawable.shazi,R.drawable.shazi,R.drawable.kong));
		things.add(new Thing("平滑沙石","phss",R.drawable.pinghuashashi,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.shashi,R.drawable.shashi,R.drawable.kong,R.drawable.shashi,R.drawable.shashi,R.drawable.kong));
		things.add(new Thing("音符盒","yfh",R.drawable.yingfuhe,1,1,"右击发出音乐","14",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.hongshi,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("南瓜灯","ngd",R.drawable.nanguadeng,1,1,"照明","13",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.nangua,R.drawable.kong,R.drawable.kong,R.drawable.huoba,R.drawable.kong));		
		things.add(new Thing("青金石块","qjsk",R.drawable.qingjingshikuai,1,1,"存储青金石块，建筑材料","14",R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao,R.drawable.qingjingshiranliao));
		things.add(new Thing("钻石块","zsk",R.drawable.zuanshikuai,1,1,"存储钻石，建筑材料","14",R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi));
		things.add(new Thing("金块","jk",R.drawable.jingkuai,1,1,"存储金锭，建筑材料","14",R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.jingding));
		things.add(new Thing("铁块","tk",R.drawable.tiekuai,1,1,"存储铁锭，建筑材料","14",R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding));
		things.add(new Thing("绿宝石块","lbsk",R.drawable.lvbaoshikuai,1,1,"存储绿宝石，建筑材料","14",R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi,R.drawable.lvbaoshi));
		things.add(new Thing("石砖","sz",R.drawable.shizhuan,1,1,"建筑材料","14",R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou));
		things.add(new Thing("木楼梯","mlt",R.drawable.mulouti,1,1,"石头|砖块|石砖|地狱砖|沙石|石英","7",R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("圆石墙","ysq",R.drawable.yuanshiqiang,1,1,"栅栏，苔石亦可","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi));
		things.add(new Thing("红石块","hsk",R.drawable.hongshikuai,1,1,"红石电源","11",R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi,R.drawable.hongshi));
		things.add(new Thing("地狱砖","dyz",R.drawable.diyuzhuan,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.diyuzhuan,R.drawable.diyuzhuan,R.drawable.kong,R.drawable.diyuzhuan,R.drawable.diyuzhuan,R.drawable.kong));
		things.add(new Thing("石英块","syk",R.drawable.shiyingkuai,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiajieshiying,R.drawable.xiajieshiying,R.drawable.kong,R.drawable.xiajieshiying,R.drawable.xiajieshiying,R.drawable.kong));
		things.add(new Thing("竖纹石英块","swsyk",R.drawable.shuwengshiyingkuai,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.shiyingkuai,R.drawable.kong,R.drawable.kong,R.drawable.shiyingkuai,R.drawable.kong));
		things.add(new Thing("木镐","mg",R.drawable.mugao,1,1,"可以挖石头及硬度更低物品","21",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("石镐","sg",R.drawable.shigao,1,1,"可以挖铁矿及硬度更低物品","21",R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("铁镐","tg",R.drawable.tiegao,1,1,"可以挖钻石及硬度更低物品","21",R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("金镐","jg",R.drawable.jinggao,1,1,"易附魔，耐久低于铁镐","21",R.drawable.jingding,R.drawable.jingding,R.drawable.jingding,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("钻石镐","zsg",R.drawable.zuanshigao,1,1,"除了基岩都可以挖","21",R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.zuanshi,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("木斧头","mft",R.drawable.mufutou,1,1,"砍树，圆石|铁锭|金锭|钻石","21",R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));		
		things.add(new Thing("木锹","mq",R.drawable.muqiao,1,1,"可铲雪，圆石|铁锭|金锭|钻石","21",R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("木锄头","mct",R.drawable.muqiao,1,1,"播种，圆石|铁锭|金锭|钻石","21",R.drawable.muban,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("钓鱼竿","dyg",R.drawable.diaoyugan,1,1,"钓鱼","21",R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.mugun,R.drawable.xian,R.drawable.mugun,R.drawable.kong,R.drawable.xian));
		things.add(new Thing("打火石","dhs",R.drawable.dahuoshi,1,1,"生火","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.suishi,R.drawable.kong));
		things.add(new Thing("指南针","znz",R.drawable.zhinanzheng,1,1,"指向出生点","21",R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.hongshi,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.kong));
		things.add(new Thing("钟","z",R.drawable.zhong,1,1,"显示时间和白天黑夜","21",R.drawable.kong,R.drawable.jingding,R.drawable.kong,R.drawable.jingding,R.drawable.hongshi,R.drawable.jingding,R.drawable.kong,R.drawable.jingding,R.drawable.kong));
		things.add(new Thing("铁通","tt",R.drawable.tietong,1,1,"装水、牛奶、岩浆","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.kong));
		things.add(new Thing("剪刀","jd",R.drawable.jiandao,1,1,"剪树叶、羊毛","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("皮革头盔","pgtk",R.drawable.pigetoukui,1,1,"提供护甲，铁锭|金锭|钻石","22",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.kong,R.drawable.pige));
		things.add(new Thing("皮革胸甲","pgxj",R.drawable.pigexiongjia,1,1,"提供护甲，铁锭|金锭|钻石","22",R.drawable.pige,R.drawable.kong,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige));
		things.add(new Thing("皮革护腿","pght",R.drawable.pigehutui,1,1,"提供护甲，铁锭|金锭|钻石","22",R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.pige,R.drawable.kong,R.drawable.pige,R.drawable.pige,R.drawable.kong,R.drawable.pige));
		things.add(new Thing("皮革靴子","pgxz",R.drawable.pigexuezi,1,1,"提供护甲，铁锭|金锭|钻石","22",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.pige,R.drawable.kong,R.drawable.pige,R.drawable.pige,R.drawable.kong,R.drawable.pige));
		things.add(new Thing("木剑","mj",R.drawable.mujian,1,1,"造成伤害，铁锭|金锭|钻石","22",R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("弓","g",R.drawable.gong,1,1,"射箭","22",R.drawable.kong,R.drawable.mugun,R.drawable.xian,R.drawable.mugun,R.drawable.kong,R.drawable.xian,R.drawable.kong,R.drawable.mugun,R.drawable.xian));
		things.add(new Thing("箭","j",R.drawable.jian_gong,1,1,"用弓射出造成伤害","22",R.drawable.kong,R.drawable.suishi,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.yumao,R.drawable.kong));
		things.add(new Thing("木压力板","mylb",R.drawable.muyaliban,1,1,"受力发出红石信号，石头亦可","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("活板门","hbm",R.drawable.huobanmeng,1,1,"右键或红石信号打开","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("木栅栏门","mzlm",R.drawable.muzhalanmeng,1,1,"右键打开","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.muban,R.drawable.mugun,R.drawable.mugun,R.drawable.muban,R.drawable.mugun));
		things.add(new Thing("木按钮","man",R.drawable.muanniu,1,1,"右键发送红石信号，石头亦可","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("拉杆","mlg",R.drawable.lagan,1,1,"右键发送红石信号","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.yuanshi,R.drawable.kong));
		things.add(new Thing("中继器","zjq",R.drawable.zhongjiqi,1,1,"红石电路的二极管或延时器","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.hongshihuoba,R.drawable.hongshi,R.drawable.hongshihuoba,R.drawable.shitou,R.drawable.shitou,R.drawable.shitou));
		things.add(new Thing("红石火把","hshb",R.drawable.hongshihuoba,1,1,"不间断发送红石信号","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.hongshi,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong));
		things.add(new Thing("唱片机","cpj",R.drawable.changpianji,1,1,"放入CD播放唱片","14",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.zuanshi,R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("发射器","fsq",R.drawable.fasheqi,1,1,"装入物品，红石信号激活发射","24",R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.gong,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.hongshi,R.drawable.yuanshi));
		things.add(new Thing("活塞","hs",R.drawable.huosai,1,1,"装入物品，红石信号激活发射","24",R.drawable.muban,R.drawable.muban,R.drawable.muban,R.drawable.yuanshi,R.drawable.tieding,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.hongshi,R.drawable.yuanshi));
		things.add(new Thing("粘性活塞","nxhs",R.drawable.nianxinghuosai,1,1,"装入物品，红石信号激活发射","24",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.nianyeqiu,R.drawable.kong,R.drawable.kong,R.drawable.huosai,R.drawable.kong));
		things.add(new Thing("矿车","kc",R.drawable.kuangche,1,1,"在铁轨上行驶，右键可乘坐","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding));
		things.add(new Thing("动力矿车","dlkc",R.drawable.donglikuangche,1,1,"烧炭产生能量提供动力","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.ronglu,R.drawable.kong,R.drawable.kong,R.drawable.kuangche,R.drawable.kong));
		things.add(new Thing("运输矿车","yskc",R.drawable.yunshukuangche,1,1,"使矿车能运输物品","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiangzi,R.drawable.kong,R.drawable.kong,R.drawable.kuangche,R.drawable.kong));
		things.add(new Thing("铁轨","tg",R.drawable.tiegui,1,1,"矿车行驶轨道","7",R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.mugun,R.drawable.tieding,R.drawable.tieding,R.drawable.kong,R.drawable.tieding));
		things.add(new Thing("充能铁轨","cntg",R.drawable.chongnengtiegui,1,1,"红石信号激活可使矿车加速","24",R.drawable.jingding,R.drawable.kong,R.drawable.jingding,R.drawable.jingding,R.drawable.mugun,R.drawable.jingding,R.drawable.jingding,R.drawable.hongshi,R.drawable.jingding));
		things.add(new Thing("探测铁轨","tctg",R.drawable.tancetiegui,1,1,"矿车经过产生红石信号","11",R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.shiyaliban,R.drawable.tieding,R.drawable.tieding,R.drawable.hongshi,R.drawable.tieding));
		things.add(new Thing("红石灯","hsd",R.drawable.hongshideng,1,1,"红石信号激活照明","11",R.drawable.kong,R.drawable.hongshi,R.drawable.kong,R.drawable.hongshi,R.drawable.yingshikuai,R.drawable.hongshi,R.drawable.kong,R.drawable.hongshi,R.drawable.kong));
		things.add(new Thing("拌线","bx",R.drawable.banxian,1,1,"通过时产生红石信号","11",R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.kong,R.drawable.mugun,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("激活铁轨","jhtg",R.drawable.jihuotiegui,1,1,"常用来激活TNT矿车和漏斗矿车","11",R.drawable.tieding,R.drawable.mugun,R.drawable.tieding,R.drawable.tieding,R.drawable.hongshihuoba,R.drawable.tieding,R.drawable.tieding,R.drawable.mugun,R.drawable.tieding));
		things.add(new Thing("阳光传感器","ygcgq",R.drawable.yangguangchuanganqi,1,1,"有阳光时发射红石信号","11",R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.xiajieshiying,R.drawable.xiajieshiying,R.drawable.xiajieshiying,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("投掷器","tzq",R.drawable.touzhiqi,1,1,"扔出物品，类似发射","24",R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.kong,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.hongshi,R.drawable.yuanshi));
		things.add(new Thing("漏斗","ld",R.drawable.loudou,1,1,"和箱子搭配,吸收上分物品到箱子","21",R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.xiangzi,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.kong));
		things.add(new Thing("漏斗矿车","ldkc",R.drawable.loudoukuangche,1,1,"自动吸取铁轨上的物品以及位于铁轨上方的容器贮存的物品","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.loudou,R.drawable.kong,R.drawable.kong,R.drawable.kuangche,R.drawable.kong));
		things.add(new Thing("TNT矿车","tntkc",R.drawable.tntkuangche,1,1,"收到红石信号爆炸","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tnt,R.drawable.kong,R.drawable.kong,R.drawable.kuangche,R.drawable.kong));
		things.add(new Thing("红石比较器","hsbjq",R.drawable.hongshibijiaoqi,1,1,"红石电路中使用","24",R.drawable.kong,R.drawable.hongshihuoba,R.drawable.kong,R.drawable.hongshihuoba,R.drawable.xiajieshiying,R.drawable.hongshihuoba,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi));
		things.add(new Thing("陷进箱","xjx",R.drawable.xianjingxiang,1,1,"红石电路中使用","11",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiangzi,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("铁测重压力板","tczylb",R.drawable.tiecezhongyaliban,1,1,"放置物品激活产生红石信号","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.loudou,R.drawable.kong,R.drawable.kong,R.drawable.kuangche,R.drawable.kong));
		things.add(new Thing("碗","w",R.drawable.wan,1,1,"装蘑菇煲","12",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.kong,R.drawable.muban,R.drawable.kong));
		things.add(new Thing("蘑菇煲","mgb",R.drawable.mogubao,1,1,"恢复4点饥饿值","12",R.drawable.kong,R.drawable.hongmogu,R.drawable.kong,R.drawable.kong,R.drawable.zongsemogu,R.drawable.kong,R.drawable.kong,R.drawable.wan,R.drawable.kong));
		things.add(new Thing("面包","mb",R.drawable.mianbao,1,1,"恢复2.5饥饿值","12",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiaomai,R.drawable.xiaomai,R.drawable.xiaomai));
		things.add(new Thing("金苹果","jpg",R.drawable.jingpingguo,1,1,"恢复2点饥饿值","12",R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.pingguo,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli));
		things.add(new Thing("附魔金苹果","fmjpg",R.drawable.fumojingpingguo,1,1,"获得2点饥饿值、30秒抗火","12",R.drawable.jingkuai,R.drawable.jingkuai,R.drawable.jingkuai,R.drawable.jingkuai,R.drawable.pingguo,R.drawable.jingkuai,R.drawable.jingkuai,R.drawable.jingkuai,R.drawable.jingkuai));
		things.add(new Thing("糖","t",R.drawable.tang,1,1,"用来制作蛋糕","12",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.ganzhe,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("蛋糕","dg",R.drawable.dangao,1,1,"恢复1.5饥饿值","12",R.drawable.niunantong,R.drawable.niunantong,R.drawable.niunantong,R.drawable.tang,R.drawable.jidan,R.drawable.tang,R.drawable.xiaomai,R.drawable.xiaomai,R.drawable.xiaomai));
		things.add(new Thing("曲奇","qq",R.drawable.quqi,1,1,"恢复1饥饿值","12",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiaomai,R.drawable.kekedou,R.drawable.xiaomai));
		things.add(new Thing("西瓜","xg",R.drawable.xigua,1,1,"西瓜","12",R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian,R.drawable.xiguapian));
		things.add(new Thing("西瓜种子","xgzz",R.drawable.xiguazhongzi,1,1,"种植在耕地上","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiguapian,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("南瓜种子","ngzz",R.drawable.nanguazhongzi,1,1,"种植在耕地上","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.nangua,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("金胡萝卜","jhlb",R.drawable.jinghuluobo,1,1,"恢复3点饥饿值，酿造原料","23",R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.huluobo,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli));
		things.add(new Thing("南瓜派","ngp",R.drawable.nanguapai,1,1,"恢复4点饥饿值","12",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.nangua,R.drawable.tang,R.drawable.kong,R.drawable.jidan,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("床","c",R.drawable.chuang,1,1,"过夜，躲过夜晚","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.yangmao,R.drawable.yangmao,R.drawable.muban,R.drawable.muban,R.drawable.muban));
		things.add(new Thing("画","h",R.drawable.hua,1,1,"装饰","14",R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.yangmao,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun));
		things.add(new Thing("纸","z",R.drawable.zhi,1,1,"制造地图和书本","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.ganzhe,R.drawable.ganzhe,R.drawable.ganzhe));
		things.add(new Thing("书本","s",R.drawable.shubeng,1,1,"制造书架、‘书与笔’、附魔台","7",R.drawable.kong,R.drawable.zhi,R.drawable.kong,R.drawable.kong,R.drawable.zhi,R.drawable.kong,R.drawable.kong,R.drawable.zhi,R.drawable.pige));
		things.add(new Thing("书与笔","syb",R.drawable.shuyubi,1,1,"写文章","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yumao,R.drawable.kong,R.drawable.kong,R.drawable.monang,R.drawable.shubeng,R.drawable.kong));
		things.add(new Thing("地图","dt",R.drawable.ditu,1,1,"显示附近区域地图","21",R.drawable.zhi,R.drawable.zhi,R.drawable.zhi,R.drawable.zhi,R.drawable.zhinanzheng,R.drawable.zhi,R.drawable.zhi,R.drawable.zhi,R.drawable.zhi));
		things.add(new Thing("玻璃板","blb",R.drawable.boliban,1,1,"类似栅栏的窗格玻璃","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.boli));
		things.add(new Thing("铁栏杆","tlg",R.drawable.tielangan,1,1,"类似栅栏但只有1个方块高度","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding));
		things.add(new Thing("金锭","jd",R.drawable.jingding,1,1,"制造金物品","7",R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli,R.drawable.jingli));
		things.add(new Thing("末影之眼","myzy",R.drawable.moyingzhiyan,1,1,"定位末地传送门","24",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.moyingzhengzhu,R.drawable.kong,R.drawable.kong,R.drawable.lieyanfeng,R.drawable.kong));
		things.add(new Thing("附魔台","fmt",R.drawable.fumotai,1,1,"附魔工具","24",R.drawable.kong,R.drawable.shubeng,R.drawable.kong,R.drawable.zuanshi,R.drawable.heiyaoshi,R.drawable.zuanshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi));
		things.add(new Thing("火焰弹","hyd",R.drawable.huoyandan,1,1,"可发射","22",R.drawable.kong,R.drawable.lieyanfeng,R.drawable.kong,R.drawable.kong,R.drawable.meitan,R.drawable.kong,R.drawable.kong,R.drawable.huoyao,R.drawable.kong));
		things.add(new Thing("末影箱","myx",R.drawable.moyingxiang,1,1,"用于存储物品，所有末影箱共享","24",R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.moyingzhiyan,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi));
		things.add(new Thing("信标","xb",R.drawable.xingbiao,1,1,"可向天空直射光束","24",R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.boli,R.drawable.xiajiezhixing,R.drawable.boli,R.drawable.heiyaoshi,R.drawable.heiyaoshi,R.drawable.heiyaoshi));
		things.add(new Thing("铁毡","tz",R.drawable.tiezhan,1,1,"修复物品、合并附魔、重命名物品","24",R.drawable.tiekuai,R.drawable.tiekuai,R.drawable.tiekuai,R.drawable.kong,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding));
		things.add(new Thing("花盆","hp",R.drawable.huapeng,1,1,"装饰","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.hongzhuan,R.drawable.kong,R.drawable.hongzhuan,R.drawable.kong,R.drawable.hongzhuan,R.drawable.pige));
		things.add(new Thing("物品展示框","wpzsk",R.drawable.wupingzhanshikuang,1,1,"放置墙上展示物品","14",R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.pige,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun,R.drawable.mugun));
		things.add(new Thing("萝卜钓竿","lbdg",R.drawable.luobodiaogan,1,1,"控制猪","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.diaoyugan,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.huluobo,R.drawable.kong));
		things.add(new Thing("骨粉","gf",R.drawable.gufeng,1,1,"加速农作物成长、制作染料","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.gutou,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("淡灰色染料","dhsrl",R.drawable.danhuiseranliao,1,1,"制作淡灰色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.gufeng,R.drawable.monang,R.drawable.gufeng,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("灰色染料","hsrl",R.drawable.huiseranliao,1,1,"制作灰色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.gufeng,R.drawable.kong,R.drawable.monang,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("玫瑰红","mgh",R.drawable.meiguihong,1,1,"制作红色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.meiguihua,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("橙色染料","csrl",R.drawable.chengseranliao,1,1,"制作橙色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.pugongyinghuang,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("蒲公英黄","pgyh",R.drawable.pugongyinghuang,1,1,"制作黄色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.pugongying,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("黄绿色染料","hlsrl",R.drawable.huanglvseranliao,1,1,"制作黄绿色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xianrengzhanglv,R.drawable.kong,R.drawable.gufeng,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("淡蓝色染料","dlsrl",R.drawable.danlanseranliao,1,1,"制作淡蓝色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.qingjingshiranliao,R.drawable.kong,R.drawable.gufeng,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("青色染料","qsrl",R.drawable.qingseranliao,1,1,"制作青色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xianrengzhanglv,R.drawable.kong,R.drawable.qingjingshiranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("紫色染料","zsrl",R.drawable.ziseranliao,1,1,"制作紫色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.qingjingshiranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("品红色染料","phsrl",R.drawable.pinghongseranliao,1,1,"制作品红色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.fenghongseranliao,R.drawable.kong,R.drawable.ziseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("粉红色染料","fhsrl",R.drawable.fenghongseranliao,1,1,"制作粉红色羊毛","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.gufeng,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("羊毛","ym",R.drawable.yangmao,1,1,"建筑材料、可被染色","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xian,R.drawable.xian,R.drawable.kong,R.drawable.xian,R.drawable.xian,R.drawable.kong));
		things.add(new Thing("淡灰色羊毛","dhsym",R.drawable.danhuiseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.danhuiseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("灰色羊毛","hsym",R.drawable.huiseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.huiseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("黑色羊毛","hsym",R.drawable.heiseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.monang,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("红色羊毛","hsym",R.drawable.hongseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.meiguihong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("橙色羊毛","csym",R.drawable.chengseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.chengseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("黄色羊毛","hsym",R.drawable.huangseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.pugongyinghuang,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("黄绿色羊毛","hlsym",R.drawable.huanglvseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.huanglvseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("绿色羊毛","lsym",R.drawable.lvseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.xianrengzhanglv,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("淡蓝色羊毛","dlsym",R.drawable.danlanseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.danlanseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("青色羊毛","qsym",R.drawable.qingseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.qingseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("蓝色羊毛","lsym",R.drawable.lanseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.qingjingshiranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("紫色羊毛","zsym",R.drawable.ziseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.ziseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("品红色羊毛","phsym",R.drawable.pinghongseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.pinghongseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("粉红色羊毛","fhsym",R.drawable.fenghongseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.fenghongseranliao,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("棕色羊毛","zsym",R.drawable.zongseyangmao,1,1,"建筑材料","14",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.yangmao,R.drawable.kong,R.drawable.kekedou,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("玻璃瓶","blp",R.drawable.boliping,1,1,"用于酿造","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.boli,R.drawable.kong,R.drawable.boli,R.drawable.kong,R.drawable.boli,R.drawable.kong));
		things.add(new Thing("炼药锅","lyg",R.drawable.lianyaoguo,1,1,"填满玻璃瓶的水","21",R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.kong,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding,R.drawable.tieding));
		things.add(new Thing("酿造台","nzt",R.drawable.niangzaotai,1,1,"用于酿造","21",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.lieyanbang,R.drawable.kong,R.drawable.yuanshi,R.drawable.yuanshi,R.drawable.yuanshi));
		things.add(new Thing("烈焰粉","lyf",R.drawable.lieyanfeng,1,1,"制造末影眼、岩浆膏","23",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.lieyanbang,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("研究膏","yjg",R.drawable.yanjiugao,1,1,"用于药剂制作","23",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.nianyeqiu,R.drawable.kong,R.drawable.kong,R.drawable.lieyanfeng,R.drawable.kong));
		things.add(new Thing("发酵蜘蛛眼","fjzzy",R.drawable.fajiaozhizhuyan,1,1,"用于药剂制作","23",R.drawable.zhizhuyan,R.drawable.kong,R.drawable.kong,R.drawable.zongsemogu,R.drawable.tang,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		things.add(new Thing("闪烁的西瓜","ssdxg",R.drawable.shanshuodexigua,1,1,"用于药剂制作","23",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.xiguapian,R.drawable.jingli,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));

		
		return things; 
	}
	
	public List<Thing> getThings_update(){
		List<Thing> things = new ArrayList<Thing>();
		//新增物品添加
		//things.add(new Thing("7gaw","7gaw",R.drawable.kong,1,1,"作者","7",R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong,R.drawable.kong));
		
		return things;
	}
	
	public List<Thing> Things_xiugai(){
		List<Thing> things = new ArrayList<Thing>();
		//需要修改的物品添加
		
		return things;
	}
	
	public List<Thing> Things_shanchu(){
		List<Thing> things = new ArrayList<Thing>();
		//删除物品添加
		//只需填写THING.name这一项，其他为空;
		return things;
	}
}
