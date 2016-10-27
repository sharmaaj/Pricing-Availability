package mobile.bean;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: AEntity Utility Class
Ver    : 1.0
Author : Ajay Sharma
+==================================================================+
* TYPE              : AEntity Bean ClasUtility Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Utility Class is used to handle junk characters received as resonse from
*                     REST Services
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           13-Aug-2016            Ajay Sharma              Final
*********************************************************************************************** */

public class AEntity {
    public AEntity() {
        super();
    }
    
    protected String getAttributeValue(String value) {
        if (value.contains("@xsi")) {
            return "";
        }
        return value;
    }
    
    public String getValue(String value) {
        if (value.contains("@nil")) {
            return "";
        }
        return value;
    }
}
