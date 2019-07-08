define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

    footballManager.service('PlayerService', function($http, SettingsService, AccountService) {
        this.url = SettingsService.getUrl();

        this.getPlayers = function () {
            return AccountService.get(this.url + 'players');
        };

        this.getCriteriaTypes = function () {
            return AccountService.get(this.url + 'transfers');
        };

        this.filterSearch = function (filters) {
            var body = AccountService.formFormat(filters);
            return AccountService.put(this.url + 'transfers', body);
        };

        this.buyPlayer = function (playerId) {
            var body = JSON.stringify(playerId);
            return AccountService.post(this.url + 'transfers', body);
        };
    })
});
