google.load("visualization", "1", {
	packages : [ "orgchart" ]
});
function drawTree(id,json){ 
	 drawChart(id,json); 
}
function drawChart(id, json) {
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Name');
	data.addColumn('string', 'Manager');
	data.addColumn('string', 'ToolTip');
	data.addRows(JSON.parse(json));
	var chart = new google.visualization.OrgChart(document.getElementById(id));
	chart.draw(data, {
		allowHtml : true
	});
}
