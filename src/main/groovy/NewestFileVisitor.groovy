import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

import static java.nio.file.FileVisitResult.CONTINUE;

public class NewestFileVisitor extends SimpleFileVisitor<Path>
{
    private FileTime newestFileTime;
    private String targetExtension;

    public NewestFileVisitor(String targetExtension)
    {
        this.newestFileTime = FileTime.fromMillis(0);
        this.targetExtension = targetExtension;
    }

    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes basicFileAttributes) throws IOException
    {
        if (filePath.toString().endsWith(targetExtension))
        {
            FileTime lastModified = Files.getLastModifiedTime(filePath);
            println "... "+filePath.toString()+" = "+lastModified;
            // Newer?
            if (lastModified.compareTo(newestFileTime) > 0)
            {
                newestFileTime = lastModified;
            }
        }

        return CONTINUE;
    }

    public String getLastModified()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        long milliseconds = newestFileTime.toMillis();

        return simpleDateFormat.format(milliseconds);
    } // end of method
    
    public static void main(String[] args)
    {
        String folder = System.getProperty("user.home");  
        folder ="/Users/owner/Downloads/Dropbox/";
        NewestFileVisitor fileVisitor = new NewestFileVisitor(".adoc");
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        System.out.println(fileVisitor.getLastModified());
        println "--- the end---"
    } // end of main
} // end of class