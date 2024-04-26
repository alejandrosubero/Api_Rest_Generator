package com.api.wiki.error;

import com.api.wiki.utility.TaskSate;
import org.springframework.stereotype.Component;

@Component
public class DeveloperErrorMenssage {

    private static final String Is400 ="400";
    private static final String menssageNoFound = "The Developer no exist";

    public String noFound(String menssage){
        if (menssage.equals(TaskSate.IS_NO_FOUND.toString())){
            return "400";
        }
        return null;
    }


    public String builErrorMessage(String menssage){
        if(menssage.equals(this.Is400)){
            return menssageNoFound;
        }
        return null;
    }
}
