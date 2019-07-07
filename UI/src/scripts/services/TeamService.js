define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

  footballManager.service('TeamService', function($http, SettingsService, AccountService) {
      this.url = SettingsService.getUrl() + 'teams/';

      this.getTeams = function () {
        return AccountService.get(this.url);
      };

      this.getTeam = function () {
        return AccountService.get(this.url + 'current');
      };

      this.chooseTeam = function (teamId) {
        var body = JSON.stringify(teamId);
        return AccountService.post(this.url + 'current', body);
      }

  });
});
