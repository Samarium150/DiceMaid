package me.cqp.samarium;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Dice{
	
	private static Dice instance;
	private Pattern space = Pattern.compile("\\s*|\t|\r|\n");

	static synchronized Dice getInstance(){
		if (instance==null) instance = new Dice();
		return instance;
	}
	
	public static void main(String[] args) {
		Dice d = Dice.getInstance();
		int count = 0;
		String c = d.coc();
		while (Integer.valueOf(c.substring(99))<=650) {
			c = d.coc();
			count++;
		}
		System.out.println("count:"+count);
		System.out.println(c);
	}
	
	private int roll(int times, int sides){
		int result = 0;
		for (int i=1; i<=times; i++) result += ThreadLocalRandom.current().nextInt(1, sides+1);
		return result;
	}
	
	String coc() {
		int strength = roll(3, 6)*5;
		int constitution = roll(3, 6)*5;
		int size = (roll(2, 6)+6)*5;
		int dexterity = roll(3, 6)*5;
		int appearance = roll(3, 6)*5;
		int intelligence = (roll(2, 6)+6)*5;
		int power = roll(3, 6)*5;
		int education =  (roll(2, 6)+6)*5;
		int luck = roll(3, 6)*5;
		int total = strength + constitution + size
				+ dexterity + appearance + intelligence
				+ power + education + luck;
		
		return String.format("调查员作成EX： \n"
						+ "力量STR：%d，体质CON：%d，体型SIZ：%d，\n"
						+ "敏捷DEX：%d，外貌APP：%d，智力INT：%d，\n"
						+ "意志POW：%d，教育EDU：%d，幸运LUC：%d，\n"
						+ "属性总和：%d",
				strength, constitution, size, dexterity,
				appearance, intelligence, power, education, luck, total);
	}
	
	String coc(int n){
		if (n>10) return "最多生成10组数据";
		if (n<0) n = Math.abs(n);
		if (n==0) return "";
		StringBuilder result = new StringBuilder();
		for (int i=0; i<n; i++) {
			result.append(coc());
			if (i!=n-1) result.append("\n");
		}
		return result.toString();
	}
	
	String toss(String msg) {
		Pattern defaultDice = Pattern.compile("\\Dd\\d*");
		Pattern specificDice = Pattern.compile("\\d*d\\d*");
		Pattern multiDice = Pattern.compile("(\\d*d\\d*)(\\+((\\d*d\\d*)|(\\d+)))+");
		
		Matcher dD = defaultDice.matcher(msg);
		Matcher sD = specificDice.matcher(msg);
		Matcher mD = multiDice.matcher(msg);
		StringBuilder result = new StringBuilder();
		if (dD.find()) {
			int s;
			try { s = Integer.valueOf(msg.substring(dD.start()+2, dD.end())); }
			catch (NumberFormatException e) { s = 100; }
			if (dD.end()!=msg.length()) {
				Matcher sp = space.matcher(msg.substring(dD.end()));
				String reason = sp.replaceAll("");
				result.append("因为").append(reason).append(", ");
			}
			result.append("投1d").append(s).append("=").append(roll(1, s));
			return result.toString();
		}
		else if (mD.find()) {
			String ds = msg.substring(mD.start(), mD.end());
			String[] dcs = ds.split("\\+");
			if (mD.end()!=msg.length()) {
				Matcher sp = space.matcher(msg.substring(mD.end()));
				String reason = sp.replaceAll("");
				result.append("因为").append(reason).append(", ");
			}
			result.append("投").append(ds).append("=");
			int sum = 0;
			for (int i=0; i<dcs.length; i++) {
				int r;
				String value = dcs[i];
				if (!value.contains("d")) {
					r = Integer.valueOf(dcs[i]);
				}
				else {
					String[] dc = value.split("d");
					int c = Integer.valueOf(dc[0]);
					if (c > 20)  return "不要扔这么多骰子!";
					int s = Integer.valueOf(dc[1]);
					r = roll(c, s);
				}
				sum += r;
				result.append(r);
				if (i!=dcs.length-1) result.append("+");
				else result.append("=").append(sum);
			}
			return result.toString();
		}
		else if (sD.find()) {
			String ds = msg.substring(sD.start(), sD.end());
			String[] dc = ds.split("d");
			int c = Integer.valueOf(dc[0]);
			if (c > 20)  return "不要扔这么多骰子!";
			int s = Integer.valueOf(dc[1]);
			if (sD.end()!=msg.length()) {
				Matcher sp = space.matcher(msg.substring(sD.end()));
				String reason = sp.replaceAll("");
				result.append("因为").append(reason).append(", ");
			}
			result.append("投").append(ds).append("=").append(roll(c, s));
			return result.toString();
		}
		return "";
	}
	
	String check(String msg, int type) {
		Pattern cReason = Pattern.compile("^[~|～](ra|en)");
		Pattern cSkill = Pattern.compile("\\s+\\d+");
		Matcher mcReason = cReason.matcher(msg);
		Matcher mcSkill = cSkill.matcher(msg);
		StringBuilder result = new StringBuilder("进行");
		int s;
		String reason = "";
		if (mcSkill.find()) {
			try {
				Matcher sp = space.matcher(msg.substring(mcSkill.start(), mcSkill.end()));
				s = Integer.valueOf(sp.replaceAll(""));
			}
			catch (NumberFormatException e) { s = 20; }
			if (mcReason.find()&mcReason.end()!=mcSkill.start()) reason = msg.substring(mcReason.end(), mcSkill.start());
		} else {
			s = 20;
			if (mcReason.find()) { reason = msg.substring(mcReason.end()); }
		}
		Matcher sp = space.matcher(reason);
		reason = sp.replaceAll("");
		result.append(reason);
		int dx = roll(1, 100);
		if (type==0) {
			result.append("检定: 1d100=").append(dx).append("|").append(s).append(", ").append(checkResult(dx, s, type));
		} else if (type==1) {
			String success = checkResult(dx, s, type);
			result.append("成长检定: 1d100=").append(dx).append("|").append(s).append(", ").append(success).append("\n");
			int n = 0;
			if (success.equals("成功")) {
				n = roll(1, 10);
				result.append("增长: 1d10=").append(n).append("\n");
			}
			result.append("当前技能值为: ").append(s+n);
		}
		return result.toString();
	}
	
	private String checkResult(int result, int skill, int type) {
		if (type==0) {
			long half = Math.round(0.5*skill);
			long fifth = Math.round(0.2*skill);
			if (result > 95 & result > skill) return "大失败";
			if (result <= 5 & skill > result) return "大成功";
			else if (skill < result & result <= 95) return "失败";
			else if (half < result & result <= skill) return  "普通成功";
			else if (fifth < result & result <= half) return "困难成功";
			else if (result <= fifth) return  "极难成功";
		} else if (type==1) {
			if (result>skill|result>=96) return "成功";
			return "失败";
		}
		
		return "";
	}
	
	String quickSanCheck(String msg) {
		Pattern pDices = Pattern.compile("\\s(\\d*d\\d+|\\d+)/(\\d*d\\d+|\\d+)");
		Pattern pre = Pattern.compile("\\d\\s\\d+");
		Matcher mDices = pDices.matcher(msg);
		Matcher mPre = pre.matcher(msg);
		StringBuilder result = new StringBuilder("的San Check: \n1d100=");
		int cResult = roll(1, 100);
		if (mDices.find()&mPre.find()) {
			System.out.println(msg.substring(mDices.start()+1, mDices.end()));
			String[] dices = msg.substring(mDices.start()+1, mDices.end()).split("/");
			String da = dices[0]; String db = dices[1];
			String[] dax = da.split("d"); String[] dbx = db.split("d");
			int p = Integer.valueOf(msg.substring(mPre.start()+2));
			boolean success = cResult<=p;
			result.append(cResult).append("|").append(p).append(", ").append(success?"成功, ":"失败, ").append("\nSan值减少");
			int times, sides, dc=0;
			if (success) {
				times = Integer.valueOf(dax[0]);
				if (dax.length==1) result.append(times);
				else {
					if (cResult <= 5) {
						dc = 1;
						result.append(dc).append("\n");
					}
					else {
						sides = Integer.valueOf(dax[1]);
						dc = roll(times, sides);
						result.append(times).append("d").append(sides).append("=").append(dc).append("\n");
					}
				}
			}
			else {
				times = Integer.valueOf(dbx[0]);
				if (dbx.length==1) result.append(times).append("\n");
				else {
					sides = Integer.valueOf(dbx[1]);
					if (cResult>=96) {
						dc = sides;
						result.append(dc).append("\n");
					}
					else {
						dc = roll(times, sides);
						result.append(times).append("d").append(sides).append("=").append(dc).append("\n");
					}
					
				}
			}
			result.append("当前San值为").append((dc!=0)?p-dc:p-times);
			return result.toString();
		}
		return "";
	}
	
	String hidden(String msg) {
		Pattern pDefault = Pattern.compile("^[~|～]h$");
		Matcher mDefault = pDefault.matcher(msg);
		String result;
		if (mDefault.matches()) result = this.toss("~r1d100");
		else result = this.toss(msg.substring(2));
		return (result.equals(""))? "" : "暗"+ result;
	}
}