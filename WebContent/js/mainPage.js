
var tableDetail = '<%=JSON.toJSONString(session.getAttribute("tableDetail"))%>';
tableDetail = JSON.parse(tableDetail);

$(document).ready(function() {
	var $container = $("#sheets");

	//加载菜单
	for (table in tableDetail)
	{
		$("#menu").append('<a href="javascript:void(0);">' + table + '</a><br/>');
	}
	//点击菜单加载表格事件
	$("a").click(function() {

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