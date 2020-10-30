package yyyq.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import yyyq.common.model.SiteModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DemoClassDes
 *
 * @author liyunzhi
 * @date 2018/8/2 17:44
 */
public class BaseController {
    @Value("${yyyq.site.title}")
    private String siteTitle;

    @Value("${yyyq.site.subtitle}")
    private String siteSubTitle;

    @Value("${yyyq.site.logo.url}")
    private String siteLogoUrl;

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 初始化Req&Res
     * @param request
     * @param response
     */
    @ModelAttribute
    public void initReqAndResp(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        SiteModel siteModel=new SiteModel();
        siteModel.title=siteTitle;
        siteModel.subTitle=siteSubTitle;
        siteModel.logoUrl=siteLogoUrl;
        request.setAttribute("site", siteModel);
        this.response = response;
    }
}
