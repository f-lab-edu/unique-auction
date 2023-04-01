package com.uniqueauction.infrastructure.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RedisLockRepository {

	private final RedissonClient redissonClient;

	@Autowired
	public RedisLockRepository(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	public RLock getLock(String lockKey) {
		return redissonClient.getLock(lockKey);
	}

	public boolean tryLock(RLock lock, long waitTime, long leaseTime) {
		try {
			return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		}
	}

	public void unlock(RLock lock) {
		lock.unlock();
	}
}
