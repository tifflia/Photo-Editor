import java.util.Scanner;

public class Project {
    public static void main(String[] args) throws InterruptedException{
        String fileName = args[0];

        //checking number/validity of command line arguments
        if(args.length != 1) {
            System.err.println("Please input only one image file name.");
            System.exit(1);
        }
        //check to see if the image file is a supported image format (JPEG, PNG, GIF, TIFF, and BMP)
        if(!(fileName.toLowerCase().indexOf(".jpeg") > 0 || fileName.toLowerCase().indexOf(".jpg") > 0 || fileName.toLowerCase().indexOf(".png") > 0 || fileName.toLowerCase().indexOf(".gif") > 0 || fileName.toLowerCase().indexOf(".tiff") > 0 || fileName.toLowerCase().indexOf(".bmp") > 0)) {
            System.err.println("Please input a valid image file. The supported image formats are: JPEG, PNG, GIF, TIFF, and BMP.");
            System.exit(1);
        }

        //initialize Picture object
        Picture picture = new Picture(fileName);
        picture.show();

        //initialize Scanner object
        Scanner input = new Scanner(System.in);

        while (true) {
            //OPTION PROMPT: ask user to edit or save
            System.out.println("\nChoose a filter to apply by typing the corresponding number:");
            System.out.println("[1] Blur");
            System.out.println("[2] Sharpen");
            System.out.println("[3] Invert");
            System.out.println("[4] Black & White");
            System.out.println("or\n[5] Finish & Save");

            int choice = input.nextInt();
            //need to add a buffer that reads the new line after pressing enter
            String buffer = input.nextLine();

            //BLUR
            if(choice == 1) {
                int intensity = 0;
                while(intensity == 0) {
                    System.out.println("\nEnter the blur intensity from 1-10:");
                    intensity = input.nextInt();
                    if(intensity < 1 || intensity > 10) {
                        System.err.println("Please enter an intensity within the range 1-10.");
                        intensity = 0;
                    }
                    else {
                        System.out.print("Loading");
                        for(int i = 0; i < 3; i++) {
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
            else if(choice == 2) {
                int intensity = 0;
                while(intensity == 0) {
                    System.out.println("\nEnter the sharpen intensity from 1-10:");
                    intensity = input.nextInt();
                    if(intensity < 1 || intensity > 10) {
                        System.err.println("Please enter an intensity within the range 1-10.");
                        intensity = 0;
                    }
                    else {
                        System.out.print("Loading");
                        for(int i = 0; i < 3; i++) {
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
            else if(choice == 3) {
                Filters.invert(picture);

                System.out.println("Filter applied!");
                StdAudio.play("glitter.wav");
            }
            //B&W
            else if(choice == 4) {
                Filters.bw(picture);

                System.out.println("Filter applied!");
                StdAudio.play("glitter.wav");
            }
            //SAVE & FINISH
            else if(choice == 5) {
                picture.savePopUp();
            }
            else {
                System.err.println("Please input a valid choice (1-5).");
            }
        }
    }
}