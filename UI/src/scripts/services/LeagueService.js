define(['footballManager', 'services/SettingsService'], function(footballManager) {

    footballManager.service('LeagueService', function($http, SettingsService) {
        this.url = SettingsService.getUrl() + "league/";

        this.getLeagueInformation = function () {
            return $http.get(this.url);
        };
    })
});
