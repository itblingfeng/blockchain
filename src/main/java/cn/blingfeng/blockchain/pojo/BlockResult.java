package cn.blingfeng.blockchain.pojo;

import cn.blingfeng.blockchain.enums.BlockEnum;
import lombok.Data;

/**
 * 区块操作的结果类型
 */
@Data
public class BlockResult {
    private int status;
    private String msg;
    private Object data;
    BlockResult(int status,String msg,Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public static BlockResult ok(String msg){
        return new BlockResult(BlockEnum.SUCCESS.getNum(),msg,null);
    }
    public static BlockResult fail(String msg){
        return new BlockResult(BlockEnum.FAIL.getNum(),msg,null);
    }
}
