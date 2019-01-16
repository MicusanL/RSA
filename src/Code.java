import bitreaderwriter.BitReader;
import bitreaderwriter.BitWriter;

public class Code {

    int p, q, n, e, d, lamda;


    public Code(int n, int e) {

        this.n = n;
        this.e = e;

        //chosePQ();

        //-----
        this.p = 11;
        this.q = 13;
        //-----
        if (n != p * q) {
            System.out.println("incorrect N");
        }


        lamda = (p - 1) * (q - 1);
        if (cmmdc(e, lamda) != 1) {
            System.out.println("incorrect Lamda");
        }

        this.d = d;
        if (d * e % lamda != 1) {
            System.out.println("incorrect D");
        }

    }

    private int cmmdc(int a, int b) {
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private void chosePQ() {

        int p = (int) (Math.random() * Constant.MULTIPLICATION_FACTOR);
        while (primNumber(p) == false) {
//            System.out.println(p);
            p = (int) (Math.random() * Constant.MULTIPLICATION_FACTOR);
        }
        this.p = p;

        int q = (int) (Math.random() * Constant.MULTIPLICATION_FACTOR);
        while (primNumber(q) == false) {
//            System.out.println(q);
            q = (int) (Math.random() * Constant.MULTIPLICATION_FACTOR);
        }
        this.q = q;


    }

    private boolean primNumber(double number) {

        for (int i = 2; i <= number / 2; ++i) {
            // condition for nonprime number
            if (number % i == 0) {
                return false;
            }
        }
        return true;

    }

    public static int rsa(int b, int x, int n) {
        long r = 1;

        for (int k = 1; k <= x; k++) {
            r *= b;
            r %= n;
        }
      /*  while (x != 0) {
            if (x % 2 == 1) {
                r *= b;
                r %= n;
            }
            b *= b;
            b %= n;
            x /= 2;
        }*/

        return (int) r;
    }

    private int[] getKeys() {
        int keys[] = new int[8];

        for (int i = 0; i < 8; i++) {
            do {
                keys[i] = (int) (Math.random() * Constant.MULTIPLICATION_FACTOR);
            } while (keys[i] >= n);
        }
        return keys;
    }

    public void cryptFile(String inputFile) {

        BitReader bitReader = new BitReader(inputFile);
        BitWriter bitWriter = new BitWriter(getOutputFileName(inputFile));

        int charactersRead = 0;
        int keys[] = getKeys();

        bitWriter.WriteNBits(e, 32);
        bitWriter.WriteNBits(n, 32);
        System.out.println("-------------Keys-------------");

        for (int i = 0; i < 8; i++) {
            System.out.println(keys[i]);
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            int c = rsa(keys[i], e, n);
            bitWriter.WriteNBits(c, 32);
        }
        while (charactersRead != bitReader.fileLength) {

            charactersRead++;
            int m = bitReader.ReadNBits(8);
            int mCrypted = keys[charactersRead % 8] ^ m;
            bitWriter.WriteNBits(mCrypted, 32);

        }

    }

    private String getOutputFileName(String inputFile) {

        /* filename.bmp[predictionNumber].pre  */
        String[] parts = inputFile.split("\\\\");
        String outputFile = parts[parts.length - 1];
        parts = outputFile.split("\\.");
        outputFile = "C://Users//lidia//Desktop//criptare//" + parts[0] + "." + parts[1] + ".myCrypt";

        return outputFile;
    }
}
