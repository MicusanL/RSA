import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private JPanel panel;
    private JTextField textFieldN;
    private JTextField textFieldE;
    private JButton buttonCrypt;
    private JTextField textFieldD;
    private JButton buttonDecrypt;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserInterface");
        frame.setContentPane(new UI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


    public UI() {

        /*int b = 31, x = 3, n = 7;
        System.out.println(b + " " + x + " " + n + " " + Code.rsa(b, x, n));*/

        buttonCrypt.addActionListener(e -> {
            {

                int N = Integer.parseInt(textFieldN.getText());
                int E = Integer.parseInt(textFieldE.getText());
                // int D = Integer.parseInt(textFieldD.getText());

                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    String inputFile = fileChooser.getSelectedFile().toString();
                    Code code = new Code(N, E);
                    code.cryptFile(inputFile);
                    System.out.println("End of file");
                }

            }
        });

        buttonDecrypt.addActionListener(e -> {
            {

//                int N = Integer.parseInt(textFieldN.getText());
//                int E = Integer.parseInt(textFieldE.getText());
                int D = Integer.parseInt(textFieldD.getText());

                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    String inputFile = fileChooser.getSelectedFile().toString();
                    Decode decode = new Decode(D);
                    int keys[] = decode.readFile(inputFile);

                    System.out.println("-------------Keys-------------");
                    for (int i = 0; i < 8; i++) {
                        System.out.println(keys[i]);
                    }
                    System.out.println();
                }
               /* int index = Integer.parseInt(textIndex.getText());
                int fullDictionaryAction = comboBoxFullDictionaryAc.getSelectedIndex();

                Code lzwCode = new Code();
                lzwCode.codeUsingLZW(index, fullDictionaryAction);*/


            }
        });
    }
}
