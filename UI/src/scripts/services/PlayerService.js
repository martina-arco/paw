define(['footballManager', 'services/SettingsService'], function(footballManager) {

    footballManager.service('PlayerService', function($http, SettingsService) {
        this.url = SettingsService.getUrl();

        this.getPlayers = function () {
            return $http.get(this.url + 'players');
        };

        this.getCriteriaTypes = function () {
            return $http.get(this.url + 'players/criteriaTypes');
        };

        this.filterSearch = function (filters) {
            var body = JSON.stringify(filters);
            return $http.post(this.url + 'players/filters', body);
        };

        this.buyPlayer = function (playerId) {
            var body = JSON.stringify({playerId: playerId});
            return $http.post(this.url + 'player/buy', body);
        };

        this.sellPlayer = function (playerId) {
            var body = JSON.stringify({playerId: playerId});
            return $http.post(this.url + 'player/sell', body);
        }
    })
});
