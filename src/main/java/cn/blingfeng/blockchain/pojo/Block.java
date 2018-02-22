package cn.blingfeng.blockchain.pojo;

/**
 * 区块数据类型
 */
@lombok.Data
public class Block {
        /** 区块在整条链中的索引 */
        private int index;
        /** 区块生成的时间戳 */
        private long timestamp;
        /** 区块中的数据内容 */
        private Data data;
        /** 区块通过SHA256算法生成的散列值 */
        private String hash;
        /** 前一个区块的SHA256散列值 */
        private String preHash;
}
