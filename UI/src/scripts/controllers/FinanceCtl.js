define(['footballManager', 'services/FinanceService'], function (footballManager) {

    footballManager.controller('FinanceCtl', function ($scope, FinanceService) {

        var initialLowClass = 0;
        var initialMediumClass = 0;
        var initialHighClass = 0;

        FinanceService.getEconomy().then(function (response) {
            $scope.summary = response.data.summary;
            $scope.income = response.data.income;
            $scope.expense = response.data.expense;
            $scope.money = response.data.money;
        });

        FinanceService.getReceipts().then(function (response) {
            $scope.receipts = response.data;
        });

        FinanceService.getStadiumFinance().then(function (response) {
            $scope.stadium  = response.data;

            $scope.initialLowClass = $scope.stadium.lowClass;
            $scope.initialMediumClass = $scope.stadium.mediumClass;
            $scope.initialHighClass = $scope.stadium.highClass;
        });

        $scope.submitStadiumUpgrade = function () {
            FinanceService.postStadiumFinance($scope.stadium);
        };

        $scope.lowClassSpent = 0;
        $scope.mediumClassSpent = 0;
        $scope.highClassSpent = 0;

        $scope.refreshLowClass = function () {
            $scope.lowClassSpent = ($scope.stadium.lowClass - $scope.initialLowClass) * $scope.stadium.lowClassPrice;
        };

        $scope.refreshMediumClass = function () {
            $scope.mediumClassSpent = ($scope.stadium.mediumClass - $scope.initialMediumClass) * $scope.stadium.mediumClassPrice;
        };

        $scope.refreshHighClass = function () {
            $scope.highClassSpent = ($scope.stadium.highClass - $scope.initialHighClass) * $scope.stadium.highClassPrice;
        };
    });

});
