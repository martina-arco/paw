define(['footballManager'], function(footballManager) {

  footballManager.service('TeamService', function($http, $q) {
      this.url = 'http://localhost:8080/webapp_war_exploded/teams';

      this.getTeams = function () {
        return $http.get(this.url);
      };

      this.getTeam = function () {
        return $http.get(this.url + 'current');
      };

      this.chooseTeam = function (teamId) {
        var body = JSON.stringify({teamId: teamId});
        return $http.post(this.url + 'current', body);
      }

  });
});
