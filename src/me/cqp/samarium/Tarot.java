package me.cqp.samarium;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Tarot {
	
	private static Tarot instance;
	private String[] numbers = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII",
			"IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI"};
	private String[] types = {"Suit", "Rank", "花色", "数字"};
 	private HashMap<String[], String[]> MAJOR_ARCANA;
	private HashMap<String[], String[][]> MINOR_ARCANA;
	private String[] MAJOR_MEANING_A;
	private String[] MAJOR_MEANING_B;
	private String[][] MINOR_MEANING;
	
	static synchronized Tarot getInstance() {
		if (instance==null) instance = new Tarot();
		return instance;
	}
	
	private Tarot() {
		String[] majors = {"The Fool 愚者", "The Magician 魔术师", "The High Priestess 女祭司", "The Empress 皇后",
				"The Emperor 皇帝", "The Hierophant 教皇", "The Lovers 恋人", "The Chariot 战车", "The Force 力量",
				"The Hermit 隐者", "Wheel of Fortune 命运之轮", "Justice 正义", "The Hanged Man 倒吊者", "Death 死亡",
				"Temperance 节制", "The Devil 恶魔", "The Tower 塔", "The Star 星辰", "The Moon 月亮", "The Sun 太阳",
				"Judgement 审判", "The World 世界"};
		String[][] minors = {
				{"Cups", "Wands", "Swords", "Coins"},
				{"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
				"Eight", "Nine", "Ten", "Page", "Knight", "Queen", "King"},
				{"圣杯", "权杖", "宝剑", "金币"},
				{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "侍从", "骑士", "女王", "国王"}};
		MAJOR_MEANING_A = new String[]{
				"毫无目的地前行, 错误的选择, 一意孤行, 意外的收获, 美好的梦想, 冒险心",
				"事情的开始, 行动的改变, 熟练的技术及技巧, 贯彻我的意志, 运用自然的力量来达到野心",
				"开发出内在的神秘潜力, 前途将有所变化的预言, 深刻地思考, 敏锐的洞察力, 准确的直觉",
				"幸福, 成功, 收获, 无忧无虑, 圆满的家庭生活, 良好的环境, 美貌, 艺术, 与大自然接触, 愉快的旅行, 休闲",
				"光荣, 权力, 胜利, 握有领导权, 坚强的意志, 达成目标, 父亲的责任, 精神上的孤单",
				"援助, 同情, 宽宏大量, 可信任的人给予的劝告, 良好的商量对象, 得到精神上的满足, 遵守规则, 志愿者",
				"撮合, 爱情, 流行, 兴趣, 充满希望的未来, 魅力, 增加朋友",
				"努力而获得成功, 胜利, 克服障碍, 行动力, 自立, 尝试, 自我主张, 旅行运大吉",
				"大胆的行动, 有勇气的决断, 新发展, 大转机, 异动, 以意志力战胜困难",
				"隐藏的事实, 个别的行动, 倾听他人的意见, 享受孤独, 有益的警戒, 避开危险",
				"关键性的事件, 有新的机会, 新的潮流, 环境的变化, 幸运的开端, 状况好转, 问题解决, 幸运之神降临",
				"公正, 中立, 诚实, 心胸坦荡, 表里如一, 身兼二职, 追求合理化, 协调者, 与法律有关, 光明正大的交往, 感情和睦",
				"接受考验, 行动受限, 牺牲, 不畏艰辛, 不受利诱, 有失必有得, 吸取经验教训, 浴火重生, 广泛学习, 奉献的爱",
				"失败, 接近毁灭, 生病, 失业, 维持停滞状态, 持续的损害, 交易停止, 枯燥的生活, 别离, 重新开始, 恋情终止",
				"单纯, 调整, 平顺, 互惠互利, 好感转为爱意",
				"被束缚, 堕落, 生病, 恶意, 屈服, 欲望的俘虏, 不可抗拒的诱惑, 颓废的生活",
				"破产, 逆境, 被开除, 急病, 致命的打击, 巨大的变动, 受牵连, 信念崩溃, 玩火自焚, 纷扰不断, 突然分离",
				"前途光明, 充满希望, 想象力, 创造力, 幻想, 满足愿望, 水准提高, 理想的对象, 美好的恋情",
				"不安, 迷惑, 动摇, 谎言, 欺骗, 鬼迷心窍, 动荡的爱, 三角关系",
				"活跃, 丰富的生命力, 充满生机, 精力充沛, 工作顺利, 贵人相助, 健康的交际",
				"复活的喜悦, 康复, 坦白, 好消息, 好运气, 初露锋芒, 复苏的爱, 重逢, 爱的奇迹",
				"完成, 成功, 完美无缺, 连续不断, 精神亢奋, 拥有毕生奋斗的目标, 完成使命, 幸运降临, 快乐的结束"};
		MAJOR_MEANING_B = new String[]{
				"重视梦想, 心情空虚, 行事缺乏计划, 没有耐心, 过于信赖别人, 成绩一落千丈",
				"意志力薄弱, 起头难, 走入错误的方向, 知识不足, 被骗和失败",
				"过于洁癖, 无知, 贪心, 目光短浅, 自尊心过高, 偏差的判断, 有勇无谋, 自命不凡",
				"不活泼, 缺乏上进心, 散漫的生活习惯, 无法解决的事情, 不能看到成果, 担于享乐, 环境险恶, 与家人发生纠纷",
				"幼稚, 无力, 独裁, 撒娇任性, 平凡, 没有自信, 行动力不足, 意志薄弱, 被支配",
				"错误的讯息, 恶意的规劝, 上当, 援助被中断, 愿望无法达成, 被人利用, 被放弃",
				"禁不起诱惑, 纵欲过度, 反覆无常, 友情变淡, 厌倦, 争吵, 华丽的打扮, 优柔寡断",
				"争论失败, 发生纠纷, 阻滞, 违返规则, 诉诸暴力, 突然的失败, 挫折和自私自利",
				"胆小, 输给强者, 经不起诱惑, 屈服在权威与常识之下, 没有实践便告放弃, 虚荣, 懦弱, 没有耐性",
				"无视警告, 憎恨孤独, 自卑, 担心, 幼稚思想, 过于慎重导致失败, 偏差, 不宜旅行",
				"挫折, 计划泡汤, 障碍, 无法修正方向, 往坏处发展, 恶性循环, 中断",
				"失衡, 偏见, 纷扰, 诉讼, 独断专行, 问心有愧, 无法两全, 表里不一, 情感波折",
				"无谓的牺牲, 骨折, 厄运, 不够努力, 处于劣势, 任性, 利己主义者, 缺乏耐心, 受惩罚, 没有结果的恋情",
				"一线希望, 起死回生, 回心转意, 摆脱低迷状态, 挽回名誉, 身体康复, 突然改变计划, 逃避现实",
				"消耗, 下降, 疲劳, 损失, 不安, 不融洽",
				"逃离拘束, 解除困扰, 治愈病痛, 告别过去, 暂停, 拒绝诱惑, 别离时刻, 爱恨交加的恋情。",
				"困境, 内讧, 紧迫的状态, 状况不佳, 趋于稳定, 骄傲自大将付出代价, 背水一战, 分离的预感",
				"挫折, 失望, 好高骛远, 异想天开, 仓皇失措, 事与愿违, 工作不顺心, 情况悲观, 秘密恋情, 缺少爱的生活",
				"逃脱骗局, 解除误会, 状况好转, 预知危险, 等待, 正视爱情的裂缝",
				"消沉, 体力不佳, 缺乏连续性, 意气消沉, 生活不安, 人际关系不好, 感情波动",
				"一蹶不振, 幻灭, 隐瞒, 坏消息, 无法决定, 缺少目标, 没有进展, 消除, 恋恋不舍",
				"未完成, 失败, 准备不足, 盲目接受, 一时不顺利, 半途而废, 精神颓废, 饱和状态, 合谋, 态度不够融洽, 感情受挫"};
		MINOR_MEANING = new String[][]{
				{
				"新恋情或者新友情的开始",
				"和谐对等的, 愉快的关系",
				"欢庆的场合",
				"消极, 无聊, 疲惫, 退缩的时光",
				"悲伤痛苦的处境, 另类生活的开始",
				"对往事的回忆和思乡, 慷慨大方, 乐于付出, ",
				"不切实际的,  污浊的存在",
				"令人愉快的公司和良好的友谊, 聚会和有计划的庆祝活动",
				"梦和愿望的实现, 好运和财富",
				"家庭幸福, 或许能得到预料之外的成功与好消息",
				"一个永久的亲密朋友, 或许是分别很久的童年朋友, 初恋情人",
				"一个假朋友, 一个来自远方的陌生人, 勾引者, 应把握当前的命运",
				"一个忠诚, 钟情的女人, 温柔大方, 惹人怜爱",
				"一个诚实, 善意的男子, 但容易草率的作出决定, 因此不是一个可以依赖的人, 也不要指望从他那里得到有益的忠告"},
				{
				"财富和事业的成功, 终身的朋友和宁静的心境",
				"失望, 来自朋友和生意伙伴的反对",
				"不止一次婚姻。可能和一个人订婚很长时间之后, 却突然和另一个人结婚",
				"谨防一个项目的失败",
				"娶一个富婆",
				"有利可图的合伙经营",
				"好运和幸福, 但应该谨防某个异性",
				"贪婪, 可能会花掉不属于自己的钱",
				"和朋友的辩论, 固执的争吵",
				"意想不到的好运, 一次长途旅行, 但也可能会失去一个亲密朋友",
				"一个诚挚但又缺乏耐心的朋友, 善意的奉承",
				"幸运地得到了亲人的帮助, 或者是陌生人的帮助",
				"一个亲切善良的女人, 但爱发脾气",
				"一个诚挚的男人, 慷慨忠实"},
				{
				"不幸, 坏消息, 死亡的消息, 充满妒忌的情感",
				"变化, 迁移, 失去家庭, 分离",
				"一次旅行, 爱情或婚姻的不幸",
				"疾病, 经济困难, 嫉妒, 各种小灾难会拖延手里的工作",
				"生意成功, 融洽和谐的伙伴关系, 但却需要克服困难。注意谨防坏脾气和气馁",
				"只有坚忍不拔的毅力, 才能帮你完成计划",
				"与朋友争吵, 招来很多麻烦",
				"在你所有的事业中都要谨慎, 那些看似朋友的人, 可能会成为你的敌人",
				"疾病, 灾难和各种各样的不幸",
				"悲伤或监禁, 否定了一切的好兆头",
				"一个懒惰或嫉妒的人, 事业中的障碍, 或许是一个骗子, 甚至可能是商业间谍",
				"传奇中的豪侠人物, 喜好奢侈和放纵, 但非常勇敢, 富于创业精神",
				"奸诈, 不忠, 恶毒, 一个寡妇或被抛弃的人",
				"一个野心勃勃, 妄想凌驾一切的人"
				},
				{
				"一个重要的消息, 或者一件珍贵的礼物",
				"热恋, 但会遭到朋友的反对",
				"争吵, 官司, 或者家庭纠纷",
				"不幸或秘密的背叛, 来自于不忠诚的朋友, 或者是因为你长久忽视了对方",
				"意外的消息, 可能带来生意的成功, 愿望能够实现, 或一桩美满的婚姻",
				"早婚, 但也会早早的结束, 而且第二次婚姻也没有好兆头",
				"谎言, 谣言, 恶意的批评, 运气糟糕的赌徒",
				"晚年婚姻, 或许是一次旅行, 可能会带来两者的结合",
				"具有强烈的旅行愿望, 嗜好冒险, 渴望看到生命的改变",
				"把钱作为行动的目标, 但并不一定会如愿以偿",
				"一个自私, 嫉妒的亲戚, 或一个带来坏消息的使者",
				"一个有耐心又有横心的男人, 发明家或科学家",
				"卖弄风情的女人, 乐于干涉别人的事情, 诽谤和谣言",
				"一个脾气粗暴的男人, 固执而充满了复仇心, 与他对抗会招来危险"
				}};
		MAJOR_ARCANA = new HashMap<>(1);
		MAJOR_ARCANA.put(numbers, majors);
		MINOR_ARCANA = new HashMap<>(1);
		MINOR_ARCANA.put(types, minors);
	}
	
	public static void main(String[] args) {
		Tarot tarot = new Tarot();
		System.out.println(tarot.MAJOR_MEANING_A[0]);
	}
	
	private String getCard() {
		int n = ThreadLocalRandom.current().nextInt(0, 22);
		int side = ThreadLocalRandom.current().nextInt(0, 9);
		String number = numbers[n];
		String card = MAJOR_ARCANA.get(numbers)[n];
		String s = (side%2 == 0)? ", 正位" : ", 逆位";
		String[] meanings = (side%2 == 0)? MAJOR_MEANING_A : MAJOR_MEANING_B;
		String meaning = meanings[n];
		return String.format("抽到了：\n%s %s%s\n释义: %s",number, card, s, meaning);
	}
	@SuppressWarnings("SameParameterValue")
	private String getCard(int n) {
		if (n==3) {
			int a1 = ThreadLocalRandom.current().nextInt(0, 3);
			int a2 = ThreadLocalRandom.current().nextInt(0, 13);
			int b1 = a1;
			int b2 = a2;

			while (b1==a1|b2==a2) {
				b1 = ThreadLocalRandom.current().nextInt(0, 3);
				b2 = ThreadLocalRandom.current().nextInt(0, 13);
			}
			String card2 = MINOR_ARCANA.get(types)[0][a1] + " " + MINOR_ARCANA.get(types)[1][a2] + " "
					+ MINOR_ARCANA.get(types)[2][a1] + MINOR_ARCANA.get(types)[3][a2];
			String card3 = MINOR_ARCANA.get(types)[0][b1] + " " + MINOR_ARCANA.get(types)[1][b2] + " "
					+ MINOR_ARCANA.get(types)[2][b1] + MINOR_ARCANA.get(types)[3][b2];
			String meaning2 = MINOR_MEANING[a1][a2];
			String meaning3 = MINOR_MEANING[b1][b2];
			return String.format("%s\n补充 1: %s\n释义: %s\n补充 2: %s\n释义: %s",
					getCard(), card2, meaning2, card3, meaning3);
		}
		return "";
	}
	String getCard(String msg) {
		Pattern pTarot = Pattern.compile("\\s3\\s*");
		Pattern tReason = Pattern.compile("^~tarot\\s*");
		
		Matcher numTarot = pTarot.matcher(msg);
		Matcher mReason = tReason.matcher(msg);
		
		StringBuilder result = new StringBuilder();
		
		if (numTarot.find()){
			if (numTarot.end()!=msg.length()) result.append("因为").append(msg.substring(numTarot.end())).append(", ");
			return result.append(this.getCard(3)).toString();
		} else {
			if (mReason.find()&&mReason.end()!=msg.length()) result.append("因为").append(msg.substring(mReason.end())).append(", ");
			return result.append(this.getCard()).toString();
		}
	}
}
