define("footballManager", function(footballManager) {

   footballManager.service('MatchService', function($http) {
       this.url = "./api/match/";
       
       this.getMatches = function () {
           return $http.get(this.url + 'matches');
       }
   })
});