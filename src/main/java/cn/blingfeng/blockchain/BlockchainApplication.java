package cn.blingfeng.blockchain;

import cn.blingfeng.blockchain.pojo.Block;
import cn.blingfeng.blockchain.pojo.Data;
import cn.blingfeng.blockchain.utils.BlockUtils;
import cn.blingfeng.blockchain.utils.FastJsonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import static cn.blingfeng.blockchain.utils.BlockUtils.calculateHash;

@SpringBootApplication
public class BlockchainApplication {
	public static void main(String[] args) {
			SpringApplication.run(BlockchainApplication.class, args);
	}

}
