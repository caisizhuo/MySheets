<%@page import="java.util.Map"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap Admin Theme v3</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/css/handsontable.full.min.css" />
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/css/styles.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/handsontable.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<!-- Logo -->
					<div class="logo">
						<h1>
							<a href="index.html">Bootstrap Admin Theme</a>
						</h1>
					</div>
				</div>
				<div class="col-md-7">
					<div class="navbar navbar-inverse" role="banner">
						<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
							<ul class="nav navbar-nav navbar-right">
								<li>
									<a>
										<span class="glyphicon glyphicon-user"></span>
										用户:
									</a>
								</li>
								<li>
									<a class="userName">XXX</a>
								</li>
								<li>
									<a href="#">
										<span class="glyphicon glyphicon-log-in"></span>
										注销
									</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="page-content">
		<div class="row">
			<div class="col-md-2">
				<div class="sidebar content-box" style="display: block;">
					<ul class="nav" id="menu">
						<!-- Main menu -->
						<li class="submenu">
							<a href="#">
								<i class="glyphicon glyphicon-list"></i>
								Pages
								<span class="caret pull-right"></span>
							</a>
							<!-- Sub menu -->
							<ul>
								<li>
									<a href="login.html">Login</a>
								</li>
								<li>
									<a href="signup.html">Signup</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-10">
				<div class="content-box-large">
					<div id="container" style="margin:0;width:100;">
						<div id="sheets"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="container">
			<div class="copy text-center">
				Copyright 2014
				<a href='#'>Website</a>
			</div>
		</div>
	</footer>
</body>
<script>
	var tableDetail = '<%=JSON.toJSONString(session.getAttribute("tableDetail"))%>';
	tableDetail = JSON.parse(tableDetail);

	$(document).ready(function() {
		var $container = $("#sheets");

		//加载菜单
		for (table in tableDetail)
		{
			$("#menu").append('<li class="current"><a class="tableLink" href="javascript:void(0);"><i class="glyphicon glyphicon-home"></i>' + table + '</a></li>');
		}
		//点击菜单加载表格事件 
		$("a.tableLink").click(function() {

			if (confirm("是否打开点击表格？"))
			{
				//当前选中表
				var $table = $(this).text();

				$.get("LoadDataServlet", "table=" + $table, function(data, status) {
					data = JSON.parse(data);

					$container.handsontable({
						data : data, //数据
						licenseKey : 'non-commercial-and-evaluation',
						colHeaders : tableDetail[$table], //显示表头
						contextMenu : true, //显示表头下拉菜单
						manualRowResize : true, //自定义行宽
						manualColumnResize : true, //自定义列高
						manualColumnMove : false, //是否能拖动列
						manualRowMove : false, //是否能拖动行
						columnSorting : true, //当值为true时，表示启用排序插件
						rowHeaders : true, //是否显示行数字
						contextMenu : true, //右键显示更多功能,
						minSpareRows : 0, //空出多少行
						autoColumnSize : true, //当值为true且列宽未设置时，自适应列大小
						stretchH : "all", //last:延伸最后一列,all:延伸所有列,none默认不延伸。
						autoWrapRow : true,
						manualColumnFreeze : true, //手动设置冻结列
						hiddenColumns : { // 隐藏列
							columns : [ 0 ],
							indicators : true
						},


						afterChange : function(change, source) {
							//change row:00,col01,old:02,new:03
							//alert(source);

							if (source === 'loadData')
							{
								return; //don't save this change
							}
							for (var i = 0; i < change.length; i++)
							{
								if (change[i][2] != change[i][3])
								{
									//alert($container.handsontable('getDataAtCell', change[0][0], 0));
									var update = {
										tableName : $table,
										primaryKeyName : tableDetail[$table][0],
										primaryKeyValue : $container.handsontable('getDataAtCell', change[i][0], 0),
										changeValueHeader : tableDetail[$table][change[i][1]],
										changeValue : change[i][3]
									};

									$.post("SaveDataServlet",
										JSON.stringify(update),
										"json");
								}
							}
						}
					});
				});
			}
		})
	});
</script>
</html>