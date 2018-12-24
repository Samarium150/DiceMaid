package me.cqp.samarium;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

final class Maid{
	private static Maid instance = null;
	
	private static Dice dice = Dice.getInstance();
	private static Madness madness = Madness.getInstance();
	private static Tarot tarot = Tarot.getInstance();
	private static LuckOfToday luckOfToday = LuckOfToday.getInstance();
	private Pattern space = Pattern.compile("\\s*|\t|\r|\n");
	
	private String response = "";
	
	private Maid(){
	
	}
	
	public static void main(String[] args){
		Maid maid = Maid.getInstance();
		maid.command(0L, "~help");
		System.out.println(maid.getResponse());
	}
	
	static synchronized Maid getInstance() {
		if (instance==null) instance = new Maid();
		return instance;
	}
	
	String getResponse() {
		String temp = response;
		response = "";
		return temp;
	}
	
	void command(long qq, String msg) {
		Pattern pHelp = Pattern.compile("^[~|～]help$");
		Matcher mHelp = pHelp.matcher(msg);
		if (mHelp.matches()) {
			response = help();
			return;
		}
		
		Pattern pCoc = Pattern.compile("^[~|～]coc7\\s*\\d*");
		Pattern pnCoc = Pattern.compile("\\s+\\d+");
		Matcher mCoc = pCoc.matcher(msg);
		Matcher mnCoc = pnCoc.matcher(msg);
		if (mCoc.matches()) {
			if (mnCoc.find()){
				Matcher sp = space.matcher(msg.substring(mnCoc.start(), mnCoc.end()));
				String num = sp.replaceAll("");
				response = dice.coc(Integer.valueOf(num));
				return;
			}
			response = dice.coc();
			return;
		}
		
		Pattern pJrrp = Pattern.compile("^[~|～]jrrp");
		Matcher mJrrp = pJrrp.matcher(msg);
		if (mJrrp.matches()) {
			response = luckOfToday.getLuck(qq);
			return;
		}
		
		Pattern pTarot = Pattern.compile("^[~|～]tarot.*");
		Matcher mTarot = pTarot.matcher(msg);
		if (mTarot.matches()) {
			response = tarot.getCard(msg);
			return;
		}
		
		Pattern pCheck = Pattern.compile("^[~|～]ra.*");
		Matcher mCheck = pCheck.matcher(msg);
		if (mCheck.matches()) {
			response = dice.check(msg);
			return;
		}
		
		Pattern pDice = Pattern.compile("^[~|～]r.*");
		Matcher mD = pDice.matcher(msg);
		if (mD.matches()) {
			response = dice.toss(msg);
			return;
		}
		
		Pattern pSc = Pattern.compile("^[~|～]sc\\s(\\d+|\\d*d\\d+)/(\\d+|\\d*d\\d+)\\s\\d+");
		Matcher mSc = pSc.matcher(msg);
		if (mSc.matches()) {
			response = dice.quickSanCheck(msg);
			return;
		}
		
		Pattern pHidden = Pattern.compile("^[~|～]h[^e]*.*");
		Matcher mHidden = pHidden.matcher(msg);
		if (mHidden.matches()) {
			response = dice.hidden(msg);
			return;
		}
		
		Pattern pMadA = Pattern.compile("^[~|～]ti$");
		Matcher mMadA = pMadA.matcher(msg);
		Pattern pMadB = Pattern.compile("^[~|～]li$");
		Matcher mMadB = pMadB.matcher(msg);
		if (mMadA.matches()) {
			response = madness.getIllness("0");
		}else if (mMadB.matches()) {
			response = madness.getIllness("1");
		}
	}
	
	private String help() {
		return "帮助文档: \n" +
				"1. ~jrrp: 查看今日人品;\n" +
				"2. ~r[骰子表达式]: 投一个指定的骰子, 不写骰子个数则默认为1;\n" +
				"3. ~ra[检定项目] [数值]: 进行一次指定技能的检定, 技能数值与项目之间必须要用空格隔开;\n" +
				"4. ~sc [San值表达式] [当前San值]: 快捷San check, 示例: ~sc 1d10/1d100 80;\n" +
				"5. ~h[骰子表达式]: 进行一次暗骰, 默认为1d100;\n" +
				"6. ~tarot[理由]: 抽一张塔罗牌; \n\t6.1 ~tarot 3[理由]: 抽三张塔罗牌, 一张大阿卡那, 两张小阿卡那;\n" +
				"7. ~help: 查看帮助文档;\n" +
				"8. ~ti/li: 决定疯狂症状;\n" +
				"9. ~coc7 [正整数n]：生成n组7版人物数据，最多10组";
	}
}
