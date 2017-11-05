app.controller('MainController', ['$scope', '$http', function($scope, $http) {

    $scope.books = {
        title: "Foodies Diet Hub",
        list: []
    }

    function hasOnlyNumbers(item) {
        return /^[0-9]*$/.test(item)
    }

    $scope.addItem = function (itemList, item) {
        $http.get("https://kgsearch.googleapis.com/v1/entities:search?query=" + item + "&key=AIzaSyA1Sy0t-uNoi-9pnR5ETJnTBGDNvoi6YhE&limit=1&indent=True")
            .then(function (response) {
                mydata = response.data;
                $scope.statuscode = response.status;
                $scope.statustext = response.statusText;
                console.log($scope.content)
                $http.get("https://api.edamam.com/api/nutrition-data?app_id=254d36ea&app_key=383e71430a323ae94ec781cab4b8918a&ingr=1%20large%20" + item)
                    .then(function (response) {
                        mydata = response.data;
                        $scope.statuscode = response.status;
                        $scope.statustext = response.statusText;
                        console.log($scope.content)
                        itemList.push("\n"+"calories:" +mydata.calories )
                        itemList.push("\n"+"calories:" +mydata.quantity )
                    });

                itemList.push( "\n" + "Description:" + mydata.itemListElement[0].result.detailedDescription.articleBody + "\n"
                                   )
            })
    }

}])