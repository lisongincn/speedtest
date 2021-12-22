package clientmode;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import toolmode.DataInfo;
import toolmode.Tool;

public class Client {
    public Client() throws IOException {
        InetSocketAddress inetSocketAddress = inputAddress(); // 输入ip和端口
        // InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080); // 默认ip和端口
        Socket socket = createSocket(inetSocketAddress); // 连接服务器
        InputStream inputStream = openInputStream(socket); // 打开通道
        while (true) { // 不断读取
            DataInfo dataInfo = readData(inputStream); // 开始读取
            // Tool.printResult(dataInfo); // 输出结果
            summarize(dataInfo);
        }
    }

    private DataInfo summarizeDataInfo = null;

    private void summarize(DataInfo dataInfo) {
        if (summarizeDataInfo == null) {
            summarizeDataInfo = new DataInfo();
            summarizeDataInfo.setStartTime(dataInfo.getStartTime());
        }
        summarizeDataInfo.setDataSize(summarizeDataInfo.getDataSize() + dataInfo.getDataSize());
        summarizeDataInfo.setFinishTime(dataInfo.getFinishTime());
        if (summarizeDataInfo.getUsedTime() >= 1000) {
            Tool.printResult(summarizeDataInfo); // 输出结果
            summarizeDataInfo = null;
            return;
        }
    }

    private InetSocketAddress inputAddress() {
        Tool.log("输入服务器的 地址和端口\t例:127.0.0.1:8888");
        String next = Tool.scanner.next();
        String[] split = new String(next).split(":");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(split[0], Integer.valueOf(split[1]));
        Tool.log(String.format("要使用的\tIP:%s\t端口:%d", inetSocketAddress.getHostString(), inetSocketAddress.getPort()));
        return inetSocketAddress;
    }

    private Socket createSocket(InetSocketAddress inetSocketAddress) throws IOException {
        Tool.log("正在尝试连接");
        Socket socket = new Socket(inetSocketAddress.getHostString(), inetSocketAddress.getPort());
        Tool.log("已连接");
        return socket;
    }

    private InputStream openInputStream(Socket socket) throws IOException {
        Tool.log("正在尝试打开通道");
        InputStream inputStream = socket.getInputStream();
        Tool.log("已打开通道");
        return inputStream;
    }

    private DataInfo readData(InputStream inputStream) throws IOException {
        DataInfo dataInfo = new DataInfo();
        //Tool.log("正在尝试接收数据");
        byte[] data = new byte[1048576];
        dataInfo.setStartTime(System.currentTimeMillis());
        int dataSize = inputStream.read(data);
        dataInfo.setFinishTime(System.currentTimeMillis());
        dataInfo.setData(Arrays.copyOf(data, dataSize));
        //Tool.log("接收数据完成");
        return dataInfo;
    }
}