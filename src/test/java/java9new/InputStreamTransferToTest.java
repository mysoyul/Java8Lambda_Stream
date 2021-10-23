package java9new;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputStreamTransferToTest {

    @Test
    public void testTransferTo() throws IOException {

        byte [] inBytes = "Hello Java 9".getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

             bis.transferTo(bos);

            byte[] outBytes = bos.toByteArray();

            assertTrue(Arrays.equals(inBytes,outBytes));

        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    @Test
    public void testTransferToJava9Syntax() throws IOException {
        byte [] inBytes = "Hello Java 9".getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

//        try (bis; bos) {
//
//            long count = bis.transferTo(bos);
//
//            byte[] outBytes = bos.toByteArray();
//            assertTrue(Arrays.equals(inBytes,outBytes));
//        }
    }
}
