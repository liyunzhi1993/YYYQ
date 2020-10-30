new Vue({
    el: '#game',
    data: {
        getGameListUrl: 'auth/game/getListByUser',
        gameList:[]
    },
    mounted:function () {
       var vm=this;
       vm.getGameList();
    },
    methods: {
        getGameList: function () {
            var vm = this;
            post(vm.getGameListUrl, {}, function (result) {
                if (!result.hasError) {
                    vm.gameList=result.data;
                }
            });
        }
    }
});