package com.hc.web;


import com.hc.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;
import java.util.Map;

/**
 * base controllers
 * @author hc
 *
 */
public abstract class BaseController {
	private static Log log = LogFactory.getLog(BaseController.class);
	public static final int DEFAULT_PAGE_SIZE = 10;

	protected final static int COMMON_SUCCESS = 1;

	private Log logger;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor(DateUtils.getDefaultTimePattern()));
    }

	/**
	 * return common format error code for AJAX calls
	 * 
	 * client side JS should extend $app.errors to register error message
	 * 
	 * Example:
	 * 
	 *    server side:
	 *          return ajaxErrorCode("custom_error");
	 *          
	 *    client side:
	 *    
	 *          $.extend($app.errors, {
	 *          	custom_error: 'this is a custom error message'
	 *          });
	 *          
	 * then the error message will be properly displayed when calling $app.ajax and $.ajaxSubmit
	 * 
	 * @param errorCode client side error code
	 * @return
	 */
	protected Object ajaxErrorCode(String errorCode) {
		AjaxResult<String> ae = new AjaxResult<String>();
		ae.setErrorCode(errorCode);
		return ae;
	}
	
	/**
	 * return common format error for AJAX calls
	 * 
	 * this method will directly return error message to client side, client side js does not need to register error to $app.errors
	 * 
	 * @param error error message
	 * @return
	 */
	protected Object ajaxError(String error) {
		AjaxResult<String> ae = new AjaxResult<String>();
		ae.setError(error);
		return ae;
	}

	/**
	 * simply return a success notification without any extra data
	 * @return
	 */
	protected <T> Object ajaxSuccess(T data) {
		AjaxResult<T> result = new AjaxResult<T>();

		result.setData(data);

		return result;
	}
	
	/**
	 * simply return a success notification without any extra data
	 * @return
	 */
	protected Object ajaxSuccess() {
		AjaxSuccess as = new AjaxSuccess();
		as.setReturnCode(COMMON_SUCCESS);
		
		return as;
	}

	protected Object ajaxSuccessReturnMap(Map<String,Object> map) {
		return map;
	}


	/**
	 * get instance logger
	 * @return
	 */
	protected Log getLogger() {
		if(logger==null)
			logger = LogFactory.getLog(this.getClass());
		return logger;
	}
	

}