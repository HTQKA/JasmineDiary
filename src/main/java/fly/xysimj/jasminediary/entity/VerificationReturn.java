package fly.xysimj.jasminediary.entity;

import lombok.Data;

@Data
public class VerificationReturn {
    private String code;
    private  byte[] image;
}
