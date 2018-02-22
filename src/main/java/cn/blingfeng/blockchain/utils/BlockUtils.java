package cn.blingfeng.blockchain.utils;

import cn.blingfeng.blockchain.pojo.Block;
import cn.blingfeng.blockchain.pojo.Data;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

/**
 * 区块操作工具类
 */
public class BlockUtils {
    /**
     * 计算区块hash
     * @param block
     * @return
     */
    public static String calculateHash(Block block){
        String result = block.getIndex() + block.getTimestamp() + block.getData().toString() + block.getPreHash();
        MessageDigest digest = DigestUtils.getSha256Digest();
        byte[] hash = digest.digest(StringUtils.getBytesUtf8(result));
        return Hex.encodeHexString(hash);
    }

    /**
     * 生成区块
     * @param oldBlock
     * @param data
     * @return
     */
    public static Block generateBlock(Block oldBlock, Data data){
        Block newBlock = new Block();
        newBlock.setIndex(oldBlock.getIndex()+1);
        newBlock.setTimestamp(System.currentTimeMillis());
        newBlock.setData(data);
        newBlock.setPreHash(oldBlock.getHash());
        newBlock.setHash(calculateHash(newBlock));
        return newBlock;
    }

    /**
     * 校验区块的有效性
     * @param newBlock
     * @param oldBlock
     * @return
     */
    public static boolean validBlock(Block newBlock,Block oldBlock){
        if(newBlock.getIndex()!=oldBlock.getIndex()+1){
            return false;
        }
        if(!newBlock.getPreHash().equals(oldBlock.getHash())){
            return false;
        }
        if(!calculateHash(oldBlock).equals(newBlock.getPreHash())){
            return false;
        }
        return true;
    }

    /**
     * 防止产生多个支链，选择最长的支链为主链
     * @param blockChain
     * @param branchChain
     */
    public static void choiceChain(List<Block> blockChain,List<Block> branchChain){
        if (branchChain.size() > blockChain.size()) {
            blockChain = branchChain;
        }
    }
}
