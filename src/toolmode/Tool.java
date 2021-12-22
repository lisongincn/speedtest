package toolmode;

import java.util.Scanner;

public class Tool {
    public static Scanner scanner = new Scanner(System.in);

    public static void log(String str) {
        System.out.println(str);
    }

    public static void loge(String str) {
        System.err.println(str);
    }

    public static String networkUnitConversion(long byteSize) {
        String unit = "Byte";
        float transform = byteSize;
        if (transform < 1024f) {
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 1024f;
        }

        if (transform < 1024f) {
            unit = "KB";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 1024f;
        }

        if (transform < 1024f) {
            unit = "MB";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 1024f;
        }

        unit = "GB";
        return String.format("%f %s", transform, unit);
    }

    public static String timeUnitConversion(long time) {
        String unit;
        float transform = time;
        if (transform < 1000f) {
            unit = "毫秒";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 1000;
        }
        if (transform < 60) {
            unit = "秒";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 60;
        }
        if (transform < 60) {
            unit = "分钟";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 60;
        }
        if (transform < 60) {
            unit = "小时";
            return String.format("%f %s", transform, unit);
        } else {
            transform /= 24;
        }
        unit = "天";
        return String.format("%f %s", transform, unit);
    }

    public static void printResult(DataInfo dataInfo) {
        Tool.log(
                String.format("数据大小%s\t所用时间:%s", Tool.networkUnitConversion(dataInfo.getDataSize()),
                        Tool.timeUnitConversion(dataInfo.getUsedTime())));
    }
}