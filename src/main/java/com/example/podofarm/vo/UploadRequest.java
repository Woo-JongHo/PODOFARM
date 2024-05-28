package com.example.podofarm.vo;

public class UploadRequest {
    private String id;
    private String studyCode;
    private String sourceText;
    private String readmeText;
    private String filename;
    private String commitMessage;

    // 각 필드에 대한 getter 및 setter 메소드

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getReadmeText() {
        return readmeText;
    }

    public void setReadmeText(String readmeText) {
        this.readmeText = readmeText;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}
