package utils;

import java.io.File;

public class ReportUtils {

    private static final String REPORT_DIR = "reports";

    public static void cleanOldReports() {

        File dir = new File(System.getProperty("user.dir") + File.separator + REPORT_DIR);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Report directory does not exist. Skipping cleanup.");
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".html"));

        if (files == null || files.length == 0) {
            System.out.println("No old reports found to delete.");
            return;
        }

        for (File file : files) {
            if (file.delete()) {
                System.out.println("Deleted old report: " + file.getName());
            } else {
                System.out.println("Failed to delete: " + file.getName());
            }
        }
    }
}
