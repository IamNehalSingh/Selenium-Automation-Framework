package email;

import java.io.File;
import java.util.List;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import utils.TestResultManager;
import utils.TestResultManager.TestResult;

public class EmailSender {

    public static void sendEmailWithAttachment(File reportFile) {

        final String senderEmail = "nehalsinghparmar@gmail.com";
        final String appPassword = "rivnivijzimqmluu";
        final String receiverEmail = "iamnehalsingh01@gmail.com";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail)
            );
            message.setSubject("Automation Test Execution Report");

            // 🔹 Build HTML table dynamically
            String tableRows = buildTestResultRows();

            String emailBody =
                    "<h2>Automation Test Execution Summary</h2>" +
                    "<table border='1' cellpadding='8' cellspacing='0' style='border-collapse:collapse'>" +
                    "<tr style='background-color:#f2f2f2'>" +
                    "<th>S.No</th>" +
                    "<th>Test Case</th>" +
                    "<th>Status</th>" +
                    "</tr>" +
                    tableRows +
                    "</table>" +
                    "<br><p>Please find the attached Extent Report for detailed execution logs.</p>" +
                    "<br><b>Regards,</b><br>Nehal Singh Parmar";

            MimeBodyPart body = new MimeBodyPart();
            body.setContent(emailBody, "text/html");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(new FileDataSource(reportFile)));
            attachment.setFileName(reportFile.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String buildTestResultRows() {

        List<TestResult> results = TestResultManager.getResults();
        StringBuilder rows = new StringBuilder();

        int index = 1;
        for (TestResult result : results) {

            String statusIcon = result.isPassed ? "✅ PASS" : "❌ FAIL";
            String statusColor = result.isPassed ? "green" : "red";

            rows.append("<tr>")
                .append("<td>").append(index++).append("</td>")
                .append("<td>").append(result.testName).append("</td>")
                .append("<td style='color:").append(statusColor).append("'>")
                .append(statusIcon)
                .append("</td>")
                .append("</tr>");
        }

        return rows.toString();
    }
}
