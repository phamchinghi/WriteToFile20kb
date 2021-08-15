import com.sun.jdi.ShortType;

import java.io.*;
import java.nio.ByteBuffer;

public class Main {



    public static void main(String[] args) throws Exception {


//==============================Write to file  usage=============================
        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\mydata2.dat"));
        byte[] buf = new byte[2];
        for (int i = 50000; i <= 60000; i++) {
            //=====Cách 1
//            buf[0] = (byte)(i >> 8);
//            buf[1] = (byte)(i & 0xFF);
//            out.write(buf);
            //=====Cách 2
            ByteBuffer dbuf = ByteBuffer.allocate(2);// tọa 1 bộ đệm mới gồm 2 byte
            dbuf.putShort((short) i);//ghi gtri i vào mảng buf theo kiểu short
            byte[] bytes = dbuf.array();//.array trả về mảng byte
            out.writeByte(bytes[0]);//ghi xuống 1 byte -> ghi vào mảng buf
            out.writeByte(bytes[1]);
            System.out.println("Write done!");
        }
        out.flush();
        out.close();
//===============================Read file  usage========================
        DataInputStream in = new DataInputStream(new FileInputStream("D:\\mydata2.dat"));
        byte[] buf1 = new byte[2];// tạo mảng ký tự chứa 2 byte đọc ra

        while (in.available() > 0)
        {
            buf1[0] = in.readByte();//bỏ byte đầu vào
            buf1[1] = in.readByte();//bỏ byte sau vào

            ByteBuffer wrapped = ByteBuffer.wrap(buf1);//gói mảng byte đó vào bộ đệm
            int num = Short.toUnsignedInt(wrapped.getShort());
            System.out.print(num +" ");
        }
        in.close();
    }

}
