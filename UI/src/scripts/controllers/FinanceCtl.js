define(['footballManager', 'services/FinanceService'], function (footballManager) {

    footballManager.controller('FinanceCtl', function ($scope, FinanceService) {

        var initialLowClass = 0;
        var initialMediumClass = 0;
        var initialHighClass = 0;

        FinanceService.getEconomy().then(function (response) {
            $scope.summary = response.data.summary;
            $scope.balance = response.data.balance;
        });

        FinanceService.getReceipts().then(function (response) {
            $scope.receipts = response.data;
        });

        // FinanceService.getStadiumFinance().then(function (response) {
            $scope.stadium  = {name: "hola", lowClass: 10000, mediumClass: 1000, highClass: 100,
            lowClassPrice: 10, mediumClassPrice: 20, highClassPrice: 50};

            initialLowClass = $scope.stadium.lowClass;
            initialMediumClass = $scope.stadium.mediumClass;
            initialHighClass = $scope.stadium.highClass;
        // });

        $scope.submitStadiumUpgrade = function () {
            FinanceService.postStadiumFinance($scope.stadium);
        };

        $scope.lowClassSpent = 0;
        $scope.mediumClassSpent = 0;
        $scope.highClassSpent = 0;

        $scope.refreshLowClass = function () {
            $scope.lowClassSpent = ($scope.stadium.lowClass - initialLowClass) * $scope.stadium.lowClassPrice;
        };

        $scope.refreshMediumClass = function () {
            $scope.mediumClassSpent = ($scope.stadium.mediumClass - initialMediumClass) * $scope.stadium.mediumClassPrice;
        };

        $scope.refreshHighClass = function () {
            $scope.highClassSpent = ($scope.stadium.highClass - initialHighClass) * $scope.stadium.highClassPrice;
        };
    });

});
