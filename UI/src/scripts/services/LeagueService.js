define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

    footballManager.service('LeagueService', function($http, SettingsService, AccountService) {
        this.url = SettingsService.getUrl() + "league/";

        this.getLeagueInformation = function () {
            return AccountService.get(this.url);
        };
    })
});
