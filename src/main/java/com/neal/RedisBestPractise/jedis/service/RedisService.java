package com.neal.RedisBestPractise.jedis.service;

public interface RedisService {
	public String set(String key,String value);
	
	public String get(String key);
}
