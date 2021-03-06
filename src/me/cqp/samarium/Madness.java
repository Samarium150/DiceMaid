package me.cqp.samarium;

import java.util.concurrent.ThreadLocalRandom;

final class Madness{
	private static Madness instance;
	private String[] MadA;
	private String[] MadB;
	private String[] Phobia;
	private String[] Mania;
	
	static Madness getInstance() {
		if (instance==null) instance = new Madness();
		return instance;
	}
	
	private Madness() {
		MadA = new String[]{
				"失忆：调查员会发现自己只记得最后身处的安全地点，却没有任何来到这里的记忆。例如，调查员前一刻还在家中吃着早饭，下一刻就已经直面着不知名的怪物。",
				"假性残疾：调查员陷入了心理性的失明，失聪以及躯体缺失感中。",
				"暴力倾向：调查员陷入了六亲不认的暴力行为中，对周围的敌人与友方进行着无差别的攻击。",
				"偏执：调查员陷入了严重的偏执妄想之中，有人在暗中窥视着他们，同伴中有人背叛了他们，没有人可以信任，万事皆虚。",
				"人际依赖：守秘人适当参考调查员的背景中重要之人的条目，调查员因为一些原因而降他人误认为了他重要的人并且努力的会与那个人保持那种关系。",
				"昏厥：调查员当场昏倒。",
				"逃避行为：调查员会用任何的手段试图逃离现在所处的位置，即使这意味着开走唯一一辆交通工具并将其它人抛诸脑后。",
				"竭嘶底里：调查员表现出大笑，哭泣，嘶吼，害怕等的极端情绪表现。",
				"恐惧症：",
				"狂躁症：",
		};
		MadB = new String[]{
				"失忆：回过神来，调查员们发现自己身处一个陌生的地方，并忘记了自己是谁。记忆会随时间恢复。",
				"被窃：数小时后恢复清醒，发觉自己被盗，身体毫发无损。如果调查员携带着宝贵之物(见调查员背景)，做幸运检定来决定其是否被盗。所有有价值的东西无需检定自动消失。",
				"遍体鳞伤：数小时后恢复清醒，发现自己身上满是拳痕和瘀伤。生命值减少到疯狂前的一半，但这不会造成重伤。调查员没有被窃。这种伤害如何持续到现在由守秘人决定。",
				"暴力倾向：调查员陷入强烈的暴力与破坏欲之中。调查员回过神来可能会理解自己做了什么也可能毫无印象。调查员对谁或何物施以暴力，他们是杀人还是仅仅造成了伤害，由守秘人决定。",
				"极端信念：查看调查员背景中的思想信念，调查员会采取极端和疯狂的表现手段展示他们的思想信念之一。比如一个信教者会在地铁上高声布道。",
				"重要之人：数小时或更久的时间中，调查员将不顾一切地接近那个人，并为他们之间的关系做出行动。",
				"被收容：调查员在精神病院病房或警察局牢房中回过神来，他们可能会慢慢回想起导致自己被关在这里的事情。",
				"逃避行为：调查员恢复清醒时发现自己在很远的地方，也许迷失在荒郊野岭，或是在驶向远方的列车或长途汽车上。",
				"恐惧症：调查员患上一个新的恐惧症。",
				"狂躁：调查员患上一个新的狂躁症。"
		};
		Phobia = new String[]{
				"洗澡恐惧症：对于洗涤或洗澡的恐惧。",
				"恐高症：对于身处高处的恐惧。",
				"飞行恐惧症：对飞行的恐惧。",
				"广场恐惧症：对于开放的(拥挤)公共场所的恐惧。",
				"恐鸡症：对鸡的恐惧。",
				"大蒜恐惧症：对大蒜的恐惧。",
				"乘车恐惧症：对于乘坐地面载具的恐惧。",
				"恐风症：对风的恐惧。",
				"男性恐惧症：对于成年男性的恐惧。",
				"恐英症：对英格兰或英格兰文化的恐惧。",
				"恐花症：对花的恐惧。",
				"截肢者恐惧症：对截肢者的恐惧。",
				"蜘蛛恐惧症：对蜘蛛的恐惧。",
				"闪电恐惧症：对闪电的恐惧。",
				"废墟恐惧症：对遗迹或残址的恐惧。",
				"长笛恐惧症：对长笛的恐惧。",
				"细菌恐惧症：对细菌的恐惧。",
				"导弹/子弹恐惧症：对导弹或子弹的恐惧。",
				"跌落恐惧症：对于跌倒或摔落的恐惧。",
				"书籍恐惧症：对书籍的恐惧。",
				"植物恐惧症：对植物的恐惧。",
				"美女恐惧症：对美貌女性的恐惧。",
				"寒冷恐惧症：对寒冷的恐惧。",
				"恐钟表症：对于钟表的恐惧。",
				"幽闭恐惧症：对于处在封闭的空间中的恐惧。",
				"小丑恐惧症：对小丑的恐惧。",
				"恐犬症：对狗的恐惧。",
				"恶魔恐惧症：对邪灵或恶魔的恐惧。",
				"人群恐惧症：对人群的恐惧。",
				"牙科恐惧症：对牙医的恐惧。",
				"丢弃恐惧症：对于丢弃物件的恐惧(贮藏癖)。",
				"皮毛恐惧症：对动物皮毛的恐惧。",
				"过马路恐惧症：对于过马路的恐惧。",
				"教堂恐惧症：对教堂的恐惧。",
				"镜子恐惧症：对镜子的恐惧。",
				"针尖恐惧症：对针或大头针的恐惧。",
				"昆虫恐惧症：对昆虫的恐惧。",
				"恐猫症：对猫的恐惧。",
				"过桥恐惧症：对于过桥的恐惧。",
				"恐老症：对于老年人或变老的恐惧。",
				"恐女症：对女性的恐惧。",
				"恐血症：对血的恐惧。",
				"宗教罪行恐惧症：对宗教罪行的恐惧。",
				"触摸恐惧症：对于被触摸的恐惧。",
				"爬虫恐惧症：对爬行动物的恐惧。",
				"迷雾恐惧症：对雾的恐惧。",
				"火器恐惧症：对火器的恐惧。",
				"恐水症：对水的恐惧。",
				"催眠恐惧症：对于睡眠或被催眠的恐惧。",
				"白袍恐惧症：对医生的恐惧。",
				"鱼类恐惧症：对鱼的恐惧。",
				"蟑螂恐惧症：对蟑螂的恐惧。",
				"雷鸣恐惧症：对雷声的恐惧。",
				"蔬菜恐惧症：对蔬菜的恐惧。",
				"噪音恐惧症：对刺耳噪音的恐惧。",
				"恐湖症：对湖泊的恐惧。",
				"机械恐惧症：对机器或机械的恐惧。",
				"巨物恐惧症：对于庞大物件的恐惧。",
				"捆绑恐惧症：对于被捆绑或紧缚的恐惧。",
				"流星恐惧症：对流星或陨石的恐惧。",
				"孤独恐惧症：对于一人独处的恐惧。",
				"不洁恐惧症：对污垢或污染的恐惧。",
				"黏液恐惧症：对黏液(史莱姆)的恐惧。",
				"尸体恐惧症：对尸体的恐惧。",
				"数字8恐惧症：对数字8的恐惧。",
				"恐牙症：对牙齿的恐惧。",
				"恐梦症：对梦境的恐惧。",
				"称呼恐惧症：对于特定词语的恐惧。",
				"恐蛇症：对蛇的恐惧。",
				"恐鸟症：对鸟的恐惧。",
				"寄生虫恐惧症：对寄生虫的恐惧。",
				"人偶恐惧症：对人偶的恐惧。",
				"吞咽恐惧症：对于吞咽或被吞咽的恐惧。",
				"药物恐惧症：对药物的恐惧。",
				"幽灵恐惧症：对鬼魂的恐惧。",
				"日光恐惧症：对日光的恐惧。",
				"胡须恐惧症：对胡须的恐惧。",
				"河流恐惧症：对河流的恐惧。",
				"酒精恐惧症：对酒或酒精的恐惧。",
				"恐火症：对火的恐惧。",
				"魔法恐惧症：对魔法的恐惧。",
				"黑暗恐惧症：对黑暗或夜晚的恐惧。",
				"恐月症：对月亮的恐惧。",
				"火车恐惧症：对于乘坐火车出行的恐惧。",
				"恐星症：对星星的恐惧。",
				"狭室恐惧症：对狭小物件或地点的恐惧。",
				"对称恐惧症：对对称的恐惧。",
				"活埋恐惧症：对于被活埋或墓地的恐惧。",
				"公牛恐惧症：对公牛的恐惧。",
				"电话恐惧症：对电话的恐惧。",
				"怪物恐惧症：对怪物的恐惧。",
				"深海恐惧症：对海洋的恐惧。",
				"手术恐惧症：对外科手术的恐惧。",
				"十三恐惧症：对数字13的恐惧症。",
				"衣物恐惧症：对衣物的恐惧。",
				"女巫恐惧症：对女巫与巫术的恐惧。",
				"黄色恐惧症：对黄色或“黄”字的恐惧。",
				"外语恐惧症：对外语的恐惧。",
				"异域恐惧症：对陌生人或外国人的恐惧。",
				"动物恐惧症：对动物的恐惧。",
		};
		Mania = new String[]{
				"沐浴癖：执着于清洗自己。",
				"犹豫癖：病态地犹豫不定。",
				"喜暗狂：对黑暗的过度热爱。",
				"喜高狂：狂热迷恋高处。",
				"亲切癖：病态地对他人友好。",
				"喜旷症：强烈地倾向于待在开阔空间中。",
				"喜尖狂：痴迷于尖锐或锋利的物体。",
				"恋猫狂：近乎病态地对猫友善。",
				"疼痛癖：痴迷于疼痛。",
				"喜蒜狂：痴迷于大蒜。",
				"乘车癖：痴迷于乘坐车辆。",
				"欣快癖：不正常地感到喜悦。",
				"喜花狂：痴迷于花朵。",
				"计算癖：狂热地痴迷于数字。",
				"消费癖：鲁莽冲动地消费。",
				"隐居癖：过度地热爱独自隐居。",
				"芭蕾癖：痴迷于芭蕾舞。",
				"窃书癖：无法克制偷窃书籍的冲动。",
				"恋书狂：痴迷于书籍和/或阅读",
				"磨牙癖：无法克制磨牙的冲动。",
				"灵臆症：病态地坚信自己已被一个邪恶的灵体占据。",
				"美貌狂：痴迷于自身的美貌。",
				"地图狂：在何时何处都无法控制查阅地图的冲动。",
				"跳跃狂：痴迷于从高处跳下。",
				"喜冷症：对寒冷或寒冷的物体的反常喜爱。",
				"舞蹈狂：无法控制地起舞或发颤。",
				"恋床癖：过度地热爱待在床上。",
				"恋墓狂：痴迷于墓地。",
				"色彩狂：痴迷于某种颜色。",
				"小丑狂：痴迷于小丑。",
				"恐惧狂：执着于经历恐怖的场面。",
				"杀戮癖：痴迷于杀戮。",
				"魔臆症：病态地坚信自己已被恶魔附身。",
				"抓挠癖：执着于抓挠自己的皮肤。",
				"正义狂：痴迷于目睹正义被伸张。",
				"嗜酒狂：反常地渴求酒精。",
				"毛皮狂：痴迷于拥有毛皮。",
				"赠物癖：痴迷于赠送礼物。",
				"漂泊症：执着于逃离。",
				"漫游癖：执着于四处漫游。",
				"自恋狂：近乎病态地以自我为中心或自我崇拜。",
				"职业狂：对于工作的无尽病态渴求。",
				"臆罪症：病态地坚信自己带有罪孽。",
				"学识狂：痴迷于获取学识。",
				"静止癖：执着于保持安静。",
				"乙醚上瘾：渴求乙醚。",
				"求婚狂：痴迷于进行奇特的求婚。",
				"狂笑癖：无法自制地，强迫性的大笑。",
				"巫术狂：痴迷于女巫与巫术。",
				"写作癖：痴迷于将每一件事写下来。",
				"裸体狂：执着于裸露身体。",
				"妄想狂：近乎病态地充满愉快的妄想，而不顾现实状况如何。",
				"蠕虫狂：过度地喜爱蠕虫。",
				"枪械狂：痴迷于火器。",
				"饮水狂：反常地渴求水分。",
				"喜鱼癖：痴迷于鱼类。",
				"图标狂：痴迷于图标与肖像",
				"偶像狂：痴迷于甚至愿献身于某个偶像。",
				"信息狂：痴迷于积累各种信息与资讯。",
				"射击狂：反常地执着于射击。",
				"偷窃癖：反常地执着于偷窃。",
				"噪音癖：无法自制地执着于制造响亮或刺耳的噪音。",
				"喜线癖：痴迷于线绳。",
				"彩票狂：极端地执着于购买彩票。",
				"抑郁症：近乎病态的重度抑郁倾向。",
				"巨石狂：当站在石环中或立起的巨石旁时，就会近乎病态地写出各种奇怪的创意。",
				"旋律狂：痴迷于音乐或一段特定的旋律。",
				"作诗癖：无法抑制地想要不停作诗。",
				"憎恨癖：憎恨一切事物，痴迷于憎恨某个事物或团体。",
				"偏执狂：近乎病态地痴迷与专注某个特定的想法或创意。",
				"夸大癖：以一种近乎病态的程度说谎或夸大事物。",
				"臆想症：妄想自己正在被某种臆想出的疾病折磨。",
				"记录癖：执着于记录一切事物，例如摄影",
				"恋名狂：痴迷于名字，包括人物的、地点的和事物的",
				"称名癖：无法抑制地不断重复某个词语的冲动。",
				"剔指癖：执着于剔指甲。",
				"恋食癖：对某种食物的病态热爱。",
				"抱怨癖：一种在抱怨时产生的近乎病态的愉悦感。",
				"面具狂：执着于佩戴面具。",
				"幽灵狂：痴迷于幽灵。",
				"谋杀癖：病态的谋杀倾向。",
				"渴光癖：对光的病态渴求。",
				"背德癖：病态地渴求违背社会道德",
				"求财癖：对财富的强迫性的渴望。",
				"欺骗狂：无法抑制的执着于撒谎。",
				"纵火狂：执着于纵火。",
				"提问狂：执着于提问。",
				"挖鼻癖：执着于挖鼻子。",
				"涂鸦癖：沉迷于涂鸦。",
				"列车狂：认为火车或类似的依靠轨道交通的旅行方式充满魅力。",
				"臆智症：臆想自己拥有难以置信的智慧。",
				"科技狂：痴迷于新的科技。",
				"臆咒狂：坚信自己已被某种死亡魔法所诅咒。",
				"臆神狂：坚信自己是一位神灵。",
				"抓挠癖：抓挠自己的强迫倾向。",
				"手术狂：对进行手术的不正常爱好。",
				"拔毛癖：执着于拔下自己的头发。",
				"臆盲症：病理性的失明。",
				"嗜外狂：痴迷于异国的事物。",
				"喜兽癖：对待动物的态度近乎疯狂地友好。",
		};
	}
	
	String getIllness(String msg) {
		StringBuilder result = new StringBuilder("疯狂发作-");
		int type = ThreadLocalRandom.current().nextInt(0, 10);
		int time = ThreadLocalRandom.current().nextInt(0, 10);
		
		if (msg.equals("0")) result.append("临时症状:1d10=").append(type).append("\n").append(MadA[type]);
		else if (msg.equals("1")) result.append("总结症状:1d10=").append(type).append("\n").append(MadB[type]);
		
		if (type >= 8) {
			int subtype = ThreadLocalRandom.current().nextInt(0, 100);
			result.append("\n具体症状:1d100=").append(subtype).append("\n");
			result.append((type==8)? Phobia[subtype] : Mania[subtype]);
		}
		return result.append("\n持续时间: 1d10=").append(time).append("轮").toString();
	}
}
