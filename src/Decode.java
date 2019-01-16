import bitreaderwriter.BitReader;
import bitreaderwriter.BitWriter;

public class Decode {
    int n, e, d;

    public Decode(int d) {

        this.d = d;
    }

    public int[] readFile(String fileName) {

        int keys[] = new int[8];
        BitReader bitReader = new BitReader(fileName);
        e = bitReader.ReadNBits(32);
        n = bitReader.ReadNBits(32);
        for (int i = 0; i < 8; i++) {
            int encryptedKey = bitReader.ReadNBits(32);
            keys[i] = Code.rsa(encryptedKey, d, n);
        }

        return keys;
    }

    private String getOutputFileName(String inputFile) {

        /* filename.bmp[predictionNumber].pre  */
        String[] parts = inputFile.split("\\\\");
        String outputFile = parts[parts.length - 1];
        parts = outputFile.split("\\.");
        outputFile = "C://Users//lidia//Desktop//criptare//" + parts[0] + "." + ".myCrypt";

        return outputFile;
    }
}
