define(['footballManager', 'services/SettingsService', 'services/AccountService'], function(footballManager) {

   footballManager.service('MatchService', function($http, SettingsService, AccountService) {
       this.url = SettingsService.getUrl() + 'matches/';

       this.getUserPlayedMatch = function () {
         return AccountService.get(this.url + 'lastUserPlayed');
       };

       this.getPlayedMatches = function () {
           return AccountService.get(this.url + 'lastPlayed');
       };

       this.getUpcomingMatch = function () {
         return AccountService.get(this.url + 'next');
       };

       this.playMatch = function () {
         return AccountService.get(this.url + 'play');
       };

   })
});
