package holenote.web.controller.file;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("upload")
@PropertySource("classpath:/application.properties")
public class UploadController {
    @Autowired
    Environment env; 
    
    @GetMapping
    public String showform(){
        return "file/upload";
    }
    
    @PostMapping
    public String handlefile(@RequestPart("file") Part part) throws IOException{
        part.write(env.getProperty("uploadpath")+part.getSubmittedFileName());
        return "file/upload";
    }
}
