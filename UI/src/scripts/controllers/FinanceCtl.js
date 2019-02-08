define(['footballManager'], function (footballManager) {

    footballManager.controller('FinanceCtl', function ($scope) {
        $scope.summary = {
            playersSold: 0,
            ticketsSold: 0,
            playersBought: 0,
            salaries: 0,
            stadiumExpansion: 0
        }; //getSummary
        $scope.economy = {
            income: 0,
            expense: 0,
            money: 0,
            stadium: 0
        }; //getEconomy
        $scope.receipts = [{amount:0, type: "type"}]; //getReceipts
    });

});
