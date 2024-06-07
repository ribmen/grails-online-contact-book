package com.hmtmcse.ocb

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile

class FileUtil {

    public static String getRootPath(){
        File file = new File(Holders.config.grails.app.upload.path);
        String path = file.getAbsolutePath();
        return path  //ESTÁ FUNCIONANDO
    }

    String path = getRootPath()


    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }

//    request.getFile("productFile")
    public static String uploadContactImage(Integer contactId, MultipartFile multipartFile){
        if (contactId && multipartFile){
            String contactImagePath = "${getRootPath()}"
            makeDirectory(contactImagePath)
            multipartFile.transferTo(new File(contactImagePath, contactId + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return "error"
    }

    public static boolean deleteContactImage(Integer contactId, String imageName) {
        if(contactId && imageName) {
            String contactImagePath = "${getRootPath()}"
            File imageFile = new File(contactImagePath, "${contactId}-${imageName}")
            if(imageFile.exists()) {
                boolean deleted = imageFile.delete()
                return deleted
            }
        }
        return false

    }
}
