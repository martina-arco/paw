define(['footballManager'], function(footballManager) {

  footballManager.service('SettingsService', function($http) {
      this.getUrl = function () {
        return 'http://localhost:8080/api/';
        return 'api/';
      }

  })
});
