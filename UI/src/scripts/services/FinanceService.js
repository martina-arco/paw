define(['footballManager'], function(footballManager) {

  footballManager.service('FinanceService', function($http) {
    this.url = "./api/v1/finance/";

    // summary: {
    //   playersSold: int,
    //   ticketsSold: int,
    //   playersBought: int,
    //   salaries: int,
    //   stadiumExpansion: int
    // }
    // balance: {
    //   income: int,
    //   expense: int,
    //   money: int,
    //   stadium: int
    // }
    this.getEconomy = function () {
        return $http.get(this.url + 'economy');
    };

    // [
    //   {
    //     amount: int,
    //     type: string
    //   },
    //   {
    //     amount: int,
    //     type: string
    //   }
    // ]
    this.getReceipts = function () {
        return $http.get(this.url + 'receipts');
    };

    this.getStadiumFinance = function () {
        return $http.get(this.url + 'stadium');
    };

    this.postStadiumFinance = function (finance) {
        var body = JSON.stringify(finance);
        return $http.post(this.url + 'stadium', body);
    };
  })
});
