<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="browsermode" content="application">
		<meta name="renderer" content="webkit">
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>数据通上报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="${ctx}/casher/js/global.js" type="text/javascript" charset="utf-8"></script>
				<link rel="stylesheet" href="${ctx}/casher/css/swiper.css"></link>
<script type="text/javascript" src="${ctx}/js/comUtil.js"></script>  
</head>

	<body>
		<!-- 顶部菜单  -->
		<div class="swiper-container swiper-container1">
			<div class="swiper-wrapper" id="menu">
				<div class="swiper-slide on">项目信息</div>
				<div class="swiper-slide">项目问题</div>
				<div class="swiper-slide">项目情况</div>
				<div class="swiper-slide">采集接口</div>
				<div class="swiper-slide">收银机接口</div>
				<div class="swiper-slide">验收情况</div>
			</div>
		</div>
		<!-- 内容区 -->
		<div class="swiper-container swiper-container2">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">项目名称：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">项目版本：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">项目经理：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">项目状态：</div>
							<div class="iz-list-content">维护</div>
						</li>
						<li>
							<div class="iz-list-title">实施/维护人员：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">更新时间：</div>
							<div class="iz-list-content">2017-02-26</div>
						</li>
					</ul>	
				</div>
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">海鼎跟进：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">海鼎-商务：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">海鼎-技术：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">海鼎-跟进：</div>
							<div class="iz-list-content">维护</div>
						</li>
						<li>
							<div class="iz-list-title">客户跟进：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">其他：</div>
							<div class="iz-list-content">2017-02-26</div>
						</li>
					</ul>	
				</div>
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">合同数量：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">需安装数量：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">已安装数量：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">验收数量：</div>
							<div class="iz-list-content">维护</div>
						</li>
						<li>
							<div class="iz-list-title">未上线数量：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
					</ul>	
				</div>
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">软件数据通：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">硬件数据通：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">其他：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
					</ul>	
				</div>
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">USB：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">串口：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">并口：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">网口：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
						<li>
							<div class="iz-list-title">其他：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">验收率：</div>
							<div class="iz-list-content">上海迪士尼</div>
						</li>
						<li>
							<div class="iz-list-title">技术准确率：</div>
							<div class="iz-list-content">2.2.2</div>
						</li>
						<li>
							<div class="iz-list-title">安装率：</div>
							<div class="iz-list-content">吴佩</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 保存按钮 -->
		<div class="g-ok">
			<div id="save">保存</div>
		</div>
		
		<script src="${ctx}/casher/js/swiper.js" type="text/javascript" charset="utf-8"></script>
		<script>
			swiper1 = new Swiper('.swiper-container1', {	//菜单区滑块
				paginationClickable: true,
				slidesPerView: 'auto',
				freeMode: true
			});
			
			swiper2 = new Swiper('.swiper-container2', {	//内容区滑块
				autoHeight: true,
				onSlidePrevStart: function(swiper) {
					app.upStyle(swiper.activeIndex);
				},
				onSlideNextStart: function(swiper) {
					app.upStyle(swiper.activeIndex);
				}
			});	
		</script>
	</body>
</html>