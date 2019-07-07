define(['footballManager', 'services/FinanceService'], function (footballManager) {

    footballManager.controller('FinanceCtl', function ($scope, FinanceService) {

        var setUpReceiptType = function () {
            $scope.receipts.forEach(function (receipt) {
                switch (receipt.type) {
                  case 'SOLDPLAYER':
                    receipt.name = $scope.RECEIPTSOLDPLAYER;
                    break;

                  case 'BOUGHTPLAYER':
                    receipt.name = $scope.RECEIPTBOUGHTPLAYER;
                    break;

                  case 'EXPANDEDSTADIUM':
                    receipt.name = $scope.RECEIPTEXPANDEDSTADIUM;
                    break;

                  case 'MATCHINCOME':
                    receipt.name = $scope.RECEIPTMATCHINCOME;
                    break;

                  case 'TOURNAMENTPRIZE':
                    receipt.name = $scope.RECEIPTTOURNAMENTPRIZE;
                    break;

                  case 'PLAYERSSALARIES':
                    receipt.name = $scope.RECEIPTPLAYERSSALARIES;
                    break;
                }
            })
        };

        FinanceService.getEconomy().then(function (response) {
            $scope.summary = response.data.summary;
            $scope.income = response.data.income;
            $scope.expense = response.data.expense;
            $scope.money = response.data.money;
        });

        FinanceService.getReceipts().then(function (response) {
            $scope.receipts = response.data;
            setUpReceiptType($scope.receipts);
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
