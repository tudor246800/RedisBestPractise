package com.neal.RedisBestPractise.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	public static void main(String[] args) {
		JedisPoolConfig jcon = new JedisPoolConfig();
		jcon.setMaxTotal(200);
		jcon.setMaxIdle(50);
		jcon.setTestOnBorrow(false);
		jcon.setTestOnReturn(false);
		jcon.setJmxEnabled(true);
		JedisPool jp = new JedisPool(jcon, "192.168.153.128", 7000);
		Jedis jedis = null;
		try {
			jedis = jp.getResource();
			System.out.println(jedis.get("xixi"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}

		jp.close();

	}
}
