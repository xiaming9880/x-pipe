package com.ctrip.xpipe.redis.keeper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.ctrip.xpipe.redis.protocal.CommandRequester;
import com.ctrip.xpipe.redis.protocal.PsyncObserver;

import io.netty.channel.Channel;

/**
 * @author wenchao.meng
 *
 * 2016年3月29日 下午3:09:23
 */
public interface RedisKeeperServer extends RedisServer, PsyncObserver{
	
	long getBeginReploffset();

	long getEndReploffset();

	void slaveConnected(Channel channel);
	
	void slaveDisconntected(Channel channel);
	
	RedisClient clientConnected(Channel channel);
	
	void clientDisConnected(Channel channel);
	
	String getKeeperRunid();
	
	void addCommandsListener(Long offset, CommandsListener listener);

	void readRdbFile(RdbFileListener rdbFileListener) throws IOException;
	
	Set<RedisClient> allClients();
	
	Map<Channel, RedisClient> slaves();
	
	CommandRequester getCommandRequester();
	
	ReplicationStore getReplicationStore();
}
