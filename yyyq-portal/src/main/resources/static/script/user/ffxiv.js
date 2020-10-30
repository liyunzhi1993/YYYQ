new Vue({
    el: '#ffxiv',
    data: {
        getUserFFXIVSelectListUrl: 'ffxiv/userffxiv/getSelectList',
        getUrl: 'ffxiv/userffxiv/get',
        selectList: {},
        userffxiv:{}
    },
    mounted: function () {
        var vm = this;
        vm.get();
    },
    methods: {
        getUserFFXIVSelectList: function () {
            var vm = this;
            post(vm.getUserFFXIVSelectListUrl, {}, function (result) {
                if (!result.hasError) {
                    vm.selectList = result.data;
                    vm.userffxiv.area = vm.selectList.areaList[vm.userffxiv.area];
                    vm.userffxiv.server = vm.selectList.chinaSDServerList[vm.userffxiv.server];
                    vm.userffxiv.class = vm.selectList.classList[vm.userffxiv.clazz];
                    vm.userffxiv.race = vm.selectList.raceList[vm.userffxiv.race];
                    console.log(vm.userffxiv)
                }
            }, true, false);
        },
        get: function () {
            var vm = this;
            post(vm.getUrl, {}, function (result) {
                vm.userffxiv=result.data;
                vm.getUserFFXIVSelectList();
            },true,false);
        }
    }
});