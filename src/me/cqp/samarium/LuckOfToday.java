package me.cqp.samarium;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

final class LuckOfToday {
	
	private static LuckOfToday instance;
	private Calendar calendar;
	private Random generator;
	private static HashMap<Long, Double> data;
	
	static synchronized LuckOfToday getInstance() {
		if (instance==null) instance = new LuckOfToday();
		return instance;
	}
	
	public static void main(String[] args) {
		LuckOfToday luckOfToday = LuckOfToday.getInstance();
		System.out.println(luckOfToday.getLuck(0L));
		System.out.println(luckOfToday.getLuck(1L));
		System.out.println(luckOfToday.getLuck(2L));
		System.out.println(luckOfToday.getLuck(0L));
	}
	
	private LuckOfToday() {
		data = new HashMap<>();
		calendar = Calendar.getInstance();
	}
	
	private boolean check() {
		Calendar temp = Calendar.getInstance();
		temp.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), temp.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		if (!calendar.getTime().toString().equals(temp.getTime().toString())) {
			calendar = temp;
			data = new HashMap<>();
			return false;
		}
		return true;
	}
	
	private void set() {
		Date date = calendar.getTime();
		long seed = date.getTime()/1000;
		generator = new Random(seed);
	}
	
	private double generate(long qq) {
		this.set();
		double fortune = generator.nextDouble();
		while (data.containsValue(fortune)) fortune = generator.nextDouble();
		data.put(qq, fortune);
		return fortune;
	}
	
	String getLuck(long qq) {
		double fortune;
		try {
			if (check()) fortune = data.get(qq);
			else fortune = generate(qq);
		} catch (NullPointerException e) {
			fortune = generate(qq);
		}
		return String.format("今日人品为：%.2f%%", fortune*100);
	}
}
