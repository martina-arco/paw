define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

  footballManager.service('UserService', function($http, SettingsService, AccountService) {
    this.url = SettingsService.getUrl() + 'user/';

    this.advanceDate = function () {
        return AccountService.post(this.url + 'advanceDate', null);
    };
  })
});
