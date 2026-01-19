package utils;

import java.io.File;
import email.EmailSender;

public class EmailUtils {

    public static void sendTestReport(String reportPath) {

        File reportFile = new File(reportPath);

        if (!reportFile.exists()) {
            throw new RuntimeException("Report file not found: " + reportPath);
        }

        EmailSender.sendEmailWithAttachment(reportFile);
    }
    
}
