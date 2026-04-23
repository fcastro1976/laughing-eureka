package com.newdex.services.inclusivecontent.domain;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/FileServiceClient;", "", "fileServiceUrl", "", "(Ljava/lang/String;)V", "restTemplate", "Lorg/springframework/web/client/RestTemplate;", "downloadAudio", "", "fileId", "Ljava/util/UUID;", "uploadAudio", "bytes", "filename", "inclusive-content-service"})
public class FileServiceClient {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileServiceUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final org.springframework.web.client.RestTemplate restTemplate = null;
    
    public FileServiceClient(@org.springframework.beans.factory.annotation.Value(value = "${inclusive-content.file-service.url:http://file-service:8080}")
    @org.jetbrains.annotations.NotNull()
    java.lang.String fileServiceUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.UUID uploadAudio(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] downloadAudio(@org.jetbrains.annotations.NotNull()
    java.util.UUID fileId) {
        return null;
    }
}