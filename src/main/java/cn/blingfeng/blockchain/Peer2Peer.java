package cn.blingfeng.blockchain;

import cn.blingfeng.blockchain.pojo.Block;
import cn.blingfeng.blockchain.utils.BlockUtils;
import cn.blingfeng.blockchain.utils.FastJsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Peer2Peer {
    private static List<Block> blockchain = new LinkedList<>();
    private static Socket socket;
    public static void main(String [] args){

        boolean isRunning = true;
        ServerSocket serverSocket = null;
        // 对新的区块进行接收

        try {
            serverSocket = new ServerSocket(9000);
            while (isRunning) {
                socket = serverSocket.accept();
                new Thread() {
                    @Override
                    public void run() {
                        InetAddress address = socket.getInetAddress();
                        BufferedReader br = null;
                        PrintWriter pw = null;
                        String line = null;
                        StringBuffer sb = new StringBuffer();
                        // 读取连接结点发送的信息
                        try {
                            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            while ((line = br.readLine()) != null) {
                                sb.append(line);
                            }
                            List<Block> newBlockchain = FastJsonUtils.toList(sb.toString(), Block.class);
                            BlockUtils.choiceChain(blockchain, newBlockchain);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (pw != null) {
                                    pw.close();
                                }
                                if (br != null) {
                                    br.close();
                                }
                            } catch (IOException e) {
                            }
                        }
                    }
                }.start();
            }


            // 将完整的区块链对其他节点进行广播
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(10000);
                            if (!socket.isClosed()) {
                                PrintWriter pw = null;
                                try {
                                    pw = new PrintWriter(socket.getOutputStream());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //将区块链广播至其他节点
                                pw.write(FastJsonUtils.toJSONString(blockchain));
                                pw.flush();
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }catch (Exception e){}
    }
}
