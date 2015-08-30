   

var webmonitor = angular.module('webmonitor', ['ngResource']);

 //google.load('visualization', '1.1', {packages: ['line']});
 google.load('visualization', '1', {packages:['corechart']});
 google.setOnLoadCallback(function() {angular.bootstrap(document.body, ['webmonitor']);
});



webmonitor.factory('urlResponse', function($resource) {
    return $resource('http://localhost:8080/application/responses', {})
	});

webmonitor.controller('webmonitorController', ['$scope', '$interval', 'urlResponse', function($scope, $interval, urlResponse) {

	$scope.data = {};

	var options = {
	title:'Website Response Performance',
	width:1200,
	height:300,
	sortAscending: false,
	vAxis: {viewWindow: {min: 0, max: 2}, title: 'response time (seconds)'},
	hAxis: {ticks: [0,-3,-6,-9,-12,-15,-18,-21,-24,-27], gridlines: { count: 10 }, title:'poll interval (seconds)'}
	};

	var chart = new google.visualization.LineChart(document.getElementById('chartdiv'))



	$interval(function(){

		var dtable = new google.visualization.DataTable();
		dtable.addColumn('number', 'Interval');
		dtable.addColumn('number', 'Response Time');



		urlResponse.query(function(data) {$scope.urlresponses = data;});

		var i = 0;

		angular.forEach($scope.urlresponses, function(urlresponse){

 	      console.log(urlresponse.responseDate);

      	dtable.addRow([i, urlresponse.responseDuration]);

				i-=3;
		})

		
		chart.draw(dtable, options);


	},3000);


}]);

























