import com.sun.jdi.ShortType;

import java.io.*;
import java.nio.ByteBuffer;

public class Main {



    public static void main(String[] args) throws Exception {


//==============================Write to file  usage=============================
        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\mydata2.txt"));
        byte[] buf = new byte[2];
        for (int i = 50000; i <= 60000; i++) {
            //=====Cách 1
//            buf[0] = (byte)(i >> 8);
//            buf[1] = (byte)(i & 0xFF);
//            out.write(buf);
            //=====Cách 2
            ByteBuffer dbuf = ByteBuffer.allocate(2);
            dbuf.putShort((short) i);
            byte[] bytes = dbuf.array();
            out.writeByte(bytes[0]);
            out.writeByte(bytes[1]);
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

            ByteBuffer wrapped = ByteBuffer.wrap(buf1);
            int num = Short.toUnsignedInt(wrapped.getShort());
            System.out.print(num +" ");
        }
        in.close();



    }

}
