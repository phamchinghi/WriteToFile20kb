import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.lang.Integer;
import java.util.Base64;

public class Main {



    public static void main(String[] args) throws Exception {


//==============================Write to file  usage=============================
        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\mydata.txt"));
        for (int i = 50000; i <= 60000; i++) {
//           out.write(i); //10kb
//           out.writeInt(i); //40kb
            out.writeShort(i); //20kb
        }
        out.close();
//===============================Read file  usage========================
        DataInputStream in = new DataInputStream(new FileInputStream("D:\\mydata.txt"));
        while (in.available() > 0) {
//            System.out.println(in.readInt());
            System.out.println(in.readUnsignedShort());
        }
        in.close();
    }

}
