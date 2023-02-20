package framework.utils;

import framework.Logger;

import java.io.File;
import java.util.Arrays;

public class FileManager {

    private static final Logger logger = Logger.getInstance();
    public static void deleteOldFiles(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if(file.exists())
            {
                file.delete();
                logger.info("FileManager.deleteOldFiles.deleted");
            }
        }
        catch(Exception e)
        {
            logger.error("FileManager.deleteOldFiles.failed");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean fileExists(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if(file.exists())
            {
                logger.error("FileManager.fileExists.true");
                return true;
            }
            logger.error("FileManager.fileExists.false");
            return false;
        }
        catch(Exception e)
        {
            logger.error("FileManager.fileExists.failed.verification");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void waitTillFIleDownloaded(String fileFolder, String fileExtension) {
        File file = new File(fileFolder);
        var timer = 0;

        while (checkFileDownloading(file, fileExtension) && timer<2000) {
            try {
                logger.info("The file is being downloaded");
                Thread.sleep(500);
                timer+=500;
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                logger.error(Arrays.toString(e.getStackTrace()));
                throw new RuntimeException(e);
            }

            file = new File(fileFolder);
        }
    }

    public static void waitTillFIleDownloadingStarted(String fileFolder, String fileExtension)
    {
        File file = new File(fileFolder);
        var timer = 0;

        while (!checkFileDownloading(file, fileExtension) && timer<2000) {
            try {
                Thread.sleep(500);
                timer+=500;
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                logger.error(Arrays.toString(e.getStackTrace()));
                throw new RuntimeException(e);
            }

            file = new File(fileFolder);
        }
    }

    private static Boolean checkFileDownloading(File fileFolder, String fileExtension)
    {
        File[] filesInList = fileFolder.listFiles();
        boolean found = false;

        assert filesInList != null;
        for (File file :filesInList) {
            if (file.isFile()){
                String name = file.getName();
                if(name.endsWith(fileExtension)){
                    found = true;
                    break;
                }
            }
        }

        return found;
    }
}
