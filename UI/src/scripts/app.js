define([
  'angular',
  './controllers/IndexCtl',
  './services/IndexService'
], function (ng) {
  'use strict';

  return ng.module('app', [
    'app.services',
    'app.controllers'
  ]);
});
