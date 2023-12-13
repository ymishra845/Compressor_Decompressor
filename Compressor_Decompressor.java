import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Compressor_Decompressor extends JFrame implements ActionListener {

    JButton compressButton;
    JButton decompressButton;

    public Compressor_Decompressor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Compression and Decompression Project");
        this.setSize(400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Compression and Decompression Project");
        titleLabel.setBounds(20, 10, 350, 20);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        compressButton = new JButton("Compress File");
        compressButton.setBounds(50, 50, 120, 30);
        compressButton.addActionListener(this);

        decompressButton = new JButton("Decompress File");
        decompressButton.setBounds(200, 50, 150, 30);
        decompressButton.addActionListener(this);

        panel.add(titleLabel);
        panel.add(compressButton);
        panel.add(decompressButton);

        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Compressor_Decompressor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compressButton) {
            compressFile();
        } else if (e.getSource() == decompressButton) {
            decompressFile();
        }
    }

    private void compressFile() {
        // Implement file compression logic here
        // Display a file chooser for selecting the file to compress
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile);
            try (FileInputStream fis = new FileInputStream(selectedFile);
                    FileOutputStream fos = new FileOutputStream(selectedFile.getAbsolutePath() + ".gz");
                    GZIPOutputStream gzip = new GZIPOutputStream(fos)) {

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    gzip.write(buffer, 0, len);
                }

                // Display a success message using JOptionPane
                JOptionPane.showMessageDialog(this, "File compressed successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void decompressFile() {
        // Implement file decompression logic here
        // Display a file chooser for selecting the file to decompress
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile);
            try (FileInputStream fis = new FileInputStream(selectedFile);
                    GZIPInputStream gzip = new GZIPInputStream(fis);
                    FileOutputStream fos = new FileOutputStream(selectedFile.getAbsolutePath().replace(".gz", ""))) {

                byte[] buffer = new byte[1024];
                int len;
                while ((len = gzip.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

                // Display a success message using JOptionPane
                JOptionPane.showMessageDialog(this, "File decompressed successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
