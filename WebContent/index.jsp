<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>HSHOP</title>

<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>

</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<%-- 样式 --%>
	<div class="container-fluid">
		<div id="myCarousel" class="carousel slide">
			<!-- 轮播小圆点 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<%-- 轮播图片 --%>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="img/1.jpg">
					<div class="carousel-caption">
						<%-- 轮播图上的文字 --%>
					</div>
				</div>
				<div class="item">
					<img src="img/2.jpg">
					<div class="carousel-caption">
						<%-- 轮播图上的文字 --%>
					</div>
				</div>
				<div class="item">
					<img src="img/3.jpg">
					<div class="carousel-caption">
						<%-- 轮播图上的文字 --%>
					</div>
				</div>
			</div>
			<!-- 上一张 下一张按钮 -->
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</body>
</html>