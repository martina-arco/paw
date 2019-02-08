define(['footballManager'], function(footballManager) {

  footballManager.service('TeamService', function($http, $q) {
    this.url = './api/team';

    this.getTeams = function () {
      return $http.get(this.url + 'teams');
    };

  });
});
