define(['footballManager'], function (footballManager) {

  footballManager.controller("IndexCtl", function ($scope) {
    $scope.appName = "hello";
    $scope.username="marti";
    $scope.password="lalala";
    $scope.alreadyHaveAccount = "already have account";
    $scope.signIn = "sign in";
  });

});
