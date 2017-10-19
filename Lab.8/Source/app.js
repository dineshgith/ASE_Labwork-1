
angular.module("app",[])
.controller('controller',function ($scope,$http) {
    $scope.search=function () {

        $http.get("http://127.0.0.1:8888/calories",{params: {name:$scope.input1}}).success(function (data) {

           if (data != null) {
               $scope.final= {
                   "score": data.results[0].calories,
                   "image": data.results[1].link,
                   "qt":data.results[0].quantity,
                   "text1":data.results[1].text1
               }
           }
        });
           document.getElementById("col").style.display='block';


    }
})