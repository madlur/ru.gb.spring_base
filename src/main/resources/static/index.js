angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                minFilter: $scope.filter ? $scope.filter.minFilter : null,
                maxFilter: $scope.filter ? $scope.filter.maxFilter : null
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsList = response.data;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.addProduct = function () {
        $http.post(contextPath + '/products/addProduct', $scope.product)
            .then(function (response) {
                $scope.product = null;
                $scope.loadProducts();
            });
    };

    $scope.loadProducts();
})
;