import bitreaderwriter.BitReader;
import bitreaderwriter.BitWriter;

public class Decode {
    int n, e, d;

    public Decode(int d) {

        this.d = d;
    }

    public int[] readFile(String inputFileName) {

        int keys[] = new int[8];
        BitReader bitReader = new BitReader(inputFileName);
        BitWriter bitWriter = new BitWriter(getOutputFileName(inputFileName));

        e = bitReader.ReadNBits(32);
        n = bitReader.ReadNBits(32);

        for (int i = 0; i < 8; i++) {
            int encryptedKey = bitReader.ReadNBits(32);
            keys[i] = Code.rsa(encryptedKey, d, n);
        }

        int charactersNumber = bitReader.fileLength / 4 - 10;
        for (int i = 0; i < charactersNumber; i++) {

            int cryptedCharacter = bitReader.ReadNBits(32);
            int decryptedCharanter = keys[i % 8] ^ cryptedCharacter;
            bitWriter.WriteNBits(decryptedCharanter, 8);
        }
        return keys;
    }

    private String getOutputFileName(String inputFile) {

        /* filename.bmp[predictionNumber].pre  */
        String[] parts = inputFile.split("\\\\");
        String outputFile = parts[parts.length - 1];
        System.out.println(outputFile);
        parts = outputFile.split("\\.");
        outputFile = "C://Users//lidia//Desktop//criptare//" + parts[0] + "." + parts[1] + ".myDeCrypt.";

        return outputFile;
    }
}
