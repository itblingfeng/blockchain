package cn.blingfeng.blockchain.enums;

import lombok.Getter;

public enum  BlockEnum {
    /** 成功 */
    SUCCESS(200,"成功"),
    FAIL(500,"失败"),
    ;
    private int num;
    private String msg;
    private BlockEnum(int num,String msg){
        this.num = num;
        this.msg = msg;
    }
    public int getNum(){
        return num;
    }
}
