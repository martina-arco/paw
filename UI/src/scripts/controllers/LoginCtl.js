define(['footballManager', 'services/AccountService'], function (footballManager) {

  footballManager.controller("LoginCtl", function ($scope, AccountService) {
      $scope.login = function () {
        AccountService.login($scope.username, $scope.password, $scope.rememberMe);
      }
  });

});
