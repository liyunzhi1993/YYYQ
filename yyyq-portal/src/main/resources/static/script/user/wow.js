new Vue({
    el: '#wow',
    data: {
        getUserWOWSelectListUrl: 'wow/userwow/getSelectList',
        getUrl: 'wow/userwow/get',
        selectList: {},
        userwow:{}
    },
    mounted: function () {
        var vm = this;
        vm.get();
    },
    methods: {
        getUserWOWSelectList: function () {
            var vm = this;
            post(vm.getUserWOWSelectListUrl, {}, function (result) {
                if (!result.hasError) {
                    vm.selectList = result.data;
                    vm.userwow.server = vm.selectList.serverList[vm.userwow.server];
                    vm.userwow.class = vm.selectList.classList[vm.userwow.clazz];
                }
            }, true, false);
        },
        get: function () {
            var vm = this;
            post(vm.getUrl, {}, function (result) {
                vm.userwow=result.data;
                vm.getUserWOWSelectList();
            },true,false);
        }
    }
});