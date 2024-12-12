package com.dnd.fbs.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.Objects;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMailWithAttachment(String toEmail,String body,String subject,String attachment) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("phong2552001@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        String pdfpath = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\PdfOrders\\" + attachment + ".pdf";
        FileSystemResource fileSystemResource = new FileSystemResource(new File(pdfpath));
        if(fileSystemResource!=null){
            System.out.println(fileSystemResource.getFile().toPath());
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),fileSystemResource);
            javaMailSender.send(mimeMessage);
            System.out.println("Send mail successfully!");
        }
        else{
            System.out.println("File is not found!");
        }
    }
}
