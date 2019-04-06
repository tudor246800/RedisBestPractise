package com.neal.RedisBestPractise.jedis.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterFactory {
	private Logger logger = LoggerFactory.getLogger(JedisClusterFactory.class);

	private JedisCluster jedisCluster;

	private int timeout = 1000;

	public static void main(String[] args) {
		JedisClusterFactory factory = new JedisClusterFactory();
		factory.init();
		System.out.println(factory.jedisCluster.get("hello"));
//		Map<String, JedisPool> jedisPoolMap = factory.jedisCluster.getClusterNodes();
//		for(Entry<String, JedisPool> pool : jedisPoolMap.entrySet()){
//			Jedis jedis = pool.getValue().getResource();
//		}
	}

	public void init() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(new String[] { "192.168.153.128:7003", "192.168.153.128:7004", "192.168.153.128:7005",
				"192.168.153.128:7006", "192.168.153.128:7007", "192.168.153.128:7008" }));
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		for (String hostPort : list) {
			String[] arr = hostPort.split(":");
			if (arr.length != 2)
				continue;
			nodes.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));
		}
		try {
			jedisCluster = new JedisCluster(nodes, timeout, jedisPoolConfig);
		} catch (Exception e) {
			logger.error("jedis cluster init failed");
		}

	}

	public void destroy() {
		if (jedisCluster != null)
			try {
				jedisCluster.close();
			} catch (IOException e) {
				logger.error("jedis cluster destroy failed");
			}
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}


	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
