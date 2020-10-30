new Vue({
    el: '#bindGuild',
    data: {
        getUserWOWSelectListUrl: 'wow/userwow/getSelectList',
        saveUrl:'wow/userwow/save',
        getUrl: 'wow/userwow/get',
        selectList: {},
        userGuild:{}
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
                vm.userwow=result.data;
                vm.getUserWOWSelectList();
            },true,false);
        },
        save:function () {
            var vm = this;
            var data = $("#wowForm").serializeJSON();
            post(vm.saveUrl, JSON.stringify(data), function (result) {
                if (!result.hasError) {
                    alert("保存成功");
                }
            });
        }
    }
});