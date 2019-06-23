
let app = getApp();

Page({
    data: {
        context:'',
        title: '',
        img: '',
        hotC: {
            comment: [],
            page_type: 'getAComments'
        },
        canShare: wx.canIUse('button.open-type.share')
    },
    onLoad: function(e) {
      var mainId = e.mainId;
      console.log(e);
    },
    onShow() {
        this.getCommentData();
    }
});