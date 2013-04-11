package tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import java.io.*;

public class ProductTag extends TagSupport {
	
	public int doStartTag() throws javax.servlet.jsp.JspException {
		HttpServletRequest 	req;
   		HttpJspPage  		g;
   		JspWriter			out;
   				
   		req = ( HttpServletRequest )pageContext.getRequest();
   				
   		try {
     		out = pageContext.getOut();
     		out.print("");
   		} catch(IOException ioe) {
     		throw new JspException( "I/O Error : " + ioe.getMessage() );
   		}//end try/catch

    	return Tag.SKIP_BODY;
	}
	
}
