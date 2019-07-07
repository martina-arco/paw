define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

  footballManager.service('FinanceService', function($http, SettingsService, AccountService) {
    this.url = SettingsService.getUrl() + "finance/";

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
        return AccountService.get(this.url);
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
        return AccountService.get(this.url + 'receipts');
    };

    this.getStadiumFinance = function () {
        return AccountService.get(this.url + 'stadium');
    };

    this.postStadiumFinance = function (finance) {
        var body = JSON.stringify(finance);
        return AccountService.post(this.url + 'stadium', body);
    };
  })
});
