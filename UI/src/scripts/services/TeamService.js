define(['footballManager', 'services/SettingsService'], function(footballManager) {

  footballManager.service('TeamService', function($http, SettingsService) {
      this.url = SettingsService.getUrl() + 'teams/';

      this.getTeams = function () {
        return $http.get(this.url);
      };

      this.getTeam = function () {
        return $http.get(this.url + 'current');
      };

      this.chooseTeam = function (teamId) {
        var body = JSON.stringify(teamId);
        return $http.post(this.url + 'current', body);
      }

  });
});
