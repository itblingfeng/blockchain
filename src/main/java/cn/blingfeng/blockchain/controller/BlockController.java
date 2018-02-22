package cn.blingfeng.blockchain.controller;

import cn.blingfeng.blockchain.pojo.Block;
import cn.blingfeng.blockchain.pojo.BlockResult;
import cn.blingfeng.blockchain.pojo.Data;
import cn.blingfeng.blockchain.utils.BlockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import static cn.blingfeng.blockchain.utils.BlockUtils.calculateHash;

@RestController
@Slf4j
public class BlockController {
    @Autowired
    private  List<Block> blockchain;
    @GetMapping("/status")
    public List<Block> getBlockchain(){
        return blockchain;
    }
    @PostMapping("/generate")
    public BlockResult generateBlock(Data data){
        if(data==null){
            log.info("【generateBlock】用户未填写转账信息");
            return BlockResult.fail("用户未填写转账信息");
        }
        //todo 获取账户中币的余额逻辑尚待完善（以下假设已经完善）


       Block endBlock =  blockchain.get(blockchain.size()-1);
        Block newBlock = BlockUtils.generateBlock(endBlock, data);
        if (BlockUtils.validBlock(newBlock, endBlock)) {
            blockchain.add(newBlock);
            log.info("【generateSuccess】block={}",newBlock.toString());
        } else {
            log.debug("【generateFail】block={}",newBlock.toString());
          return  BlockResult.fail("generateFail");
        }
        return BlockResult.ok("generateSuccess");

    }

}
