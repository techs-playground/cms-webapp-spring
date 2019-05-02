(function (angular) {
  'use strict';

  angular.module('cms.modules.category.services', []).
  service('CategoryService', ['$http',
    function ($http) {
      let serviceAddress = 'http://localhost:8080';
      let apiUrl = `${serviceAddress}/api/category`;

      this.find = () => $http.get(apiUrl);
      this.findOne = (id) => $http.get(`${apiUrl}/${id}`);
      this.create = (data) => $http.post(apiUrl, data);
      this.update = (data) => $http.put(`${apiUrl}/${data._id}`, data);
      this.remove = (data) => $http.delete(`${apiUrl}/${data._id}`);
    }
  ]);
})(angular);