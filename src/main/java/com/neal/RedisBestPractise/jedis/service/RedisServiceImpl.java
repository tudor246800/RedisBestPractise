package com.neal.RedisBestPractise.jedis.service;

import redis.clients.jedis.JedisCluster;

public class RedisServiceImpl implements RedisService {
	
	private JedisCluster jedisCluster;

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	} 

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

}
