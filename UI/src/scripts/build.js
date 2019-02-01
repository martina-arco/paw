requirejs.config({
  baseUrl: 'scripts',
  // paths: maps ids with paths (no extension)
  paths: {
    'angular':
      ['https://code.angularjs.org/1.3.5/angular']
  },
  // shim: makes external libraries reachable
  shim: {
    angular: {
      exports : 'angular'
    }
  },
  deps: ['./bootstrap']
});
