import com.sun.jdi.ShortType;

import java.io.*;
import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) throws Exception {
//==============================Write to file  usage=====================
//        write2Bytetofile("D:\\mydata2.dat");
//===============================Read file  usage========================
        read2Bytefromfile("D:\\mydata2.dat");
    }


    private static void write2Bytetofile(String fileName) throws FileNotFoundException {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            byte[] buf = new byte[2];
            for (int i = 50000; i <= 60000; i++) {
//---------------------------------------------------Cách đầu tiên
                //out.writeShort(i); //20kb

//----------------------------------------------Cách 2---- không được dùng bất cứ từ short nào
//                ByteBuffer dbuf = ByteBuffer.allocate(2);// tạo 1 bộ đệm mới gồm 2 byte
//                dbuf.putShort((short) i);//ghi gtri i vào mảng buf theo kiểu short
//                byte[] bytes = dbuf.array();//.array trả về mảng byte
//                out.writeByte(bytes[0]);//ghi xuống 1 byte -> ghi vào mảng buf
//                out.writeByte(bytes[1]);

//-----------------------------------------------Cách 3---- dùng được

                buf[0] = (byte)(i >> 8);
                buf[1] = (byte)(i & 0xFF);
                out.write(buf);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    private static void read2Bytefromfile(String fileName) throws FileNotFoundException {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(fileName));
            byte[] buf1 = new byte[2];// tạo mảng ký tự chứa 2 byte đọc ra

            while (in.available() > 0)
            {
//--------------------------Đọc theo cách dùng được
                buf1[0] = in.readByte();//bỏ byte đầu vào
                buf1[1] = in.readByte();//bỏ byte sau vào
                int num = ((buf1[0] & 0xff) << 8) | (buf1[1] & 0xff);
                System.out.print(num + " ");

//--------------------------Đọc theo cách 2
//            ByteBuffer wrapped = ByteBuffer.wrap(buf1);//gói mảng byte đó vào bộ đệm
//            int num = Short.toUnsignedInt(wrapped.getShort());
//            System.out.print(num +" ");

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
