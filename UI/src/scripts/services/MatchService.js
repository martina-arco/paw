define(['footballManager', 'services/SettingsService'], function(footballManager) {

   footballManager.service('MatchService', function($http, SettingsService) {
       this.url = SettingsService.getUrl() + 'matches/';

       this.getMatch = function () {
         return $http.get(this.url + 'current');
       };

       this.getUpcomingMatches = function () {
           return $http.get(this.url);
       };

       this.getUpcomingMatch = function () {
         return $http.get(this.url + 'next');
       };

       this.playMatch = function () {
         return $http.get(this.url + 'play');
       };

   })
});
