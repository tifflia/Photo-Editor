import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class Project {
    public static void main(String[] args) throws InterruptedException, IOException {
        // prompt user to choose image to load
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "tiff", "bmp");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            throw new IOException("Please select a valid image file. The supported image formats are: JPEG, PNG, GIF, TIFF, and BMP.");
        }
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();
        // initialize Picture object
        Picture picture = new Picture(filePath);
        picture.show();
        Scanner input = new Scanner(System.in);
        while (true) {
            // OPTION PROMPT: ask user to edit or save
            System.out.println("\nChoose a filter to apply by typing the corresponding number:");
            System.out.println("[1] Blur");
            System.out.println("[2] Sharpen");
            System.out.println("[3] Invert");
            System.out.println("[4] Black & White");
            System.out.println("or\n[5] Finish & Save");
            int choice = input.nextInt();
            // need buffer that reads the new line after pressing enter
            input.nextLine();
            //BLUR
            if (choice == 1) {
                int intensity = 0;
                while (intensity == 0) {
                    System.out.println("\nEnter the blur intensity from 1-10:");
                    intensity = input.nextInt();
                    if (intensity < 1 || intensity > 10) {
                        System.err.println("Please enter an intensity within the range 1-10.");
                        intensity = 0;
                    }
                    else {
                        System.out.print("Loading");
                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(500);
                            System.out.print(".");
                        }
                        Filters.blur(picture, intensity);
                        System.out.println("\nFilter applied!");
                        StdAudio.play("glitter.wav");
                    }
                }
            }
            //SHARPEN
            else if (choice == 2) {
                int intensity = 0;
                while (intensity == 0) {
                    System.out.println("\nEnter the sharpen intensity from 1-10:");
                    intensity = input.nextInt();
                    if (intensity < 1 || intensity > 10) {
                        System.err.println("Please enter an intensity within the range 1-10.");
                        intensity = 0;
                    }
                    else {
                        System.out.print("Loading");
                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(500);
                            System.out.print(".");
                        }
                        Filters.sharpen(picture, intensity);
                        System.out.println("\nFilter applied!");
                        StdAudio.play("glitter.wav");
                    }
                }
            }
            //INVERT
            else if (choice == 3) {
                Filters.invert(picture);
                System.out.println("Filter applied!");
                StdAudio.play("glitter.wav");
            }
            //B&W
            else if (choice == 4) {
                Filters.bw(picture);
                System.out.println("Filter applied!");
                StdAudio.play("glitter.wav");
            }
            //SAVE & FINISH
            else if (choice == 5) {
                input.close();
                picture.savePopUp();
            }
            else {
                System.err.println("Please input a valid choice (1-5).");
            }
        }
    }
}
