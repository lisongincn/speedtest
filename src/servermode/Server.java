package servermode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import toolmode.DataInfo;
import toolmode.Tool;

public class Server {

    public Server() throws IOException {
        int inputProt = inputProt(); // 输入端口
        int inputDataSize = inputDataSize(); // 输入数据大小
        // int inputProt = 8888; // 输入端口
        // int inputDataSize = 536870912; // 输入数据大小
        ServerSocket createServerSocket = createServerSocket(inputProt); // 输入创建服务器
        Socket waitingForConnection = waitingForConnection(createServerSocket); // 等待连接
        OutputStream openInputStream = openOutputStream(waitingForConnection); // 打开通道
        while (true) { // 不断发送
            DataInfo dataInfo = writeData(openInputStream, inputDataSize); // 发送数据
            Tool.printResult(dataInfo); // 输出结果
        }
    }

    private Socket waitingForConnection(ServerSocket serverSocket) throws IOException {
        Tool.log("等待连接");
        Socket accept = serverSocket.accept();
        Tool.log(String.format("%s 已连接", accept.getInetAddress().getHostAddress()));
        return accept;
    }

    private int inputProt() {
        Tool.log("输入要使用的 端口\t例:8888");
        int prot = Tool.scanner.nextInt();
        Tool.log(String.format("要使用的\t端口:%d", prot));
        return prot;
    }

    private int inputDataSize() {
        Tool.log("输入数据大小(单位:MB,1GB=1024MB)\t例:512");
        Tool.loge("注意\t数据多大就会占用多大内存");
        int dataSize = Tool.scanner.nextInt() * 1024 * 1024;
        Tool.log(String.format("数据大小为:\t%d 字节", dataSize));
        return dataSize;
    }

    private ServerSocket createServerSocket(int prot) throws IOException {
        Tool.log("正在尝试创建服务器");
        ServerSocket serverSocket = new ServerSocket(prot);
        Tool.log("已创建");
        return serverSocket;
    }

    private OutputStream openOutputStream(Socket socket) throws IOException {
        Tool.log("正在尝试打开通道");
        OutputStream outputStream = socket.getOutputStream();
        Tool.log("已打开通道");
        return outputStream;
    }

    private DataInfo writeData(OutputStream outputStream, int dataSize) throws IOException {
        DataInfo dataInfo = new DataInfo();
        dataInfo.setData(new byte[dataSize]);
        Tool.log("正在尝试发送数据");
        dataInfo.setStartTime(System.currentTimeMillis());
        outputStream.write(dataInfo.getData());
        outputStream.flush();
        dataInfo.setFinishTime(System.currentTimeMillis());
        Tool.log("发送数据完成");
        return dataInfo;
    }
}