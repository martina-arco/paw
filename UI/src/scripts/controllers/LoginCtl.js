define(['footballManager', 'services/AccountService'], function (footballManager) {

  footballManager.controller("LoginCtl", function ($scope, AccountService) {
      $scope.username="marti";
      $scope.password="lalala";
      $scope.rememberMe = "rememeber me";
      $scope.dontHaveAccount = "don't have account. ";
    $scope.signUp = "sign up";
      $scope.login = function () {
        AccountService.login($scope.username, $scope.password, $scope.rememberMe);
      }
  });

});
