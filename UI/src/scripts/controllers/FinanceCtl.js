define(['footballManager', 'services/FinanceService'], function (footballManager) {

    footballManager.controller('FinanceCtl', function ($scope, $window, ngDialog, FinanceService) {

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

        $scope.openStadiumExpansionSuccessModal = function() {
          ngDialog.open({
            templateUrl: 'views/stadiumExpansionSuccessModal.html',
            className: 'ngdialog-theme-default',
            scope: $scope
          });
        };

        var setUpEconomy = function () {
          FinanceService.getEconomy().then(function (response) {
            $scope.summary = response.data.summary;
            $scope.income = response.data.income;
            $scope.expense = response.data.expense;
            $scope.money = response.data.money;
          });
        };

        var setUpReceipts = function () {
          FinanceService.getReceipts().then(function (response) {
            $scope.receipts = response.data;
            setUpReceiptType($scope.receipts);
          });
        };

        var setUpStadium = function () {
          FinanceService.getStadiumFinance().then(function (response) {
            $scope.stadium  = response.data;

            $scope.lowClassSpent = 0;
            $scope.mediumClassSpent = 0;
            $scope.highClassSpent = 0;

            $scope.initialLowClass = $scope.stadium.lowClass;
            $scope.initialMediumClass = $scope.stadium.mediumClass;
            $scope.initialHighClass = $scope.stadium.highClass;
          });
        };

        $scope.submitStadiumUpgrade = function () {
            FinanceService.postStadiumFinance($scope.stadium).then(function (response) {
              setUpEconomy();
              setUpReceipts();
              setUpStadium();
              $scope.openStadiumExpansionSuccessModal();
            });
        };

        $scope.refreshLowClass = function () {
            if($scope.stadium.lowClass != null)
                $scope.lowClassSpent = ($scope.stadium.lowClass - $scope.initialLowClass) * $scope.stadium.lowCost;
        };

        $scope.refreshMediumClass = function () {
            if($scope.stadium.mediumClass != null)
                $scope.mediumClassSpent = ($scope.stadium.mediumClass - $scope.initialMediumClass) * $scope.stadium.mediumCost;
        };

        $scope.refreshHighClass = function () {
            if($scope.stadium.highClass != null)
                $scope.highClassSpent = ($scope.stadium.highClass - $scope.initialHighClass) * $scope.stadium.highCost;
        };

        setUpEconomy();
        setUpReceipts();
        setUpStadium();
    });

});
