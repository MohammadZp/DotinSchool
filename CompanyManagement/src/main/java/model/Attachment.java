package model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private byte[] attachment;
    private String format;
    private String fileName;

    public Attachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Attachment() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
