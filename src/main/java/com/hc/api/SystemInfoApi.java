package com.hc.api;

import com.hc.service.SystemService;
import com.hc.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by hc on 2017/4/5.
 */
@Controller
public class SystemInfoApi extends BaseController{
    @Autowired
    SystemService systemService;
}
