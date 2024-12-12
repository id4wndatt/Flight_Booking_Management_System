package com.dnd.fbs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;

public class AbstractClass {
    public boolean isNullorEmpty(String val){
        if(val == null || val.isEmpty())
            return true;
        else return false;
    }
    public boolean isAdmin(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            if(session == null){
                return false;
            }
            if(session.getAttribute("user") != null){
                return false;
            }
            if ((int) session.getAttribute("role") == 2 || (int) session.getAttribute("role") == 3){
                return true;
            }

        } catch (Exception exception){
            System.out.println(exception);
        }
        return false;

    }
    public void clearSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session == null){
            return;
        }
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String attributeName = attributeNames.nextElement();
            System.out.println(attributeName);
            session.removeAttribute(attributeName);
        }
    }

}
