package br.com.project.labtrack.infra.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadQRCode(byte[] qrCodeBytes, String publicId) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                qrCodeBytes,
                ObjectUtils.asMap(
                        "public_id", publicId,
                        "folder", "qrcode",
                        "resource_type", "image"
                )
        );

        return (String) uploadResult.get("secure_url");
    }
}
