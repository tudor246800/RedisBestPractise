package com.neal.RedisBestPractise.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JedisPractise {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.153.128", 7000);
//		jedis.set("hi", "hehe");
//		System.out.println(jedis.get("hi"));
		jedis.incr("xixi");
		jedis.close();
		
		Pipeline pipeline = jedis.pipelined();
		pipeline.syncAndReturnAll();
	}
}
