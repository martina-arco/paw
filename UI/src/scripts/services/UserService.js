define(['footballManager', 'services/SettingsService'], function(footballManager) {

  footballManager.service('UserService', function($http, SettingsService) {
    this.url = SettingsService.getUrl();

    this.createUser = function (user) {
        var body = JSON.stringify(user);
        return $http.post(this.url + 'register', body);
    };
  })
});
