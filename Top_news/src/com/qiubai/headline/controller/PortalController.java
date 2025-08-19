package com.qiubai.headline.controller;

import jakarta.servlet.annotation.WebServlet;

/**
 * 门户控制器
 * 不需要登录，不需要做增删改的门户操作页面的请求放在这里
 * 若当前服务的请求量过大，可以将当前控制器单独部署在一个服务器上实现集群部署分担服务器压力
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
}
