package cn.blingfeng.blockchain.config;

import cn.blingfeng.blockchain.pojo.Block;
import cn.blingfeng.blockchain.pojo.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

import static cn.blingfeng.blockchain.utils.BlockUtils.calculateHash;

@Configuration
public class Config {
    /**
     * 创建创世块
     * @return
     */
    @Bean
    public List<Block> getBlockchain(){
        List<Block> blockchain = new LinkedList<>();
        Block genesisBlock = new Block();
        genesisBlock.setIndex(0);
        genesisBlock.setTimestamp(System.currentTimeMillis());
        Data data = new Data();
        data.setSedAddr("0x0000000000000000000000");
        data.setRecAddr("0x0101010101010011100004");
        data.setCoinCount(222);
        genesisBlock.setData(data);
        genesisBlock.setPreHash("0x");
        genesisBlock.setHash(calculateHash(genesisBlock));
        blockchain.add(genesisBlock);
        return blockchain;
    }
}
