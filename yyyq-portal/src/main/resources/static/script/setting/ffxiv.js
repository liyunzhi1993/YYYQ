new Vue({
    el: '#ffxiv',
    data: {
        getUserFFXIVSelectListUrl: 'ffxiv/userffxiv/getSelectList',
        saveUrl:'ffxiv/userffxiv/save',
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
                    $(".selectpicker").selectpicker();
                    vm.selectList = result.data;
                    setTimeout(function () {
                        $('.selectpicker').selectpicker('refresh');
                    }, 1);
                }
            }, true, false);
        },
        get: function () {
            var vm = this;
            post(vm.getUrl, {}, function (result) {
                vm.userffxiv=result.data;
                vm.getUserFFXIVSelectList();
            },true,false);
        },
        save:function () {
            var vm = this;
            var data = $("#ffxivForm").serializeJSON();
            data.area = 1;
            post(vm.saveUrl, JSON.stringify(data), function (result) {
                if (!result.hasError) {
                    alert("保存成功");
                }
            });
        }
    }
});