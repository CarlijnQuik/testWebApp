import java.io.File;
import java.io.FilenameFilter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class fileLister
{
    public static void main(String[] args)
    {
        // list input files
        File folder = new File(args[0]);
        File[] files = folder.listFiles();

        loopThroughFiles(files);

    }

    public static void loopThroughFiles(File[] inputFile){
        for (File file : inputFile)
        {
            File[] nestedFiles = file.listFiles();

            if(nestedFiles != null) {
                loopThroughFiles(nestedFiles);

            }

            writeNames(file);

        }
    }

    public static void writeNames(File nameToWrite)
    {
        String fileName = nameToWrite.getPath();
    
        if(fileName.endsWith(".java")) {

            //File fileToWrite = new File("C:\\Users\\CQUIK95\\Documents\\ShoppingCart\\javaClasses.txt");
            System.out.println(fileName);

            //try {
              //  FileWriter fw = new FileWriter(fileToWrite, true);
               // fw.write(fileName + " ");
                //fw.close();

            //} catch (Exception e) {
              //  System.out.println(e);

            //}

        }

    }

}

