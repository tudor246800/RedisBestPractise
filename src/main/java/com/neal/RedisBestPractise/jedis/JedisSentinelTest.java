package com.neal.RedisBestPractise.jedis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelTest {
	
	public static void main(String[] args) {
		JedisPoolConfig jcon = new JedisPoolConfig();
		jcon.setMaxTotal(200);
		jcon.setMaxIdle(50);
		jcon.setTestOnBorrow(false);
		jcon.setTestOnReturn(false);
		jcon.setJmxEnabled(true);
		Set<String> sentinels = new HashSet<String>();
		sentinels.add("192.168.153.128:26379");
		sentinels.add("192.168.153.128:26380");
		sentinels.add("192.168.153.128:26381");
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels,jcon,5000 );
		Jedis jedis = null;
		try {
			jedis = sentinelPool.getResource();
			System.out.println(jedis.get("xixi"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}

		sentinelPool.close();

	}

}
