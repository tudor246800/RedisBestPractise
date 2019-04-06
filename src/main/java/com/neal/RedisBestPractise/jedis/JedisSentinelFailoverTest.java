package com.neal.RedisBestPractise.jedis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelFailoverTest {

	private static Logger logger = LoggerFactory.getLogger(JedisSentinelFailoverTest.class);

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
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels, jcon, 5000);

		while (true) {
			Jedis jedis = null;
			try {
				jedis = sentinelPool.getResource();
				System.out.println(jedis.get("xixi"));
				TimeUnit.SECONDS.sleep(2);;
			} catch (Exception e) {
				logger.error("------------- Failed !!!----------");
			} finally {
				if (jedis != null)
					jedis.close();
			}
			
			

		}

	}
}
